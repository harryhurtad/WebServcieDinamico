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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author T13237
 */
@Entity
@Table(name = "METODOS_OPERACIONES_JSF")
@NamedQueries({
    @NamedQuery(name = "MetodosOperacionesJsf.findAll", query = "SELECT m FROM MetodosOperacionesJsf m"),
    @NamedQuery(name = "MetodosOperacionesJsf.findByIdMetodoOperacionJSF", query = "SELECT m FROM MetodosOperacionesJsf m WHERE m.idMetodoOperacionJSF = :idMetodoOperacionJSF"),
    @NamedQuery(name = "MetodosOperacionesJsf.findByNombreGetAtributoParamWS", query = "SELECT m FROM MetodosOperacionesJsf m WHERE m.nombreGetAtributoParamWS = :nombreGetAtributoParamWS")})
public class MetodosOperacionesJsf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)  
    @Column(name = "idMetodoOperacionJSF")
    private Integer idMetodoOperacionJSF;
    @Size(max = 50)
    @Column(name = "nombreGetAtributoParamWS")
    private String nombreGetAtributoParamWS;
    @JoinColumn(name = "idParametroOperacionWS", referencedColumnName = "idParametroOperacion")
    @ManyToOne(optional = false)
    private PublicadorWsParametrosOperaciones idParametroOperacionWS;
    @JoinColumn(name = "idMetodoOperacion", referencedColumnName = "idMetodoOperacion")
    @ManyToOne(optional = false)
    private PublicadorWsMetodosOperaciones idMetodoOperacion;
    @JoinColumn(name = "idClassInternalValEvent", referencedColumnName = "idClase")
    @ManyToOne(optional = false)
    private PublicadorWsClases idClassInternalValEvent;
    @JoinColumn(name = "idClassParamEvent", referencedColumnName = "idClase")
    @ManyToOne(optional = false)
    private PublicadorWsClases idClassParamEvent;

    @JoinColumn(name = "idClassUIComponentJSF", referencedColumnName = "idClase")
    @ManyToOne(optional = false)
    private PublicadorWsClases idClassUIComponentJSF;
    
    public MetodosOperacionesJsf() {
    }

    public MetodosOperacionesJsf(Integer idMetodoOperacionJSF) {
        this.idMetodoOperacionJSF = idMetodoOperacionJSF;
    }

    public Integer getIdMetodoOperacionJSF() {
        return idMetodoOperacionJSF;
    }

    public void setIdMetodoOperacionJSF(Integer idMetodoOperacionJSF) {
        this.idMetodoOperacionJSF = idMetodoOperacionJSF;
    }

    public String getNombreGetAtributoParamWS() {
        return nombreGetAtributoParamWS;
    }

    public void setNombreGetAtributoParamWS(String nombreGetAtributoParamWS) {
        this.nombreGetAtributoParamWS = nombreGetAtributoParamWS;
    }

    public PublicadorWsParametrosOperaciones getIdParametroOperacionWS() {
        return idParametroOperacionWS;
    }

    public void setIdParametroOperacionWS(PublicadorWsParametrosOperaciones idParametroOperacionWS) {
        this.idParametroOperacionWS = idParametroOperacionWS;
    }

    public PublicadorWsMetodosOperaciones getIdMetodoOperacion() {
        return idMetodoOperacion;
    }

    public void setIdMetodoOperacion(PublicadorWsMetodosOperaciones idMetodoOperacion) {
        this.idMetodoOperacion = idMetodoOperacion;
    }

    public PublicadorWsClases getIdClassInternalValEvent() {
        return idClassInternalValEvent;
    }

    public void setIdClassInternalValEvent(PublicadorWsClases idClassInternalValEvent) {
        this.idClassInternalValEvent = idClassInternalValEvent;
    }

    public PublicadorWsClases getIdClassParamEvent() {
        return idClassParamEvent;
    }

    public void setIdClassParamEvent(PublicadorWsClases idClassParamEvent) {
        this.idClassParamEvent = idClassParamEvent;
    }

    public PublicadorWsClases getIdClassUIComponentJSF() {
        return idClassUIComponentJSF;
    }

    public void setIdClassUIComponentJSF(PublicadorWsClases idClassUIComponentJSF) {
        this.idClassUIComponentJSF = idClassUIComponentJSF;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMetodoOperacionJSF != null ? idMetodoOperacionJSF.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MetodosOperacionesJsf)) {
            return false;
        }
        MetodosOperacionesJsf other = (MetodosOperacionesJsf) object;
        if ((this.idMetodoOperacionJSF == null && other.idMetodoOperacionJSF != null) || (this.idMetodoOperacionJSF != null && !this.idMetodoOperacionJSF.equals(other.idMetodoOperacionJSF))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.entity.MetodosOperacionesJsf[ idMetodoOperacionJSF=" + idMetodoOperacionJSF + " ]";
    }
    
}
