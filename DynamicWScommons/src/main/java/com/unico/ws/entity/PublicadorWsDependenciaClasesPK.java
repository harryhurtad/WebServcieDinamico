/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author T13237
 */
@Embeddable
public class PublicadorWsDependenciaClasesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idClassDependencia")
    private int idClassDependencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idClase")
    private int idClase;

    public PublicadorWsDependenciaClasesPK() {
    }

    public PublicadorWsDependenciaClasesPK(int idClassDependencia, int idClase) {
        this.idClassDependencia = idClassDependencia;
        this.idClase = idClase;
    }

    public int getIdClassDependencia() {
        return idClassDependencia;
    }

    public void setIdClassDependencia(int idClassDependencia) {
        this.idClassDependencia = idClassDependencia;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idClassDependencia;
        hash += (int) idClase;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicadorWsDependenciaClasesPK)) {
            return false;
        }
        PublicadorWsDependenciaClasesPK other = (PublicadorWsDependenciaClasesPK) object;
        if (this.idClassDependencia != other.idClassDependencia) {
            return false;
        }
        if (this.idClase != other.idClase) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.exception.PublicadorWsDependenciaClasesPK[ idClassDependencia=" + idClassDependencia + ", idClase=" + idClase + " ]";
    }
    
}
