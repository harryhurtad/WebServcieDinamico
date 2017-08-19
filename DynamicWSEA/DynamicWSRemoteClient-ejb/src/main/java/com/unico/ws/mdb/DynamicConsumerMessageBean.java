/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.mdb;

import com.unico.ws.constantes.DynamicWSMessageException;
import com.unico.ws.constantes.StatusExecutionWSEnum;
import com.unico.ws.ejb.DynamicProductorJMSSesBean;
import com.unico.ws.exception.CompilerWSClassException;
import com.unico.ws.exception.DynamicWSException;
import com.unico.ws.helper.ContextOperationWS;
import com.unico.ws.helper.DynamicWSClientHelper;
import com.unico.ws.helper.DynamicWSServiceLocator;
import com.unico.ws.helper.SendToRequestServletHelper;
import com.unico.ws.helper.WSDynamicUtil;
import com.unico.ws.interceptor.LogDynamicWSExecStatusInter;
import com.unico.ws.interfaces.DynamicWSExecutionStatusBeanRemote;
import com.unico.ws.object.RequestDynamicWSJMS;
import com.unico.ws.object.ReqParamsComplexObjDynaJMS;
import com.unico.ws.object.RequestParamDynamicJMS;
import com.unico.ws.object.RequestParamPrimitiveDynaJMS;
import com.unico.ws.operation.OperationRequestParamWS;
import com.unico.ws.operation.OperationRequestWS;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.EJBHome;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author T13237
 */
@MessageDriven(mappedName = "jms/dynamicWSQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
@Interceptors(LogDynamicWSExecStatusInter.class)
public class DynamicConsumerMessageBean implements MessageListener {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DynamicConsumerMessageBean.class);

    public DynamicConsumerMessageBean() {
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void onMessage(Message message) {

        String idRequest = null;
        //REcibe el mensaje 
        if (message instanceof ObjectMessage) {
            try {
                ObjectMessage msg = (ObjectMessage) message;
                DynamicWSClientHelper clientHelper = new DynamicWSClientHelper();
                SendToRequestServletHelper helper = new SendToRequestServletHelper();
                //Obtiene el request

                RequestDynamicWSJMS request = (RequestDynamicWSJMS) msg.getObject();
                idRequest = request.getRequestId();
                LOGGER.info("Se procesa la peticion en la cola DynamicConsumerMessageBean No: " + idRequest);
                //Transformar RequestDynamicWSJMS a OperationRequestWS
                OperationRequestWS operRequest = new OperationRequestWS(request.getRequestId(), request.getIdOperacion(), request.getContextWebAppCLient());
                //Recorro map de parametros 
                Map<Integer, OperationRequestParamWS> mapParamsRequest = new HashMap();
                for (Map.Entry<Integer, RequestParamDynamicJMS> param : request.getMapParamsOperationWS().entrySet()) {

                    String className;
                    Object instance;
                    if (param.getValue() instanceof ReqParamsComplexObjDynaJMS) {
                        //Realiza deserializacion del obj y lo prepara para ser enviado por el obj de tipo(OperationRequestParamWS)  que procesa el servlet
                        ReqParamsComplexObjDynaJMS paramToTransformObj = (ReqParamsComplexObjDynaJMS) param.getValue();
                        className = paramToTransformObj.getListClassToLoad()[paramToTransformObj.getListClassToLoad().length - 1];
                        /*try {
                            Class.forName(className);
                            instance = clientHelper.realizarDesSerializacion(paramToTransformObj.getInstanceSerializable());
                        } catch (ClassNotFoundException ex) {*/
                        if (WSDynamicUtil.getInstance().evaluateJavaCollectionValuesClass(className)) {
                            instance = paramToTransformObj.getInstanceSerializable();
                        } else {
                            instance = clientHelper.realizarDesSerializacion(DynamicConsumerMessageBean.class.getClassLoader(), paramToTransformObj.getListClassToLoad(), paramToTransformObj.getInstanceSerializable());

                        }

                        // }   
                    } else {
                        RequestParamPrimitiveDynaJMS paramPrimitive = (RequestParamPrimitiveDynaJMS) param.getValue();
                        className = paramPrimitive.getParameter().getClass().getName();
                        instance = paramPrimitive.getParameter();
                    }
                    OperationRequestParamWS paramTmp = new OperationRequestParamWS(className, instance);

                    mapParamsRequest.put(param.getKey(), paramTmp);

                }
                operRequest.getMapParamsRequest().putAll(mapParamsRequest);
                //Adiciona la peticion al contexto de solicitudes
                ContextOperationWS.getInstance().getMapObjectContext().put(operRequest.getRequestId(), operRequest);
                //Envia la transaccion
                LOGGER.info("DynamicConsumerMessageBean-> Envia la peticion  No: " + idRequest);
                helper.sendRequestServlet(operRequest);
            } catch (JMSException | ClassNotFoundException | IOException | CompilerWSClassException | NamingException ex) {
                LOGGER.error(DynamicWSMessageException.ERROR_PROCCESS_JMS, ex);
                if (idRequest != null) {
                    sendNotificacion(DynamicWSMessageException.ERROR_EJECUTAR_OPERACION_WS,idRequest);                    
                }

            } catch (DynamicWSException ex) {
                 sendNotificacion(ex.getMessage(),idRequest);
            }

        }

    }

    private void sendNotificacion(String message, String idRequest) {
        try {

            DynamicWSExecutionStatusBeanRemote bean = (DynamicWSExecutionStatusBeanRemote) new InitialContext().lookup("ejb/DynamicWSExecutionStatusBean");
            bean.updatePublicadorWsEstadoInvocOper(idRequest, StatusExecutionWSEnum.ERROR, message);
        } catch (NamingException ex1) {
            LOGGER.error(DynamicWSMessageException.ERROR_PROCCESS_JMS, ex1);
        }
    }

}
