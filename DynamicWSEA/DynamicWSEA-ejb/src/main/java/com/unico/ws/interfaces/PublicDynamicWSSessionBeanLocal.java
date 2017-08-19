/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.interfaces;

import com.unico.ws.dto.PuplishWSDTO;
import com.unico.ws.exception.DynamicWSException;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author T13237
 */
@Local
public interface PublicDynamicWSSessionBeanLocal {

    public List<PuplishWSDTO>  publicarWS() throws DynamicWSException;


    public List<PuplishWSDTO> deshabilitarWebServices(List<PuplishWSDTO> instancia);

    public List<PuplishWSDTO> habilitarWebServices(List<PuplishWSDTO> instancia)throws Exception;
    
    public void loadDynamicPublicWSContext();

}
