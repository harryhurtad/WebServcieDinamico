/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.mb;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author T13237
 */
@ManagedBean
@ViewScoped
public class CalculadoraJSFManagedBean implements Serializable {

    private Integer dato1;
    private Integer dato2;
    private Integer idOperacion;
    @ManagedProperty(value = "#{loginJSFManagedBean}")
    private LoginJSFManagedBean loginBean;

    @EJB
    private com.unico.ws.CalculadoraSessionBeanLocal calculadoraSessionBean;

    /**
     * Creates a new instance of CalculadoraJSFManagedBean
     */
    @PostConstruct
    public void init() {
        dato1 = 10;
        dato2 = 20;
        System.out.println("valor dato1: " + dato1);
        System.out.println("valor dato2: " + dato2);
    }

    public CalculadoraJSFManagedBean() {
    }

    public void realizarSuma() {
        Integer resultado = calculadoraSessionBean.sumar(dato1, dato2);
        System.out.println("El resultado de la suma es :" + resultado);
    }

    public void realizarResta() {
        Integer resultado = calculadoraSessionBean.restar(dato1, dato2);
        System.out.println("El resultado de la resta es :" + resultado);
    }

    public void realizarMultiplicacion() {
        Integer resultado = calculadoraSessionBean.multiplicar(dato1, dato2);
        System.out.println("El resultado de la multiplicacion es :" + resultado);
    }

    public void realizarDivicion() {
        Integer resultado = calculadoraSessionBean.dividir(dato1, dato2);
        System.out.println("El resultado de la divicion es :" + resultado);
    }

    public void selectionOption(ValueChangeEvent event) {

        idOperacion = (Integer) event.getNewValue();
    }

    public void realizarCalculoOperacion() {
        if (loginBean.getUsuarioLogeado() != null && loginBean.getUsuarioLogeado().getFehcaInicioSesion() != null) {
            switch (idOperacion) {
                case 1:
                    realizarSuma();
                    break;
                case 2:
                    realizarResta();
                    break;
                case 3:
                    realizarMultiplicacion();
                    break;

            }
        }else{
            System.out.println("No se realiza la operacion Calculadora!!");
        }
    }

    public Integer getDato1() {
        return dato1;
    }

    public void setDato1(Integer dato1) {
        this.dato1 = dato1;
    }

    public Integer getDato2() {
        return dato2;
    }

    public void setDato2(Integer dato2) {
        this.dato2 = dato2;
    }

    public LoginJSFManagedBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginJSFManagedBean loginBean) {
        this.loginBean = loginBean;
    }

    
    
}
