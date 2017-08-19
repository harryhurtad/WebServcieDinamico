/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.object;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author T13237
 */
public class RequestDynamicWSJMS implements Serializable {
    private final String requestId;
    private final Integer idOperacion;
    private final String contextWebAppCLient;
    private final Map<Integer,RequestParamDynamicJMS> mapParamsOperationWS;

    public RequestDynamicWSJMS(String requestId, Integer idOperacion, String contextWebAppCLient) {
        this.requestId = requestId;
        this.idOperacion = idOperacion;
        this.contextWebAppCLient = contextWebAppCLient;
        mapParamsOperationWS=new HashMap<Integer, RequestParamDynamicJMS>();
    }

    public Map<Integer, RequestParamDynamicJMS> getMapParamsOperationWS() {
        return mapParamsOperationWS;
    }
    
    

    public String getRequestId() {
        return requestId;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public String getContextWebAppCLient() {
        return contextWebAppCLient;
    }
    
    
}
