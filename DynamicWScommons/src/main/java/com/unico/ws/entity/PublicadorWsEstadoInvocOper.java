/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.entity;

import com.unico.ws.constantes.StatusExecutionWSEnum;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author T13237
 */
@Entity
@Table(name = "PUBLICADOR_WS_ESTADO_INVOC_OPER")
@NamedQueries({
    @NamedQuery(name = "PublicadorWsEstadoInvocOper.findAll", query = "SELECT p FROM PublicadorWsEstadoInvocOper p"),
    @NamedQuery(name = "PublicadorWsEstadoInvocOper.findByIdEstOperacion", query = "SELECT p FROM PublicadorWsEstadoInvocOper p WHERE p.idEstOperacion = :idEstOperacion"),
    @NamedQuery(name = "PublicadorWsEstadoInvocOper.findByIdRequest", query = "SELECT p FROM PublicadorWsEstadoInvocOper p WHERE p.idRequest = :idRequest"),
    @NamedQuery(name = "PublicadorWsEstadoInvocOper.findByEstado", query = "SELECT p FROM PublicadorWsEstadoInvocOper p WHERE p.estado = :estado"),
    @NamedQuery(name = "PublicadorWsEstadoInvocOper.findByExcepcion", query = "SELECT p FROM PublicadorWsEstadoInvocOper p WHERE p.excepcion = :excepcion")})
public class PublicadorWsEstadoInvocOper implements Serializable {

       private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Basic(optional = false)  
    @Column(name = "idEstOperacion")
    private Integer idEstOperacion;
    @Basic(optional = false)
    @NotNull 
    @Column(name = "idRequest")
    private String idRequest;
 
   
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private StatusExecutionWSEnum estado;
   
    @Column(name = "excepcion")
    private String excepcion;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fechaFinalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalizacion;
    
    @JoinColumn(name = "idOperacion",referencedColumnName = "idOperacion")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private PublicadorWsOperaciones idOperacion;
    
    public PublicadorWsEstadoInvocOper() {
    }

    public PublicadorWsEstadoInvocOper(Integer idEstOperacion) {
        this.idEstOperacion = idEstOperacion;
    }

    public PublicadorWsEstadoInvocOper(PublicadorWsOperaciones idOperacion ,String idRequest, StatusExecutionWSEnum estado) {
         this.idOperacion=idOperacion;
         this.idRequest=idRequest;
        this.estado = estado;
    }

    public Integer getIdEstOperacion() {
        return idEstOperacion;
    }

    public void setIdEstOperacion(Integer idEstOperacion) {
        this.idEstOperacion = idEstOperacion;
    }

    public String getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(String idRequest) {
        this.idRequest = idRequest;
    }

    public StatusExecutionWSEnum getEstado() {
        return estado;
    }

    public void setEstado(StatusExecutionWSEnum estado) {
        this.estado = estado;
    }

    public String getExcepcion() {
        return excepcion;
    }

    public void setExcepcion(String excepcion) {
        this.excepcion = excepcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstOperacion != null ? idEstOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicadorWsEstadoInvocOper)) {
            return false;
        }
        PublicadorWsEstadoInvocOper other = (PublicadorWsEstadoInvocOper) object;
        if ((this.idEstOperacion == null && other.idEstOperacion != null) || (this.idEstOperacion != null && !this.idEstOperacion.equals(other.idEstOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PublicadorWsEstadoInvocOper{" + "idEstOperacion=" + idEstOperacion + ", idRequest=" + idRequest + ", estado=" + estado + ", excepcion=" + excepcion + ", fechaInicio=" + fechaInicio + ", fechaFinalizacion=" + fechaFinalizacion + ", idOperacion=" + idOperacion + '}';
    }

   

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public PublicadorWsOperaciones getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(PublicadorWsOperaciones idOperacion) {
        this.idOperacion = idOperacion;
    }
    
    
    
}
