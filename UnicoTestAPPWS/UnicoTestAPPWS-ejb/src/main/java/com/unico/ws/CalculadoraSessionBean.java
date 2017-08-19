/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws;

import javax.ejb.Stateless;

/**
 *
 * @author T13237
 */
@Stateless
public class CalculadoraSessionBean implements CalculadoraSessionBeanLocal {

    @Override
    public Integer sumar(Integer dato1, Integer dato2) {
        return dato1+dato2;
    }

    @Override
    public Integer restar(Integer dato1, Integer dato2) {
         return dato1-dato2;
    }

    @Override
    public Integer multiplicar(Integer dato1, Integer dato2) {
         return dato1*dato2;
    }

    @Override
    public Integer dividir(Integer dato1, Integer dato2) {
         return dato1/dato2;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
}
