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
public class OperationParamMthManageBean {
    
    private String nombreParametro;
    private Class tipoDato;

    public OperationParamMthManageBean(String nombreParametro, Class tipoDato) {
        this.nombreParametro = nombreParametro;
        this.tipoDato = tipoDato;
    }

    public OperationParamMthManageBean(){
    }
    
    
    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public Class getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(Class tipoDato) {
        this.tipoDato = tipoDato;
    }
    
    
}
