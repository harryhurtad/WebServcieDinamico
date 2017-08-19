/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.ejb;

import com.unico.ws.constantes.StatusExecutionWSEnum;
import com.unico.ws.entity.PublicadorWsEstadoInvocOper;
import com.unico.ws.entity.PublicadorWsOperaciones;
import javax.ejb.Stateless;
import com.unico.ws.interfaces.DynamicWSExecutionStatusBeanRemote;
import java.util.Date;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author T13237
 */
@Stateless
public class DynamicWSExecutionStatusBean implements DynamicWSExecutionStatusBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @PersistenceContext(unitName = "DynamicWS_0PU")
    private EntityManager mg;
     
     /**
      * crea un nuevo estado de ejeciion de una operacion
      * @param idOperacion
      * @param idRequest 
      */
     @Override
       @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void createNewPublicadorWsEstadoInvocOper(Integer idOperacion,String idRequest){
        //Busca la operacion;
        Query q=mg.createNamedQuery("PublicadorWsOperaciones.findByIdOperacion");
        q.setParameter("idOperacion", idOperacion);
        PublicadorWsOperaciones operacion=(PublicadorWsOperaciones)q.getSingleResult();
        //Crea el nuevo registro PublicadorWsEstadoInvocOper
        PublicadorWsEstadoInvocOper invocOper=new PublicadorWsEstadoInvocOper(operacion, idRequest, StatusExecutionWSEnum.INICIO);
        invocOper.setFechaInicio(new Date());
        mg.persist(invocOper);
    
    }
    
    /**
     *  busca el estado de la operacion
     * @param idRequest
     * @return 
     */
     @Override   
    public PublicadorWsEstadoInvocOper findPublicadorWsEstadoInvocOperByRequest(String idRequest){
        Query q=mg.createNamedQuery("PublicadorWsEstadoInvocOper.findByIdRequest");
        q.setParameter("idRequest", idRequest);
         PublicadorWsEstadoInvocOper registro=null;
        try{
           registro =(PublicadorWsEstadoInvocOper)q.getSingleResult();  
       
        }catch(NoResultException ex){
            
        }    
        return registro;
    }
    
    
    /**
     * actualiza el estado de ejecucion de la operacion
     * @param idRequest
     * @param estado
     * @param excepcion 
     */
     @Override
      @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updatePublicadorWsEstadoInvocOper(String idRequest,StatusExecutionWSEnum estado,String excepcion){
        //Busca el registro
        PublicadorWsEstadoInvocOper registro= findPublicadorWsEstadoInvocOperByRequest(idRequest);
        registro.setEstado(estado);
        switch(estado){
          
            case FIN:
                registro.setFechaFinalizacion(new Date());
                break;
            case ERROR:
                registro.setFechaFinalizacion(new Date());
                registro.setExcepcion(excepcion);
                break;
        }
        //actualiza la entidad
        mg.merge(registro);
    
    }
}
