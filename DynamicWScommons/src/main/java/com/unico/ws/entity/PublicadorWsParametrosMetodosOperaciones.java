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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author T13237
 */
@Entity
@Table(name = "PUBLICADOR_WS_PARAMETROS_METODOS_OPERACIONES")
@NamedQueries({
    @NamedQuery(name = "PublicadorWsParametrosMetodosOperaciones.findAll", query = "SELECT p FROM PublicadorWsParametrosMetodosOperaciones p"),
    @NamedQuery(name = "PublicadorWsParametrosMetodosOperaciones.findByIdParametroOperacion", query = "SELECT p FROM PublicadorWsParametrosMetodosOperaciones p WHERE p.idParametroOperacion = :idParametroOperacion"),
    @NamedQuery(name = "PublicadorWsParametrosMetodosOperaciones.findByIdMetodoOperacion", query = "SELECT p FROM PublicadorWsParametrosMetodosOperaciones p WHERE p.idMetodoOperacion.idMetodoOperacion = :idMetodoOperacion"),
    @NamedQuery(name = "PublicadorWsParametrosMetodosOperaciones.findByNombreParametro", query = "SELECT p FROM PublicadorWsParametrosMetodosOperaciones p WHERE p.nombreParametro = :nombreParametro"),
    @NamedQuery(name = "PublicadorWsParametrosMetodosOperaciones.findByOrdenParametro", query = "SELECT p FROM PublicadorWsParametrosMetodosOperaciones p WHERE p.ordenParametro = :ordenParametro")})
public class PublicadorWsParametrosMetodosOperaciones implements Serializable {

   

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idParametroOperacion")
    private Integer idParametroOperacion;
    @Size(max = 100)
    @Column(name = "nombreParametro")
    private String nombreParametro;
 
    @JoinColumn(name = "idMetodoOperacion", referencedColumnName = "idMetodoOperacion")
    @ManyToOne
    private PublicadorWsMetodosOperaciones idMetodoOperacion;
    @JoinColumn(name = "idClase", referencedColumnName = "idClase")
    @ManyToOne
    private PublicadorWsClases idClase;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ordenParametro")
    private int ordenParametro;
    @OneToOne(cascade = CascadeType.REFRESH, mappedBy = "idParametroOperacion")
    private ParametrosToMapMetodos parametroToMapMetodos;

    public PublicadorWsParametrosMetodosOperaciones() {
    }

    public PublicadorWsParametrosMetodosOperaciones(Integer idParametroOperacion) {
        this.idParametroOperacion = idParametroOperacion;
    }

    public Integer getIdParametroOperacion() {
        return idParametroOperacion;
    }

    public void setIdParametroOperacion(Integer idParametroOperacion) {
        this.idParametroOperacion = idParametroOperacion;
    }

    public String getNombreParametro() {
        return nombreParametro;
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }


    public PublicadorWsMetodosOperaciones getIdMetodoOperacion() {
        return idMetodoOperacion;
    }

    public void setIdMetodoOperacion(PublicadorWsMetodosOperaciones idMetodoOperacion) {
        this.idMetodoOperacion = idMetodoOperacion;
    }

    public PublicadorWsClases getIdClase() {
        return idClase;
    }

    public void setIdClase(PublicadorWsClases idClase) {
        this.idClase = idClase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametroOperacion != null ? idParametroOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicadorWsParametrosMetodosOperaciones)) {
            return false;
        }
        PublicadorWsParametrosMetodosOperaciones other = (PublicadorWsParametrosMetodosOperaciones) object;
        if ((this.idParametroOperacion == null && other.idParametroOperacion != null) || (this.idParametroOperacion != null && !this.idParametroOperacion.equals(other.idParametroOperacion))) {
            return false;
        }
        return true;
    }
    
    
    public int getOrdenParametro() {
        return ordenParametro;
    }

    public void setOrdenParametro(int ordenParametro) {
        this.ordenParametro = ordenParametro;
    }

    public ParametrosToMapMetodos getParametroToMapMetodos() {
        return parametroToMapMetodos;
    }

    public void setParametroToMapMetodos(ParametrosToMapMetodos parametroToMapMetodos) {
        this.parametroToMapMetodos = parametroToMapMetodos;
    }

   
    

    @Override
    public String toString() {
        return "com.unico.ws.entity.PublicadorWsParametrosMetodosOperaciones[ idParametroOperacion=" + idParametroOperacion + " ]";
    }

}
