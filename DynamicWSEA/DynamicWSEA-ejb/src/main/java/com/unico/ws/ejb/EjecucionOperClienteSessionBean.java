/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.ejb;

import com.unico.ws.entity.PublicadorWsClases;
import com.unico.ws.entity.PublicadorWsEjecucionOperacionCliente;
import com.unico.ws.entity.PublicadorWsMetodosOperaciones;
import com.unico.ws.entity.PublicadorWsOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosMetodosOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosToMapManagebean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.unico.ws.interfaces.EjecucionOperClienteSessionBeanRemote;

/**
 *
 * @author T13257
 */
@Stateless(mappedName = "ejb/EjecucionOperClienteSessionBean")
public class EjecucionOperClienteSessionBean implements EjecucionOperClienteSessionBeanRemote {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "DynamicWS_0PU")
    private EntityManager mg;

    public Integer consultaIdOperacion(String nombre) {
        Query q = mg.createNamedQuery("PublicadorWsOperaciones.findByNombreOperacion", PublicadorWsOperaciones.class);
        q.setParameter("nombreOperacion", nombre);
        PublicadorWsOperaciones operacion = (PublicadorWsOperaciones) (q.getSingleResult());
        return operacion.getIdOperacion();
    }

    public PublicadorWsOperaciones consultaOperacion(Integer idOperacion) {
        Query q = mg.createNamedQuery("PublicadorWsOperaciones.findByIdOperacion", PublicadorWsOperaciones.class);
        q.setParameter("idOperacion", idOperacion);
        PublicadorWsOperaciones operacion = (PublicadorWsOperaciones) (q.getSingleResult());
        return operacion;
    }

    public List<PublicadorWsParametrosOperaciones> consultaParametrosOperacion(Integer idOperacion) {
        Query q = mg.createNamedQuery("PublicadorWsParametrosOperaciones.findByIdOperacion", PublicadorWsParametrosOperaciones.class);
        q.setParameter("idOperacion", idOperacion);
        List<PublicadorWsParametrosOperaciones> publicadorWsParametrosOperaciones = q.getResultList();
        return publicadorWsParametrosOperaciones;
    }

    public List<PublicadorWsParametrosMetodosOperaciones> consultaParametrosMetodoOperacion(Integer idMetodo) {
        Query q = mg.createNamedQuery("PublicadorWsParametrosMetodosOperaciones.findByIdMetodoOperacion", PublicadorWsParametrosMetodosOperaciones.class);
        q.setParameter("idMetodoOperacion", idMetodo);
        List<PublicadorWsParametrosMetodosOperaciones> publicadorWsParametrosMetodosOperaciones = q.getResultList();
        return publicadorWsParametrosMetodosOperaciones;
    }

    public List<PublicadorWsParametrosToMapManagebean> consultarParametrosToMapManageBean(Integer idOperacion) {
        Query q = mg.createNamedQuery("PublicadorWsParametrosToMapManagebean.findByIdOperacion", PublicadorWsParametrosToMapManagebean.class);
        q.setParameter("idOperacion", idOperacion);
        List<PublicadorWsParametrosToMapManagebean> listParamToMapMB = q.getResultList();
        return listParamToMapMB;
    }

    public Map<Integer, List<PublicadorWsParametrosToMapManagebean>> consultarMapaParametrosToMapManageBean(Integer idOperacion) {
        Query q = mg.createNamedQuery("PublicadorWsParametrosToMapManagebean.findByIdOperacion", PublicadorWsParametrosToMapManagebean.class);
        q.setParameter("idOperacion", idOperacion);
        List<PublicadorWsParametrosToMapManagebean> listParamToMapMB = q.getResultList();
        Map<Integer, List<PublicadorWsParametrosToMapManagebean>> mapaParametrosToMapManagebean = new HashMap<>();

        for (PublicadorWsParametrosToMapManagebean param : listParamToMapMB) {
            Integer orden = param.getIdParametroOperacionWS().getOrdenParametro();
            if (!mapaParametrosToMapManagebean.containsKey(orden)) {
                mapaParametrosToMapManagebean.put(orden, new ArrayList<PublicadorWsParametrosToMapManagebean>());
            }
            mapaParametrosToMapManagebean.get(orden).add(param);
        }
        return mapaParametrosToMapManagebean;
    }

    public Map<Integer, List<PublicadorWsParametrosOperaciones>> consultaMapaParametrosOperacion(Integer idOperacion) {
        Query q = mg.createNamedQuery("PublicadorWsParametrosOperaciones.findByIdOperacion", PublicadorWsParametrosOperaciones.class);
        q.setParameter("idOperacion", idOperacion);
        List<PublicadorWsParametrosOperaciones> listParamToMapMB = q.getResultList();
        Map<Integer, List<PublicadorWsParametrosOperaciones>> mapaParametrosToMapManagebean = new HashMap<>();

        for (PublicadorWsParametrosOperaciones param : listParamToMapMB) {
            Integer orden = param.getOrdenParametro();
            if (!mapaParametrosToMapManagebean.containsKey(orden)) {
                mapaParametrosToMapManagebean.put(orden, new ArrayList<PublicadorWsParametrosOperaciones>());
            }
            mapaParametrosToMapManagebean.get(orden).add(param);
        }
        return mapaParametrosToMapManagebean;
    }

    /**
     *
     * @param idOperacion
     * @return
     */
    @Override
    public Map<PublicadorWsClases, List<PublicadorWsParametrosToMapManagebean>> consultarMapaParametrosToMapManageBeanIdClaseMB(Integer idOperacion) {
        Query q = mg.createNamedQuery("PublicadorWsParametrosToMapManagebean.findByIdOperacion", PublicadorWsParametrosToMapManagebean.class);
        q.setParameter("idOperacion", idOperacion);
        List<PublicadorWsParametrosToMapManagebean> listParamToMapMB = q.getResultList();
        Map<PublicadorWsClases, List<PublicadorWsParametrosToMapManagebean>> mapaParametrosToMapManagebean = new HashMap<>();

        for (PublicadorWsParametrosToMapManagebean param : listParamToMapMB) {
            // Integer orden = param.getIdParametroOperacionWS().getOrdenParametro();
            PublicadorWsClases idManageBean = param.getIdClaseManageBean();
            if (!mapaParametrosToMapManagebean.containsKey(idManageBean)) {
                mapaParametrosToMapManagebean.put(idManageBean, new ArrayList<PublicadorWsParametrosToMapManagebean>());
            }
            mapaParametrosToMapManagebean.get(idManageBean).add(param);
        }
        return mapaParametrosToMapManagebean;
    }

    @Override
    public List<PublicadorWsEjecucionOperacionCliente> consultarPublicWsEjecOperacionClienteByClient(Integer idOperacion) {
        Query q = mg.createNamedQuery("PublicadorWsEjecucionOperacionCliente.findByIdOperacion", PublicadorWsEjecucionOperacionCliente.class);
        q.setParameter("idOperacion", idOperacion);
        List<PublicadorWsEjecucionOperacionCliente> listPublicadorWsEjecucionOperacionCliente = q.getResultList();
        return listPublicadorWsEjecucionOperacionCliente;
    }
}
