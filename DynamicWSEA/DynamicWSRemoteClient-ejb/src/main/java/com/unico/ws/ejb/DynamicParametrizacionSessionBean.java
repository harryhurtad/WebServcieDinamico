/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.ejb;

import com.unico.ws.interfaces.DynamicParametrizacionSessionBeanRemote;
import com.unico.ws.entity.ConstantesParametrizacionWs;
import com.unico.ws.entity.PublicadorWsEstructura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author T13237
 */
@Stateless(mappedName = "ejb/DynamicParametrizacionSessionBean")
//@Stateless
public class DynamicParametrizacionSessionBean implements   DynamicParametrizacionSessionBeanRemote{

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
     @PersistenceContext(unitName = "DynamicWS_0PU")
    private EntityManager mg;
     
     /***
      * Busca todos los parametros parametrizados en ConstantesParametrizacionWs
      * @return 
      */
     @Override
     public List<ConstantesParametrizacionWs> buscarParametrizacionDynamicWS(){
         Query query=mg.createNamedQuery("ConstantesParametrizacionWs.findAll");
         return query.getResultList();
     }
     
     /**
      * Busca el parametro por nombre 
      * @param nobmreParametro
      * @return 
      */
     @Override
     public ConstantesParametrizacionWs buscarParametroPorNombre(String nobmreParametro){
          Query query=mg.createNamedQuery("ConstantesParametrizacionWs.findByNombreParametro");
          query.setParameter("nombreParametro", nobmreParametro);
         return (ConstantesParametrizacionWs)query.getSingleResult();
     }
     
     
      @Override
      public String buscarNombreContextoCliente(String nombreWS){
         Query query=mg.createNamedQuery("PublicadorWsEstructura.findByNombreWs");
         query.setParameter("nombreWs", nombreWS);
         PublicadorWsEstructura entity=(PublicadorWsEstructura) query.getSingleResult();
         return  entity.getContexto_app_cliente();
      }
}
