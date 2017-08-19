/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.example;

import com.unico.ws.entity.PublicadorWsOperaciones;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author T13237
 */
@Local
public interface DynamicSessionBeanLocal {

    public Object cargarClaseDynamica(String nameClass);

    public void realizarSumaRemota(Object instance);

    public List<PublicadorWsOperaciones> buscarOperacionesWS();

    public void setOperacionWS(PublicadorWsOperaciones operacion);

    public PublicadorWsOperaciones buscarOperacionWS(Integer idOperacion);

}
