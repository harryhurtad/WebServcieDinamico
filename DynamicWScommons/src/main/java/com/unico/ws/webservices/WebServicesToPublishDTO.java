/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.webservices;

import java.util.List;

/**
 *
 * @author T13237
 */
public class WebServicesToPublishDTO {
    
  
    private final String portName;
    private final String serviceName;
    private final String targetNamespace;
    private final String endpointInterface;
    private final String package_ws;
    private final String nameInterface;
    private final String URLToPublish;
    private final List<MethodToPublishWSDTO> listaOperaciones;
    private final ClassDynamicWSDTO[] listImports;

    public WebServicesToPublishDTO(String serviceName, String portName,  String targetNamespace, String endpointInterface, String package_ws,String nameInterface,List<MethodToPublishWSDTO>listaOperaciones,String URLToPublish,ClassDynamicWSDTO[] listImports) {
        this.serviceName = serviceName;
        this.portName = portName;
      
        this.targetNamespace = targetNamespace;
        this.endpointInterface = endpointInterface;
        this.package_ws = package_ws;
        this.listaOperaciones=listaOperaciones;
        this.nameInterface=nameInterface;
        this.listImports=listImports;
        this.URLToPublish=URLToPublish;
    }

    public String getNameInterface() {
        return nameInterface;
    }

    
    public String getPortName() {
        return portName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getTargetNamespace() {
        return targetNamespace;
    }

    public String getEndpointInterface() {
        return endpointInterface;
    }

    public String getPackage_ws() {
        return package_ws;
    }

    public List<MethodToPublishWSDTO> getListaOperaciones() {
        return listaOperaciones;
    }

    public ClassDynamicWSDTO[] getListImports() {
        return listImports;
    }

    public String getURLToPublish() {
        return URLToPublish;
    }
    
    
    
    
    
}
