/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;

import com.unico.ws.ejb.PublicDynamicWSSessionBean;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author T13237
 */
public class DynamicWSContextSingleton {
    private static DynamicWSContextSingleton instance;
    private final Map<String,String> mapDynamicWSParmas;
   

    private DynamicWSContextSingleton() {
        mapDynamicWSParmas=new HashMap<>();
      
    }

    public static DynamicWSContextSingleton getInstance() {

        if (instance == null) {
            instance = new DynamicWSContextSingleton();
        }
        return instance;
    }

    public Map<String, String> getMapDynamicWSParmas() {
        return mapDynamicWSParmas;
    }
    
}
