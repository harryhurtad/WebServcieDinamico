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
public class ReqParamsComplexObjDynaJMS implements Serializable,RequestParamDynamicJMS{
    private final byte[] instanceSerializable;
    private final String[] listClassToLoad; 

    public ReqParamsComplexObjDynaJMS(byte[] instanceSerializable, String[] listClassToLoad) {
        this.instanceSerializable = instanceSerializable;
        this.listClassToLoad = listClassToLoad;
    }
    
    
    
    public byte[] getInstanceSerializable() {
        return instanceSerializable;
    }

    public String[] getListClassToLoad() {
        return listClassToLoad;
    }
    
    
    
            
}
