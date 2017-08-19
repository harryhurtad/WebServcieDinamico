/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.interfaces;

import com.unico.ws.entity.PublicadorWsClases;
import com.unico.ws.entity.PublicadorWsEjecucionOperacionCliente;
import com.unico.ws.entity.PublicadorWsMetodosOperaciones;
import com.unico.ws.entity.PublicadorWsOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosMetodosOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosToMapManagebean;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author T13257
 */
@Remote
public interface EjecucionOperClienteSessionBeanRemote {

    public Integer consultaIdOperacion(String nombre);

    public PublicadorWsOperaciones consultaOperacion(Integer idOperacion);

    public List<PublicadorWsParametrosOperaciones> consultaParametrosOperacion(Integer idOperacion);

    public List<PublicadorWsParametrosMetodosOperaciones> consultaParametrosMetodoOperacion(Integer idMetodo);

    public List<PublicadorWsParametrosToMapManagebean> consultarParametrosToMapManageBean(Integer idOperacion);

    public Map<Integer, List<PublicadorWsParametrosToMapManagebean>> consultarMapaParametrosToMapManageBean(Integer idOperacion);

    public Map<PublicadorWsClases, List<PublicadorWsParametrosToMapManagebean>> consultarMapaParametrosToMapManageBeanIdClaseMB(Integer idOperacion);

    public List<PublicadorWsEjecucionOperacionCliente> consultarPublicWsEjecOperacionClienteByClient(Integer idOperacion);

}
