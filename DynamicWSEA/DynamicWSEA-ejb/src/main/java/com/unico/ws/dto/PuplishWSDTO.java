/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.dto;

import java.util.Objects;
import javax.xml.ws.Endpoint;

/**
 *
 * @author T13237
 */
public class PuplishWSDTO {
    
    private Integer idWS;
    private String nombreWS;
    private String URL;
    private Endpoint endpoint;
    private boolean habilitado;

    public PuplishWSDTO(Integer idWS, String nombreWS, String URL, Endpoint endpoint, boolean habilitado) {
        this.idWS = idWS;
        this.nombreWS = nombreWS;
        this.URL = URL;
        this.endpoint = endpoint;
        this.habilitado = habilitado;
    }

   

    public String getNombreWS() {
        return nombreWS;
    }

    public void setNombreWS(String nombreWS) {
        this.nombreWS = nombreWS;
    }
    
    
    
    
   // private

    public Integer getIdWS() {
        return idWS;
    }

    public void setIdWS(Integer idWS) {
        this.idWS = idWS;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.idWS);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PuplishWSDTO other = (PuplishWSDTO) obj;
        if (!Objects.equals(this.idWS, other.idWS)) {
            return false;
        }
        return true;
    }
    
    
    
}
