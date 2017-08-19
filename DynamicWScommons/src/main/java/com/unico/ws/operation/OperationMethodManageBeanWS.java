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
public class OperationMethodManageBeanWS {
    
    private String nombreClase; 
    private String nombreMetodo;
    private OperationParamMthManageBean[] parameters;

    public OperationMethodManageBeanWS(String nombreClase,  String nombreMetodo, OperationParamMthManageBean[] parameters) {
        this.nombreClase = nombreClase;
        
        this.nombreMetodo = nombreMetodo;
        this.parameters = parameters;
    }

   

    
    
    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }

    public OperationParamMthManageBean[] getParameters() {
        return parameters;
    }

    public void setParameters(OperationParamMthManageBean[] parameters) {
        this.parameters = parameters;
    }

   
    
    
}
