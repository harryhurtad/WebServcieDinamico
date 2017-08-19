/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.interceptor;

import com.unico.ws.constantes.StatusExecutionWSEnum;
import com.unico.ws.entity.PublicadorWsEstadoInvocOper;
import com.unico.ws.interfaces.DynamicWSExecutionStatusBeanRemote;
import com.unico.ws.object.RequestDynamicWSJMS;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.jms.ObjectMessage;
import javax.naming.InitialContext;

/**
 *
 * @author T13237
 */
public class LogDynamicWSExecStatusInter {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {
    
        DynamicWSExecutionStatusBeanRemote bean = (DynamicWSExecutionStatusBeanRemote) new InitialContext().lookup("ejb/DynamicWSExecutionStatusBean");
        ObjectMessage msg=(ObjectMessage)context.getParameters()[0];        
        RequestDynamicWSJMS request = (RequestDynamicWSJMS) msg.getObject();
        String idRequestId = request.getRequestId();
        //Busca que no exista la peticion
        if(bean.findPublicadorWsEstadoInvocOperByRequest(idRequestId)==null){
            bean.createNewPublicadorWsEstadoInvocOper(request.getIdOperacion(), idRequestId);
        }

        Object result = context.proceed();
        PublicadorWsEstadoInvocOper requestToProcess= bean.findPublicadorWsEstadoInvocOperByRequest(idRequestId);
        if(!requestToProcess.getEstado().equals(StatusExecutionWSEnum.ERROR) ){
            bean.updatePublicadorWsEstadoInvocOper(idRequestId, StatusExecutionWSEnum.FIN, null);
        }

        return result;
    }
}
