/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.webservices;

/**
 *
 * @author T13237
 */
public class ParameterWSDynamicDTO {
    
    private final Integer orden;
    private final String alias;
    private final ClassDynamicWSDTO typeClase;

    public ParameterWSDynamicDTO(Integer orden,String alias, ClassDynamicWSDTO typoClase) {
        this.orden=orden;
        this.alias = alias;
        this.typeClase = typoClase;
        
    }

    public String getAlias() {
        return alias;
    }

    public ClassDynamicWSDTO getTypeClase() {
        return typeClase;
    }

    public Integer getOrden() {
        return orden;
    }
    
    
    
}
