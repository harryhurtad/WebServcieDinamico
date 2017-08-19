/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.webservice;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author T13237
 */
public class OperacionDTO implements Serializable {

    private final static long serialVersionUID = 12343L;
    protected String mensaje;
    protected BigInteger dato1;
    protected BigInteger dato2;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public BigInteger getDato1() {
        return dato1;
    }

    public void setDato1(BigInteger dato1) {
        this.dato1 = dato1;
    }

    public BigInteger getDato2() {
        return dato2;
    }

    public void setDato2(BigInteger dato2) {
        this.dato2 = dato2;
    }

    
    
}
