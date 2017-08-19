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
public class InterfaceToPublishDTO {
    private final ClassDynamicWSDTO[] listImports;
    private final String package_ws;
    private final String nameInterface;
   private final String targetNamespace;
    private final List<MethodToPublishWSDTO> listaOperaciones;

    public InterfaceToPublishDTO(ClassDynamicWSDTO[] listImports, String package_ws, String nameInterface, String targetNamespace, List<MethodToPublishWSDTO> listaOperaciones) {
        this.listImports = listImports;
        this.package_ws = package_ws;
        this.nameInterface = nameInterface;
        this.targetNamespace = targetNamespace;
        this.listaOperaciones = listaOperaciones;
    }

    

    public ClassDynamicWSDTO[] getListImports() {
        return listImports;
    }

    public String getPackage_ws() {
        return package_ws;
    }

    public String getNameInterface() {
        return nameInterface;
    }

    public List<MethodToPublishWSDTO> getListaOperaciones() {
        return listaOperaciones;
    }

    public String getTargetNamespace() {
        return targetNamespace;
    }
      
      
}
