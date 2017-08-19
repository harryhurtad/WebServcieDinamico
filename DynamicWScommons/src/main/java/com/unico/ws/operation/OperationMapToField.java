/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.operation;

/**
 *
 * @author T13237
 */
public class OperationMapToField {
    private String nombreClaseWS;
    private Class typeFieldClaseWS;
    private String nombreGetFieldWS;    
    private String nombreSetFieldBackingBean;
    private Class typeFieldBackingBean;

    public OperationMapToField(String nombreClaseWS, Class typeFieldClaseWS, String nombreFieldWS, String nombreFieldBackingBean, Class typeFieldBackingBean) {
        this.nombreClaseWS = nombreClaseWS;
        this.typeFieldClaseWS = typeFieldClaseWS;
        this.nombreGetFieldWS = nombreFieldWS;
        this.nombreSetFieldBackingBean = nombreFieldBackingBean;
        this.typeFieldBackingBean = typeFieldBackingBean;
    }

   
    

    
    
    public String getNombreClaseWS() {
        return nombreClaseWS;
    }

    public void setNombreClaseWS(String nombreClaseWS) {
        this.nombreClaseWS = nombreClaseWS;
    }

    public String getNombreGetFieldWS() {
        return nombreGetFieldWS;
    }

    public void setNombreGetFieldWS(String nombreGetFieldWS) {
        this.nombreGetFieldWS = nombreGetFieldWS;
    }

 

    public String getNombreSetFieldBackingBean() {
        return nombreSetFieldBackingBean;
    }

    public void setNombreSetFieldBackingBean(String nombreSetFieldBackingBean) {
        this.nombreSetFieldBackingBean = nombreSetFieldBackingBean;
    }

    public Class getTypeFieldClaseWS() {
        return typeFieldClaseWS;
    }

    public void setTypeFieldClaseWS(Class typeFieldClaseWS) {
        this.typeFieldClaseWS = typeFieldClaseWS;
    }

    public Class getTypeFieldBackingBean() {
        return typeFieldBackingBean;
    }

    public void setTypeFieldBackingBean(Class typeFieldBackingBean) {
        this.typeFieldBackingBean = typeFieldBackingBean;
    }
    
    
    
    
}
