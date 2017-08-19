/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.ejb;

import com.unico.ws.constantes.DynamicWS;
import com.unico.ws.constantes.DynamicWSMessageException;
import com.unico.ws.dto.PuplishWSDTO;
import com.unico.ws.interfaces.DynamicParametrizacionSessionBeanRemote;

import com.unico.ws.interfaces.DynamicWSContextSingletonBeanLocal;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author T13237
 */
@Singleton(mappedName = "ejb/DynamicWSContextSingletonBean")
public class DynamicWSContextSingletonBean implements DynamicWSContextSingletonBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
   
    private String directorioSalida;
    private String directorioFuentes;
    private String hostWS;
    private String portWS;
    private Map<Integer,PuplishWSDTO> mapPuplishWSDTO;
    @EJB(lookup = "ejb/DynamicParametrizacionSessionBean")
    private DynamicParametrizacionSessionBeanRemote dynamicParametrizacionSessionBean;
    private  static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DynamicWSContextSingletonBean.class);
    @PostConstruct
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    @Override
    public void init() {
             
       // Properties prop = new Properties();
       try{
        directorioSalida=(dynamicParametrizacionSessionBean.buscarParametroPorNombre(DynamicWS.PARAMETRO_RUTA_DIRECTORIO_SALIDA)).getValorParametro();
        directorioFuentes=(dynamicParametrizacionSessionBean.buscarParametroPorNombre(DynamicWS.PARAMETRO_RUTA_DIRECTORIO_FUENTES)).getValorParametro();
        portWS=(dynamicParametrizacionSessionBean.buscarParametroPorNombre(DynamicWS.PUERTO_WS)).getValorParametro();
        hostWS=(dynamicParametrizacionSessionBean.buscarParametroPorNombre(DynamicWS.HOST_WS)).getValorParametro();
       }catch(Exception e){            
            LOGGER.error(DynamicWSMessageException.ERROR_INICIAL_PARAM_PUBLISH_WS, e);
           
       }
       
       /* try (InputStream in = PublicDynamicWSSessionBean.class.getResourceAsStream("/META-INF/DynamicWS.properties")) {
            prop.load(in);
            directorioSalida = prop.getProperty(DynamicWS.PARAMETRO_RUTA_DIRECTORIO_SALIDA);
            directorioFuentes = prop.getProperty(DynamicWS.PARAMETRO_RUTA_DIRECTORIO_FUENTES);
        } catch (IOException ex) {
            Logger.getLogger(PublicDynamicWSSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }

    @Override
    public String getHostWS() {
        return hostWS;
    }

    @Override
    public String getPortWS() {
        return portWS;
    }


    
    
    @Override
    public String getDirectorioSalida() {
        return directorioSalida;
    }

    @Override
    public String getDirectorioFuentes() {
        return directorioFuentes;
    }
    
    
    
 
    /*public void setMapWebServicesWebToPublish(Map<Integer, PublicadorWsEstructura> mapWebServicesWebToPublish) {
        this.mapWebServicesWebToPublish = mapWebServicesWebToPublish;
    }*/

    @Override
    public Map<Integer, PuplishWSDTO> getMapPuplishWSDTO() {
        return mapPuplishWSDTO;
    }

    @Override
    public void setMapPuplishWSDTO(Map<Integer, PuplishWSDTO> mapPuplishWSDTO) {
        this.mapPuplishWSDTO = mapPuplishWSDTO;
    }

  
  

    

  

}
