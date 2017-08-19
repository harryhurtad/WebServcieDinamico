/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "PUBLICADOR_WS_METODOS_OPERACIONES")
@NamedQueries({
    @NamedQuery(name = "PublicadorWsMetodosOperaciones.findAll", query = "SELECT p FROM PublicadorWsMetodosOperaciones p"),
    @NamedQuery(name = "PublicadorWsMetodosOperaciones.findByIdMetodoOperacion", query = "SELECT p FROM PublicadorWsMetodosOperaciones p WHERE p.idMetodoOperacion = :idMetodoOperacion"),
   // @NamedQuery(name = "PublicadorWsMetodosOperaciones.findByIdOperacion", query = "SELECT p FROM PublicadorWsMetodosOperaciones p WHERE p.idOperacion.idOperacion = :idOperacion "),
    @NamedQuery(name = "PublicadorWsMetodosOperaciones.findByMetodoOperacion", query = "SELECT p FROM PublicadorWsMetodosOperaciones p WHERE p.metodoOperacion = :metodoOperacion"),
    @NamedQuery(name = "PublicadorWsMetodosOperaciones.findByDescripcionMetdOperacion", query = "SELECT p FROM PublicadorWsMetodosOperaciones p WHERE p.descripcionMetdOperacion = :descripcionMetdOperacion")}
)

public class PublicadorWsMetodosOperaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMetodoOperacion")
    private Integer idMetodoOperacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "metodoOperacion")
    private String metodoOperacion;
    @Size(max = 100)
    @Column(name = "descripcionMetdOperacion")
    private String descripcionMetdOperacion;

    @JoinColumn(name = "idClaseMetdRetornoOperacion", referencedColumnName = "idClase")
    @ManyToOne
    private PublicadorWsClases idClaseMetdRetornoOperacion;
    @JoinColumn(name = "idClasePrincipalMetdOperacion", referencedColumnName = "idClase")
    @ManyToOne(optional = false)
    private PublicadorWsClases idClasePrincipalMetdOperacion;
    @OneToMany(mappedBy = "idMetodoOperacion", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<PublicadorWsParametrosMetodosOperaciones> publicadorWsParametrosMetodosOperacionesList;

    @OneToMany(mappedBy = "idMetodoOperacion", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private List<MetodosOperacionesJsf> metodosOperacionesJsfList;

    public PublicadorWsMetodosOperaciones() {
    }

    public PublicadorWsMetodosOperaciones(Integer idMetodoOperacion) {
        this.idMetodoOperacion = idMetodoOperacion;
    }

    public PublicadorWsMetodosOperaciones(Integer idMetodoOperacion, String metodoOperacion) {
        this.idMetodoOperacion = idMetodoOperacion;
        this.metodoOperacion = metodoOperacion;
    }

    public Integer getIdMetodoOperacion() {
        return idMetodoOperacion;
    }

    public void setIdMetodoOperacion(Integer idMetodoOperacion) {
        this.idMetodoOperacion = idMetodoOperacion;
    }

    public String getMetodoOperacion() {
        return metodoOperacion;
    }

    public void setMetodoOperacion(String metodoOperacion) {
        this.metodoOperacion = metodoOperacion;
    }

    public String getDescripcionMetdOperacion() {
        return descripcionMetdOperacion;
    }

    public void setDescripcionMetdOperacion(String descripcionMetdOperacion) {
        this.descripcionMetdOperacion = descripcionMetdOperacion;
    }

    public PublicadorWsClases getIdClaseMetdRetornoOperacion() {
        return idClaseMetdRetornoOperacion;
    }

    public void setIdClaseMetdRetornoOperacion(PublicadorWsClases idClaseMetdRetornoOperacion) {
        this.idClaseMetdRetornoOperacion = idClaseMetdRetornoOperacion;
    }

    public PublicadorWsClases getIdClasePrincipalMetdOperacion() {
        return idClasePrincipalMetdOperacion;
    }

    public void setIdClasePrincipalMetdOperacion(PublicadorWsClases idClasePrincipalMetdOperacion) {
        this.idClasePrincipalMetdOperacion = idClasePrincipalMetdOperacion;
    }

    public List<PublicadorWsParametrosMetodosOperaciones> getPublicadorWsParametrosMetodosOperacionesList() {
        return publicadorWsParametrosMetodosOperacionesList;
    }

    public void setPublicadorWsParametrosMetodosOperacionesList(List<PublicadorWsParametrosMetodosOperaciones> publicadorWsParametrosMetodosOperacionesCollection) {
        this.publicadorWsParametrosMetodosOperacionesList = publicadorWsParametrosMetodosOperacionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMetodoOperacion != null ? idMetodoOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicadorWsMetodosOperaciones)) {
            return false;
        }
        PublicadorWsMetodosOperaciones other = (PublicadorWsMetodosOperaciones) object;
        if ((this.idMetodoOperacion == null && other.idMetodoOperacion != null) || (this.idMetodoOperacion != null && !this.idMetodoOperacion.equals(other.idMetodoOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.entity.PublicadorWsMetodosOperaciones[ idMetodoOperacion=" + idMetodoOperacion + " ]";
    }

    public List<MetodosOperacionesJsf> getMetodosOperacionesJsfList() {
        return metodosOperacionesJsfList;
    }

    public void setMetodosOperacionesJsfList(List<MetodosOperacionesJsf> metodosOperacionesJsfList) {
        this.metodosOperacionesJsfList = metodosOperacionesJsfList;
    }

}
