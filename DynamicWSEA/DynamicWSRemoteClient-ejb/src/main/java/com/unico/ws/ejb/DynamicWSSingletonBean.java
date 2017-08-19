/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.ejb;

import com.unico.ws.constantes.DynamicWS;
import com.unico.ws.constantes.DynamicWSMessageException;
import com.unico.ws.interfaces.DynamicParametrizacionSessionBeanRemote;
import com.unico.ws.interfaces.DynamicWSSingletonBeanLocal;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author T13237
 */
@Singleton
@Startup
public class DynamicWSSingletonBean implements DynamicWSSingletonBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public static final Logger LOGGER = Logger.getLogger(DynamicWSSingletonBean.class);
    @EJB(  lookup = "ejb/DynamicParametrizacionSessionBean")
    private DynamicParametrizacionSessionBeanRemote dynamicParametrizacionSessionBean;
    private String rutaLog4jProperties;
    
    @PostConstruct
    @Override
     public void init() {
          Properties configProps = new Properties();
        try {
            //dynamicParametrizacionSessionBean = (DynamicParametrizacionSessionBeanLocal) new InitialContext().lookup("java:global:DynamicParametrizacionSessionBean");
            rutaLog4jProperties = dynamicParametrizacionSessionBean.buscarParametroPorNombre(DynamicWS.RUTA_PROPERTIES_LOG4J).getValorParametro();
            configProps.clear();
            String rutaLog4j = dynamicParametrizacionSessionBean.buscarParametroPorNombre(DynamicWS.RUTA_LOG_WS).getValorParametro();
            System.setProperty("ruta.log4j", rutaLog4j);
            FileInputStream fis = new FileInputStream(new File(rutaLog4jProperties));
            configProps.load(fis);
            PropertyConfigurator.configure(configProps);
            LOGGER.info("iniciando Log de DynamicWSRemoteClient de Web Services .... ");
        } catch (IOException e) {
            LOGGER.error("DynamicWSRemoteClient->Error al configurar el Log usando la librería LOG4J", e);
        } catch (Exception e) {
            LOGGER.error("DynamicWSRemoteClient->"+DynamicWSMessageException.ERROR_INIT_LOG4J, e);
        }
     }
}
