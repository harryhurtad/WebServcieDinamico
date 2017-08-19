/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author T13237
 */
@Entity
@Table(name = "PUBLICADOR_WS_DEPENDENCIA_CLASES")
@NamedQueries({
    @NamedQuery(name = "PublicadorWsDependenciaClases.findAll", query = "SELECT p FROM PublicadorWsDependenciaClases p"),
    @NamedQuery(name = "PublicadorWsDependenciaClases.findByIdClassDependencia", query = "SELECT p FROM PublicadorWsDependenciaClases p WHERE p.publicadorWsDependenciaClasesPK.idClassDependencia = :idClassDependencia"),
    @NamedQuery(name = "PublicadorWsDependenciaClases.findByIdClase", query = "SELECT p FROM PublicadorWsDependenciaClases p WHERE p.publicadorWsDependenciaClasesPK.idClase = :idClase"),
    @NamedQuery(name = "PublicadorWsDependenciaClases.findByOrdenCompilacion", query = "SELECT p FROM PublicadorWsDependenciaClases p WHERE p.ordenCompilacion = :ordenCompilacion")})
public class PublicadorWsDependenciaClases implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PublicadorWsDependenciaClasesPK publicadorWsDependenciaClasesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ordenCompilacion")
    private int ordenCompilacion;
    
    @JoinColumn(name = "idClassDependencia", referencedColumnName = "idClase", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PublicadorWsClases publicadorWsClasesDependencia;

    @JoinColumn(name = "idClase", referencedColumnName = "idClase", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PublicadorWsClases publicadorWsClasesPrincipal;

    public PublicadorWsDependenciaClases() {
    }

    public PublicadorWsDependenciaClases(PublicadorWsDependenciaClasesPK publicadorWsDependenciaClasesPK) {
        this.publicadorWsDependenciaClasesPK = publicadorWsDependenciaClasesPK;
    }

    public PublicadorWsDependenciaClases(PublicadorWsDependenciaClasesPK publicadorWsDependenciaClasesPK, int ordenCompilacion) {
        this.publicadorWsDependenciaClasesPK = publicadorWsDependenciaClasesPK;
        this.ordenCompilacion = ordenCompilacion;
    }

    public PublicadorWsDependenciaClases(int idClassDependencia, int idClase) {
        this.publicadorWsDependenciaClasesPK = new PublicadorWsDependenciaClasesPK(idClassDependencia, idClase);
    }

    public PublicadorWsDependenciaClasesPK getPublicadorWsDependenciaClasesPK() {
        return publicadorWsDependenciaClasesPK;
    }

    public void setPublicadorWsDependenciaClasesPK(PublicadorWsDependenciaClasesPK publicadorWsDependenciaClasesPK) {
        this.publicadorWsDependenciaClasesPK = publicadorWsDependenciaClasesPK;
    }

    public int getOrdenCompilacion() {
        return ordenCompilacion;
    }

    public void setOrdenCompilacion(int ordenCompilacion) {
        this.ordenCompilacion = ordenCompilacion;
    }

 
    public PublicadorWsClases getPublicadorWsClasesDependencia() {
        return publicadorWsClasesDependencia;
    }

    public void setPublicadorWsClasesDependencia(PublicadorWsClases publicadorWsClasesDependencia) {
        this.publicadorWsClasesDependencia = publicadorWsClasesDependencia;
    }

    public PublicadorWsClases getPublicadorWsClasesPrincipal() {
        return publicadorWsClasesPrincipal;
    }

    public void setPublicadorWsClasesPrincipal(PublicadorWsClases publicadorWsClasesPrincipal) {
        this.publicadorWsClasesPrincipal = publicadorWsClasesPrincipal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (publicadorWsDependenciaClasesPK != null ? publicadorWsDependenciaClasesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicadorWsDependenciaClases)) {
            return false;
        }
        PublicadorWsDependenciaClases other = (PublicadorWsDependenciaClases) object;
        if ((this.publicadorWsDependenciaClasesPK == null && other.publicadorWsDependenciaClasesPK != null) || (this.publicadorWsDependenciaClasesPK != null && !this.publicadorWsDependenciaClasesPK.equals(other.publicadorWsDependenciaClasesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.exception.PublicadorWsDependenciaClases[ publicadorWsDependenciaClasesPK=" + publicadorWsDependenciaClasesPK + " ]";
    }
    
}
