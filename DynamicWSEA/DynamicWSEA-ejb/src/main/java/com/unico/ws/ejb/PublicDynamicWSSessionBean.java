/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.ejb;

import com.unico.ws.constantes.DynamicWSMessageException;
import com.unico.ws.dto.PuplishWSDTO;
import com.unico.ws.entity.PublicadorWsEstructura;
import com.unico.ws.exception.DynamicWSException;
import com.unico.ws.interfaces.PublicDynamicWSSessionBeanLocal;
import com.unico.ws.helper.DynamicWSHelper;
import com.unico.ws.interfaces.DynamicEstructuraWSSessionBeanLocal;
import com.unico.ws.interfaces.DynamicWSContextSingletonBeanLocal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.apache.log4j.Logger;

/**
 *
 * @author T13237
 */
@Stateless
public class PublicDynamicWSSessionBean implements PublicDynamicWSSessionBeanLocal {

    private static final Logger LOGGER = Logger.getLogger(PublicDynamicWSSessionBean.class);
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private DynamicEstructuraWSSessionBeanLocal dynamicEstructuraWSSessionBean;
    @EJB
    private DynamicWSContextSingletonBeanLocal singletonBean;

    /**
     * Publica los Servicios Web
     *
     * @return
     * @throws com.unico.ws.exception.DynamicWSException
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<PuplishWSDTO> publicarWS() throws DynamicWSException {
        List<PuplishWSDTO> listWSReturn = new ArrayList<>();
        Map<Integer, PuplishWSDTO> mapListTmp = new HashMap<>();
        //Carga la informacion de la BD  
        LOGGER.info("*****Inicia la publicacion de los servicios************");
        List<PublicadorWsEstructura> listWSToPublish = dynamicEstructuraWSSessionBean.findALLWebServicesWebToPublish();

        //Recorre los WS a publicar
        for (PublicadorWsEstructura wsToPublish : listWSToPublish) {
            LOGGER.info("Inicio publicacion de servicio: "+wsToPublish.getNombreWs());
            //Obtiene el WS
           PuplishWSDTO puplishWSDTO;
           if(wsToPublish.getHabilitado()){
                puplishWSDTO = DynamicWSHelper.getInstance().createWSEndPoint(wsToPublish, singletonBean);
           }else{
               String URLToPublish="http://"+singletonBean.getHostWS()+":"+singletonBean.getPortWS()+"/"+ wsToPublish.getContextoWS()+"/"+wsToPublish.getNombreWs();
               puplishWSDTO=new PuplishWSDTO(wsToPublish.getIdWs(), wsToPublish.getNombreWs(), URLToPublish,null , false);
           }
           
            listWSReturn.add(puplishWSDTO);
            mapListTmp.put(wsToPublish.getIdWs(), puplishWSDTO);
            LOGGER.info("publicacion exitosa del servicio: "+wsToPublish.getNombreWs());

        }
         LOGGER.info("*****Finaliza la publicacion de los servicios************");
        singletonBean.setMapPuplishWSDTO(mapListTmp);
        return listWSReturn;

    }

    /**
     * Deshabilita el EndPoint
     *
     * @param listPuplishWSDTO
     * @return
     */
    @Override
    public List<PuplishWSDTO> deshabilitarWebServices(List<PuplishWSDTO> listPuplishWSDTO) {
        
        for (PuplishWSDTO instanciaWS : listPuplishWSDTO) {
            //Deshabilita la instancia
            PublicadorWsEstructura estructura = dynamicEstructuraWSSessionBean.finPublicadorWsEstructuraById(instanciaWS.getIdWS());
            estructura.setHabilitado(false);
            PuplishWSDTO instanceTmp = singletonBean.getMapPuplishWSDTO().get(instanciaWS.getIdWS());
            instanceTmp.setHabilitado(false);
            //Quita el endpoint       
            instanciaWS.getEndpoint().stop();
            dynamicEstructuraWSSessionBean.updatePublicadorWsEstructura(estructura);
            LOGGER.info("Se Deshabilita la instancia de WS ->"+estructura.getNombreWs());
            
        }

        return getListPuplishWSDTO();
    }

    /**
     * Obtiene el listado que se encuentra en cache
     *
     * @return
     */
    private List<PuplishWSDTO> getListPuplishWSDTO() {
        List<PuplishWSDTO> listTmp = new ArrayList<>();
        for (Map.Entry<Integer, PuplishWSDTO> instance : singletonBean.getMapPuplishWSDTO().entrySet()) {
            listTmp.add(instance.getValue());
        }

        return listTmp;
    }

    /**
     * Habilita el EndPoint
     *
     * @param listPuplishWSDTO
     * @return
     */
    @Override
    public List<PuplishWSDTO> habilitarWebServices(List<PuplishWSDTO> listPuplishWSDTO)throws Exception  {
        for (PuplishWSDTO instanciaWS : listPuplishWSDTO) {
            try {
                //Habilita la instancia
                PublicadorWsEstructura estructura = dynamicEstructuraWSSessionBean.finPublicadorWsEstructuraById(instanciaWS.getIdWS());
                estructura.setHabilitado(true);
                //Crea de nuevo el endpoint
                PuplishWSDTO objWsTmp = DynamicWSHelper.getInstance().createWSEndPoint(estructura, singletonBean);
                //Adicion el nuevo WS
                singletonBean.getMapPuplishWSDTO().put(instanciaWS.getIdWS(), objWsTmp);
                // listPuplishWSDTO(instanciaWS);

                dynamicEstructuraWSSessionBean.updatePublicadorWsEstructura(estructura);
                 LOGGER.info("Se Habilita la instancia de WS ->"+objWsTmp.getNombreWS());
            } catch (DynamicWSException ex) {
                LOGGER.error(DynamicWSMessageException.ERROR_ENABLE_WS, ex);
                throw new Exception(DynamicWSMessageException.ERROR_ENABLE_WS);
            }
        }
        return getListPuplishWSDTO();

    }

    @Override
    public void loadDynamicPublicWSContext() {

        singletonBean.init();
        dynamicEstructuraWSSessionBean.refreshContextEstructura();

    }

}
