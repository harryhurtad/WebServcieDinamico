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
public class MethodToPublishWSDTO {
    private final Integer idMethod;
    private final  String nombreMetodo;
    private final String nombreCLassRetorno;
    private final ParameterWSDynamicDTO[] listaParametros;
    

    public MethodToPublishWSDTO(Integer idMethod, String nombreMetodo, String nombreCLassRetorno, ParameterWSDynamicDTO[] listaParametros) {
        this.idMethod = idMethod;
        this.nombreMetodo = nombreMetodo;
        this.nombreCLassRetorno = nombreCLassRetorno;
        this.listaParametros = listaParametros;
    }

    
    
    
    public Integer getIdMethod() {
        return idMethod;
    }

   

    public String getNombreMetodo() {
        return nombreMetodo;
    }

 

    public String getNombreCLassRetorno() {
        return nombreCLassRetorno;
    }

   

    public ParameterWSDynamicDTO[] getListaParametros() {
        return listaParametros;
    }

   
    
    
    
    
}
