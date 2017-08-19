/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.operation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author T13237
 */
public class OperationRequestWS implements Serializable {
    
    private final Map<Integer,OperationRequestParamWS> mapParamsRequest;
    private final String requestId;
    private final Integer idOperacion;
    private final String contextWebAppCLient;
    
    
    public OperationRequestWS(String requestId,Integer idOperacion,String contextWebAppCLient){
        mapParamsRequest=new HashMap<>();
        this.requestId=requestId;
        this.idOperacion=idOperacion;
        this.contextWebAppCLient=contextWebAppCLient;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getContextWebAppCLient() {
        return contextWebAppCLient;
    }

    
    
    public Map<Integer, OperationRequestParamWS> getMapParamsRequest() {
        return mapParamsRequest;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }
    
    
    
}
