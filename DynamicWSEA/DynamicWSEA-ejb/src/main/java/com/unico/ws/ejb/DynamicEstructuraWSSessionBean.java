/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.ejb;

import com.unico.ws.entity.ParametrosToMapInstClass;
import com.unico.ws.entity.PublicadorWsClases;
import com.unico.ws.entity.PublicadorWsEjecucionOperacionCliente;
import com.unico.ws.entity.PublicadorWsEstructura;
import com.unico.ws.entity.PublicadorWsOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosToMapManagebean;
import com.unico.ws.interfaces.DynamicEstructuraWSSessionBeanLocal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author T13237
 */
@Stateless(mappedName = "ejb/DynamicEstructuraWSSessionBean")
public class DynamicEstructuraWSSessionBean implements DynamicEstructuraWSSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "DynamicWS_0PU")
    private EntityManager mg;

    /**
     * Bsuca todos los ws a publicar
     *
     * @return
     */
    @Override
    public List<PublicadorWsEstructura> findALLWebServicesWebToPublish() {

        Query q = mg.createNamedQuery("PublicadorWsEstructura.findAll", PublicadorWsEstructura.class);
        List<PublicadorWsEstructura> listPublicadorWsEstructura = q.getResultList();

        return listPublicadorWsEstructura;

    }

    public PublicadorWsEstructura finPublicadorWsEstructuraById(Integer id) {
        Query q = mg.createNamedQuery("PublicadorWsEstructura.findByIdWs", PublicadorWsEstructura.class);
        q.setParameter("idWs", id);
        return (PublicadorWsEstructura) q.getSingleResult();
    }

    @Override
    public PublicadorWsEstructura updatePublicadorWsEstructura(PublicadorWsEstructura estructura) {
        estructura = mg.merge(estructura);
        return estructura;
    }

    @Override
    public void refreshContextEstructura() {
        //    mg.flush();
        Query q = mg.createNamedQuery("PublicadorWsEstructura.findAll");
        List<PublicadorWsEstructura> publicadorWsEstructuraList = q.getResultList();
        for (PublicadorWsEstructura estructura : publicadorWsEstructuraList) {
            mg.refresh(estructura);
        }
        //Busca Todas las operaciones
        q = mg.createNamedQuery("PublicadorWsOperaciones.findAll");
        List<PublicadorWsOperaciones> publicadorWsOperacionesList = q.getResultList();
        for (PublicadorWsOperaciones operaciones : publicadorWsOperacionesList) {
            mg.refresh(operaciones);
        }
        //Recarga el contexto de las entidades PublicadorWsClases
        q = mg.createNamedQuery("PublicadorWsClases.findAll");
        List<PublicadorWsClases> listPublicadorWsClases = q.getResultList();
        for (PublicadorWsClases publicadorWsClases : listPublicadorWsClases) {
            mg.refresh(publicadorWsClases);
        }

        q = mg.createNamedQuery("PublicadorWsParametrosToMapManagebean.findAll");
        List<PublicadorWsParametrosToMapManagebean> listPublicadorWsParametrosToMapManagebean = q.getResultList();
        for (PublicadorWsParametrosToMapManagebean publicadorWsParametrosToMapManagebean : listPublicadorWsParametrosToMapManagebean) {
            mg.refresh(publicadorWsParametrosToMapManagebean);
        }
        
        q = mg.createNamedQuery("ParametrosToMapInstClass.findAll");
        List<ParametrosToMapInstClass> listParametrosToMapInstClass = q.getResultList();
        for (ParametrosToMapInstClass parametrosToMapInstClass : listParametrosToMapInstClass) {
            mg.refresh(parametrosToMapInstClass);
        }
        
         q = mg.createNamedQuery("PublicadorWsEjecucionOperacionCliente.findAll");
        List<PublicadorWsEjecucionOperacionCliente> listPublicadorWsEjecucionOperacionCliente = q.getResultList();
        for (PublicadorWsEjecucionOperacionCliente publicadorWsEjecucionOperacionCliente : listPublicadorWsEjecucionOperacionCliente) {
            mg.refresh(publicadorWsEjecucionOperacionCliente);
        }

    }
}
