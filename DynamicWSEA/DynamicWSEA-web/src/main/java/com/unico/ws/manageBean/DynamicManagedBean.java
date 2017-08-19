/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.manageBean;

import com.unico.ws.entity.PublicadorWsOperaciones;
import com.unico.ws.example.DynamicSessionBeanLocal;
import com.unico.ws.helper.WSDynamicUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author T13237
 */
@ManagedBean
@SessionScoped
public class DynamicManagedBean {

    @EJB
    private DynamicSessionBeanLocal dynamicSessionBean;

    private String nombreResuldato;
    private Object employeeInst;

    /**
     * Creates a new instance of DynamicManagedBean
     */
    public DynamicManagedBean() {
    }

    public void cargarClaseDynamica() {

        employeeInst = dynamicSessionBean.cargarClaseDynamica("com.prueba.pojo.Employee");
        System.out.println("REalizacion del cargue exitosamente");

    }
    
    public String mostrarIndex(){
    
        return "";
    }
    
    public void consultarOperacionxId(){
        PublicadorWsOperaciones res = dynamicSessionBean.buscarOperacionWS(2);
        System.out.println(res.getNombreOperacion());
    }

  
    
    public String getNombreResuldato() {
        String resultado = "No te saludo!!";
        if (employeeInst != null) {
            //

            try {
                WSDynamicUtil.getInstance().invokeMethod(employeeInst, "com.prueba.pojo.Employee", new Class[]{String.class}, "setNombre", new String[]{"Harry"});
                resultado = "Hola mundo Reflexion " + (String) WSDynamicUtil.getInstance().invokeMethod(employeeInst, "com.prueba.pojo.Employee", new Class[]{}, "getNombre", null);
            } catch (Exception ex) {
                Logger.getLogger(DynamicManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            resultado = nombreResuldato==null?resultado:nombreResuldato;
        }
        return resultado;
    }

    public void setNombreResuldato(String nombreResuldato) {
        this.nombreResuldato = nombreResuldato;
    }

}
