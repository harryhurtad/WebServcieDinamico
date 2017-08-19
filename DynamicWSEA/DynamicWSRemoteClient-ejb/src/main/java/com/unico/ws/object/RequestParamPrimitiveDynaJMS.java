/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.object;

import java.io.Serializable;

/**
 *
 * @author T13237
 */
public class RequestParamPrimitiveDynaJMS implements Serializable,RequestParamDynamicJMS {
    
    private Object parameter;

    public RequestParamPrimitiveDynaJMS(Object parameter) {
        this.parameter = parameter;
    }

    
    
    public Object getParameter() {
        return parameter;
    }
    
    
}
