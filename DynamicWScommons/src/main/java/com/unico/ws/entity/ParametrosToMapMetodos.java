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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author T13237
 */
@Entity
@Table(name = "PARAMETROS_TO_MAP_METODOS")
@NamedQueries({
    @NamedQuery(name = "ParametrosToMapMetodos.findAll", query = "SELECT p FROM ParametrosToMapMetodos p"),
    @NamedQuery(name = "ParametrosToMapMetodos.findByIdParamToMapMetodo", query = "SELECT p FROM ParametrosToMapMetodos p WHERE p.idParamToMapMetodo = :idParamToMapMetodo"),
    @NamedQuery(name = "ParametrosToMapMetodos.findByNombreParametroGetWS", query = "SELECT p FROM ParametrosToMapMetodos p WHERE p.nombreParametroGetWS = :nombreParametroGetWS")})
public class ParametrosToMapMetodos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idParamToMapMetodo")
    private Integer idParamToMapMetodo;
    @Size(max = 100)
    @Column(name = "nombreParametroGetWS")
    private String nombreParametroGetWS;
    @JoinColumn(name = "idParametroOperacionWS", referencedColumnName = "idParametroOperacion")
    @ManyToOne
    private PublicadorWsParametrosOperaciones idParametroOperacionWS;
    @JoinColumn(name = "idParametroOperacion", referencedColumnName = "idParametroOperacion")
    @OneToOne
    private PublicadorWsParametrosMetodosOperaciones idParametroOperacion;
    @JoinColumn(name = "idResultEjecInstClass", referencedColumnName = "idEjecucionOperacion")
    @ManyToOne
    private PublicadorWsEjecucionOperacionCliente idResultEjecInstClass;

    public ParametrosToMapMetodos() {
    }

    public ParametrosToMapMetodos(Integer idParamToMapMetodo) {
        this.idParamToMapMetodo = idParamToMapMetodo;
    }

    public Integer getIdParamToMapMetodo() {
        return idParamToMapMetodo;
    }

    public void setIdParamToMapMetodo(Integer idParamToMapMetodo) {
        this.idParamToMapMetodo = idParamToMapMetodo;
    }

    public String getNombreParametroGetWS() {
        return nombreParametroGetWS;
    }

    public void setNombreParametroGetWS(String nombreParametroGetWS) {
        this.nombreParametroGetWS = nombreParametroGetWS;
    }

    public PublicadorWsParametrosOperaciones getIdParametroOperacionWS() {
        return idParametroOperacionWS;
    }

    public void setIdParametroOperacionWS(PublicadorWsParametrosOperaciones idParametroOperacionWS) {
        this.idParametroOperacionWS = idParametroOperacionWS;
    }

    public PublicadorWsParametrosMetodosOperaciones getIdParametroOperacion() {
        return idParametroOperacion;
    }

    public void setIdParametroOperacion(PublicadorWsParametrosMetodosOperaciones idParametroOperacion) {
        this.idParametroOperacion = idParametroOperacion;
    }

  

  
    public PublicadorWsEjecucionOperacionCliente getIdResultEjecInstClass() {
        return idResultEjecInstClass;
    }

    public void setIdResultEjecInstClass(PublicadorWsEjecucionOperacionCliente idResultEjecInstClass) {
        this.idResultEjecInstClass = idResultEjecInstClass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParamToMapMetodo != null ? idParamToMapMetodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosToMapMetodos)) {
            return false;
        }
        ParametrosToMapMetodos other = (ParametrosToMapMetodos) object;
        if ((this.idParamToMapMetodo == null && other.idParamToMapMetodo != null) || (this.idParamToMapMetodo != null && !this.idParamToMapMetodo.equals(other.idParamToMapMetodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.entity.ParametrosToMapMetodos[ idParamToMapMetodo=" + idParamToMapMetodo + " ]";
    }
    
}
