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
public class OperationManageBeanWS {
    
    private int idOperation;
    private String nombreBackingBean;
    private String alias;
    private OperationMethodManageBeanWS[] operaciones;
    private OperationMapToField[] fields;

    public int getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(int idOperation) {
        this.idOperation = idOperation;
    }

    public OperationMethodManageBeanWS[] getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(OperationMethodManageBeanWS[] operaciones) {
        this.operaciones = operaciones;
    }

    public OperationMapToField[] getFields() {
        return fields;
    }

    public void setFields(OperationMapToField[] fields) {
        this.fields = fields;
    }

    public String getNombreBackingBean() {
        return nombreBackingBean;
    }

    public void setNombreBackingBean(String nombreBackingBean) {
        this.nombreBackingBean = nombreBackingBean;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    
    
}
