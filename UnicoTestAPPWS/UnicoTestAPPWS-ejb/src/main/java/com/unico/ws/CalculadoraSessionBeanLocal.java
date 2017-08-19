/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws;

import javax.ejb.Local;

/**
 *
 * @author T13237
 */
@Local
public interface CalculadoraSessionBeanLocal {
    
    Integer sumar(Integer dato1,Integer dato2);
    Integer restar(Integer dato1,Integer dato2);
    Integer multiplicar(Integer dato1,Integer dato2);
    Integer dividir(Integer dato1,Integer dato2);
}
