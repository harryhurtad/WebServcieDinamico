/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.webservices;

import java.io.File;

/**
 *
 * @author T13237
 */
public class ClassDynamicWSDTO {
    private final Integer id;
    private final String nombreCLase;
    private final String simpleName;
    //Temporal
    private final File rutaDefXSD;
    private final String[] classDependence;

    public ClassDynamicWSDTO(Integer id, String nombreCLase, String simpleName, File rutaDefXSD, String[] classDependence) {
        this.id = id;
        this.nombreCLase = nombreCLase;
        this.simpleName = simpleName;
        this.rutaDefXSD = rutaDefXSD;
        this.classDependence = classDependence;
    }
    
    

 

    public Integer getId() {
        return id;
    }

    public String getNombreCLase() {
        return nombreCLase;
    }

    public File getRutaDefXSD() {
        return rutaDefXSD;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public String[] getClassDependence() {
        return classDependence;
    }

   
    
    
    
}
