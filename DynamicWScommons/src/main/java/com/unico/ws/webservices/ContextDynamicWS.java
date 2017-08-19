/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.webservices;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author T13237
 */
public class ContextDynamicWS {
    
    private static ContextDynamicWS instance;
    private final Map<Integer,WebServicesToPublishDTO> mapWebServicesToPuplish;
    
    private ContextDynamicWS(){
        mapWebServicesToPuplish=new HashMap();
    }
    
    public static ContextDynamicWS getInstance(){
    
        if(instance==null){
            instance=new ContextDynamicWS();
        }
        
        return instance;
    }

    public Map<Integer, WebServicesToPublishDTO> getMapWebServicesToPuplish() {
        return mapWebServicesToPuplish;
    }
    
}
