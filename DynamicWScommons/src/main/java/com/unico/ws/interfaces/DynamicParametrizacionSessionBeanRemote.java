/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.interfaces;

import com.unico.ws.entity.ConstantesParametrizacionWs;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author T13237
 */
@Remote
public interface DynamicParametrizacionSessionBeanRemote {


  
   
    public String buscarNombreContextoCliente(String nombreWS);

    public List<ConstantesParametrizacionWs> buscarParametrizacionDynamicWS();

    public ConstantesParametrizacionWs buscarParametroPorNombre(String nobmreParametro);

  
    
 
}
