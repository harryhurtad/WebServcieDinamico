/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.interfaces;

import com.unico.ws.dto.PuplishWSDTO;
import java.util.Map;

/**
 *
 * @author T13237
 */
public interface DynamicWSContextSingletonBeanLocal {

    public String getDirectorioSalida();

    public String getDirectorioFuentes();

    public String getPortWS();

    public String getHostWS();

    public Map<Integer, PuplishWSDTO> getMapPuplishWSDTO();

    public void setMapPuplishWSDTO(Map<Integer, PuplishWSDTO> mapPuplishWSDTO);

    public void init();
    
}
