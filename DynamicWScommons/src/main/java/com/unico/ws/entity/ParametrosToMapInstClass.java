/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "PARAMETROS_TO_MAP_INST_CLASS")
@NamedQueries({
    @NamedQuery(name = "ParametrosToMapInstClass.findAll", query = "SELECT p FROM ParametrosToMapInstClass p"),
    @NamedQuery(name = "ParametrosToMapInstClass.findByIdParamToMapInstClass", query = "SELECT p FROM ParametrosToMapInstClass p WHERE p.idParamToMapInstClass = :idParamToMapInstClass"),
    @NamedQuery(name = "ParametrosToMapInstClass.findByNombreParametroGetWS", query = "SELECT p FROM ParametrosToMapInstClass p WHERE p.nombreParametroGetWS = :nombreParametroGetWS")})
public class ParametrosToMapInstClass implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idParamToMapInstClass")
    private Integer idParamToMapInstClass;
    @Size(max = 100)
    @Column(name = "nombreParametroGetWS")
    private String nombreParametroGetWS;
    @JoinColumn(name = "idParametroOperacionWS", referencedColumnName = "idParametroOperacion")
    @ManyToOne(optional = false)
    private PublicadorWsParametrosOperaciones idParametroOperacionWS;
    @JoinColumn(name = "idEjecucionOperacion", referencedColumnName = "idEjecucionOperacion")
    @ManyToOne(optional = false)
    private PublicadorWsEjecucionOperacionCliente idEjecucionOperacion;
    @JoinColumn(name = "idAtributoToSetValueClass", referencedColumnName = "idAtributo")
    @ManyToOne(optional = false,cascade = CascadeType.REFRESH)
    private PublicadorWsClasesAtributos idAtributoToSetValueClass;

    public ParametrosToMapInstClass() {
    }

    public ParametrosToMapInstClass(Integer idParamToMapInstClass) {
        this.idParamToMapInstClass = idParamToMapInstClass;
    }

    public Integer getIdParamToMapInstClass() {
        return idParamToMapInstClass;
    }

    public void setIdParamToMapInstClass(Integer idParamToMapInstClass) {
        this.idParamToMapInstClass = idParamToMapInstClass;
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

    public PublicadorWsEjecucionOperacionCliente getIdEjecucionOperacion() {
        return idEjecucionOperacion;
    }

    public void setIdEjecucionOperacion(PublicadorWsEjecucionOperacionCliente idEjecucionOperacion) {
        this.idEjecucionOperacion = idEjecucionOperacion;
    }

    public PublicadorWsClasesAtributos getIdAtributoToSetValueClass() {
        return idAtributoToSetValueClass;
    }

    public void setIdAtributoToSetValueClass(PublicadorWsClasesAtributos idAtributoToSetValueClass) {
        this.idAtributoToSetValueClass = idAtributoToSetValueClass;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParamToMapInstClass != null ? idParamToMapInstClass.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosToMapInstClass)) {
            return false;
        }
        ParametrosToMapInstClass other = (ParametrosToMapInstClass) object;
        if ((this.idParamToMapInstClass == null && other.idParamToMapInstClass != null) || (this.idParamToMapInstClass != null && !this.idParamToMapInstClass.equals(other.idParamToMapInstClass))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.entity.ParametrosToMapInstClass[ idParamToMapInstClass=" + idParamToMapInstClass + " ]";
    }
    
}
