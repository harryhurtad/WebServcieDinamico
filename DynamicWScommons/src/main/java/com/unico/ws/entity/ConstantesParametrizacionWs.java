/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author T13237
 */
@Entity
@Table(name = "CONSTANTES_PARAMETRIZACION_WS")
@NamedQueries({
    @NamedQuery(name = "ConstantesParametrizacionWs.findAll", query = "SELECT c FROM ConstantesParametrizacionWs c  WHERE c.habilitado=true"),
    @NamedQuery(name = "ConstantesParametrizacionWs.findByNombreParametro", query = "SELECT c FROM ConstantesParametrizacionWs c WHERE c.nombreParametro = :nombreParametro AND c.habilitado=true"),
    @NamedQuery(name = "ConstantesParametrizacionWs.findByValorParametro", query = "SELECT c FROM ConstantesParametrizacionWs c WHERE c.valorParametro = :valorParametro AND c.habilitado=true"),
    @NamedQuery(name = "ConstantesParametrizacionWs.findByHabilitado", query = "SELECT c FROM ConstantesParametrizacionWs c WHERE c.habilitado = :habilitado")})
public class ConstantesParametrizacionWs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_parametro")
    private String nombreParametro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "valor_parametro")
    private String valorParametro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habilitado")
    private boolean habilitado;

    public ConstantesParametrizacionWs() {
    }

    public ConstantesParametrizacionWs(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public ConstantesParametrizacionWs(String nombreParametro, String valorParametro, boolean habilitado) {
        this.nombreParametro = nombreParametro;
        this.valorParametro = valorParametro;
        this.habilitado = habilitado;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public String getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreParametro != null ? nombreParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConstantesParametrizacionWs)) {
            return false;
        }
        ConstantesParametrizacionWs other = (ConstantesParametrizacionWs) object;
        if ((this.nombreParametro == null && other.nombreParametro != null) || (this.nombreParametro != null && !this.nombreParametro.equals(other.nombreParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.entity.ConstantesParametrizacionWs[ nombreParametro=" + nombreParametro + " ]";
    }
    
}
