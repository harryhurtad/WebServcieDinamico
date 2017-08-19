/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.interfaces;

import com.unico.ws.constantes.StatusExecutionWSEnum;
import com.unico.ws.entity.PublicadorWsEstadoInvocOper;
import javax.ejb.Remote;

/**
 *
 * @author T13237
 */
@Remote
public interface DynamicWSExecutionStatusBeanRemote {

    public void updatePublicadorWsEstadoInvocOper(String idRequest, StatusExecutionWSEnum estado, String excepcion);

    public PublicadorWsEstadoInvocOper findPublicadorWsEstadoInvocOperByRequest(String idRequest);

    public void createNewPublicadorWsEstadoInvocOper(Integer idOperacion, String idRequest);
    
}
