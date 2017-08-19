/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.operation;

import java.io.Serializable;

/**
 *
 * @author T13237
 */
public class OperationRequestParamWS implements Serializable {
    private final String nameClass;
    private final Object instance;

    public OperationRequestParamWS(String nameClass, Object instance) {
        this.nameClass = nameClass;
        this.instance = instance;
    }

    
    
    public String getNameClass() {
        return nameClass;
    }

    public Object getInstance() {
        return instance;
    }
    
    
}
