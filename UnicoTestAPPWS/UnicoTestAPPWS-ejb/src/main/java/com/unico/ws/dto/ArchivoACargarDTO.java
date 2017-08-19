/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.dto;

/**
 *
 * @author T13237
 */
public class ArchivoACargarDTO {
    
    private String nombreArchivo;
    private int tipoArchivoSeleccionado;

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public int getTipoArchivoSeleccionado() {
        return tipoArchivoSeleccionado;
    }

    public void setTipoArchivoSeleccionado(int tipoArchivoSeleccionado) {
        this.tipoArchivoSeleccionado = tipoArchivoSeleccionado;
    }
 
    
    
}
