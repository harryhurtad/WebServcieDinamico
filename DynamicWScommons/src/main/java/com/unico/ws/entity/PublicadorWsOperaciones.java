/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author T13237
 */
@Entity
@Table(name = "PUBLICADOR_WS_OPERACIONES")
@NamedQueries({
    @NamedQuery(name = "PublicadorWsOperaciones.findAll", query = "SELECT p FROM PublicadorWsOperaciones p"),
    @NamedQuery(name = "PublicadorWsOperaciones.findByIdOperacion", query = "SELECT p FROM PublicadorWsOperaciones p WHERE p.idOperacion = :idOperacion"),
    @NamedQuery(name = "PublicadorWsOperaciones.findByNombreOperacion", query = "SELECT p FROM PublicadorWsOperaciones p WHERE p.nombreOperacion = :nombreOperacion"),
    @NamedQuery(name = "PublicadorWsOperaciones.findByDescripcionOperacion", query = "SELECT p FROM PublicadorWsOperaciones p WHERE p.descripcionOperacion = :descripcionOperacion"),
    @NamedQuery(name = "PublicadorWsOperaciones.findByMetodoOperacion", query = "SELECT p FROM PublicadorWsOperaciones p WHERE p.metodoOperacion = :metodoOperacion")})
public class PublicadorWsOperaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOperacion")
    private Integer idOperacion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombreOperacion")
    private String nombreOperacion;
    
    @Size(max = 100)
    @Column(name = "descripcionOperacion")
    private String descripcionOperacion;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "metodoOperacion")
    private String metodoOperacion;
           
   
    
    @JoinColumn(name = "idClaseRetornoOperacion", referencedColumnName = "idClase")   
    @ManyToOne(cascade = CascadeType.REFRESH)
    private PublicadorWsClases idClaseRetornoOperacion;
    
    @OneToMany(mappedBy = "idOperacion", cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<PublicadorWsParametrosOperaciones> publicadorWsParametrosOperacionesList;
    
    @OneToMany(mappedBy = "idOperacion", cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private List<PublicadorWsEjecucionOperacionCliente> publicadorWsEjecucionOperacionClienteList;
    
     public PublicadorWsOperaciones() {
    }

    public PublicadorWsOperaciones(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public PublicadorWsOperaciones(Integer idOperacion, String nombreOperacion, String metodoOperacion) {
        this.idOperacion = idOperacion;
        this.nombreOperacion = nombreOperacion;
        this.metodoOperacion = metodoOperacion;
    }

    public Integer getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(Integer idOperacion) {
        this.idOperacion = idOperacion;
    }

    public String getNombreOperacion() {
        return nombreOperacion;
    }

    public void setNombreOperacion(String nombreOperacion) {
        this.nombreOperacion = nombreOperacion;
    }

    public String getDescripcionOperacion() {
        return descripcionOperacion;
    }

    public void setDescripcionOperacion(String descripcionOperacion) {
        this.descripcionOperacion = descripcionOperacion;
    }

    public String getMetodoOperacion() {
        return metodoOperacion;
    }

    public void setMetodoOperacion(String metodoOperacion) {
        this.metodoOperacion = metodoOperacion;
    }
  

    public PublicadorWsClases getIdClaseRetornoOperacion() {
        return idClaseRetornoOperacion;
    }

    public void setIdClaseRetornoOperacion(PublicadorWsClases idClaseRetornoOperacion) {
        this.idClaseRetornoOperacion = idClaseRetornoOperacion;
    }

    public List<PublicadorWsParametrosOperaciones> getPublicadorWsParametrosOperacionesList() {
        return publicadorWsParametrosOperacionesList;
    }

    public void setPublicadorWsParametrosOperacionesList(List<PublicadorWsParametrosOperaciones> publicadorWsParametrosOperacionesList) {
        this.publicadorWsParametrosOperacionesList = publicadorWsParametrosOperacionesList;
    }

    public List<PublicadorWsEjecucionOperacionCliente> getPublicadorWsEjecucionOperacionClienteList() {
        return publicadorWsEjecucionOperacionClienteList;
    }

    public void setPublicadorWsEjecucionOperacionClienteList(List<PublicadorWsEjecucionOperacionCliente> publicadorWsEjecucionOperacionClienteList) {
        this.publicadorWsEjecucionOperacionClienteList = publicadorWsEjecucionOperacionClienteList;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOperacion != null ? idOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicadorWsOperaciones)) {
            return false;
        }
        PublicadorWsOperaciones other = (PublicadorWsOperaciones) object;
        if ((this.idOperacion == null && other.idOperacion != null) || (this.idOperacion != null && !this.idOperacion.equals(other.idOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.entity.PublicadorWsOperaciones[ idOperacion=" + idOperacion + " ]";
    }  

}
