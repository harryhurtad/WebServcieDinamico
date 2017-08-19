/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.ejb;


import com.unico.ws.constantes.DynamicWSMessageException;
import com.unico.ws.exception.DynamicWSException;
import com.unico.ws.interfaces.DynamicProductorJMSSesBeanLocal;
import com.unico.ws.object.RequestDynamicWSJMS;
import com.unico.ws.operation.OperationRequestWS;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

/**
 *
 * @author T13237
 */
@Stateless
public class DynamicProductorJMSSesBean implements DynamicProductorJMSSesBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Resource(mappedName = "jms/dynamicWSFactory")
    private  ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/dynamicWSQueue")
    private  Queue queue;
    private  static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DynamicProductorJMSSesBean.class);
    
    @Override
    public void enviaMensajeCola(RequestDynamicWSJMS request)throws DynamicWSException{
        Connection connection = null;
        Session session = null;

        MessageProducer producer = null;
        Message message ;
        boolean esTransaccional = true;
        try {
            connection = connectionFactory.createConnection();
            // Recordar llamar a start() para permitir el envio de mensajes
            connection.start();
            // Creamos una sesion sin transaccionalidad y con envio de acuse automatico
            session = connection.createSession(esTransaccional, Session.AUTO_ACKNOWLEDGE);
            // Creamos el productor a partir de una cola
            producer = session.createProducer(queue);
            // Se envia el obj con los parametros de la peticion
            message = session.createObjectMessage(request);         
            // Mediante le productor, enviamos el mensaje
            producer.send(message);
			
 
        } catch (JMSException ex) {            
            LOGGER.error(DynamicWSMessageException.ERROR_SEND_JMS);
            throw new DynamicWSException(DynamicWSMessageException.ERROR_SEND_JMS);
        } finally {
           
            try {
                // Cerramos los recursos
                 if(producer!=null){
                     producer.close();
                 }
                 
                 if(session!=null){
                     session.close();
                 }
                 
                 if(connection!=null){
                       connection.close();
                 }
                
              
            } catch (JMSException ex) {
                LOGGER.error(DynamicWSMessageException.ERROR_SEND_JMS);
                throw new DynamicWSException(DynamicWSMessageException.ERROR_SEND_JMS);
            }
           
        }
    
    }
}
