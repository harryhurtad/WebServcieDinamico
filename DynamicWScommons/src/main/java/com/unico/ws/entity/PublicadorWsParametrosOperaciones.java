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
@Table(name = "PUBLICADOR_WS_PARAMETROS_OPERACIONES")
@NamedQueries({
    @NamedQuery(name = "PublicadorWsParametrosOperaciones.findAll", query = "SELECT p FROM PublicadorWsParametrosOperaciones p"),
    @NamedQuery(name = "PublicadorWsParametrosOperaciones.findByIdParametroOperacion", query = "SELECT p FROM PublicadorWsParametrosOperaciones p WHERE p.idParametroOperacion = :idParametroOperacion"),
    @NamedQuery(name = "PublicadorWsParametrosOperaciones.findByIdOperacion", query = "SELECT p FROM PublicadorWsParametrosOperaciones p WHERE p.idOperacion.idOperacion = :idOperacion"),
    @NamedQuery(name = "PublicadorWsParametrosOperaciones.findByNombreParametro", query = "SELECT p FROM PublicadorWsParametrosOperaciones p WHERE p.nombreParametro = :nombreParametro"),
    @NamedQuery(name = "PublicadorWsParametrosOperaciones.findByOrdenParametro", query = "SELECT p FROM PublicadorWsParametrosOperaciones p WHERE p.ordenParametro = :ordenParametro")})
public class PublicadorWsParametrosOperaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idParametroOperacion")
    private Integer idParametroOperacion;
    @Size(max = 100)
    @Column(name = "nombreParametro")
    private String nombreParametro;
    @Column(name = "ordenParametro")
    private Integer ordenParametro;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "idParametroOperacionWS")
    private Collection<PublicadorWsParametrosToMapManagebean> publicadorWsParametrosToMapManagebeanCollection;
    @JoinColumn(name = "idOperacion", referencedColumnName = "idOperacion")
    @ManyToOne
    private PublicadorWsOperaciones idOperacion;
    @JoinColumn(name = "idClaseLlave", referencedColumnName = "idClase")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private PublicadorWsClases idClaseLlave;

    @JoinColumn(name = "idClaseValor", referencedColumnName = "idClase")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private PublicadorWsClases idClaseValor;

    @JoinColumn(name = "idClaseCollection", referencedColumnName = "idClase")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private PublicadorWsClases idClaseCollection;

    public PublicadorWsParametrosOperaciones() {
    }

    public PublicadorWsParametrosOperaciones(Integer idParametroOperacion) {
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

    public Integer getOrdenParametro() {
        return ordenParametro;
    }

    public void setOrdenParametro(Integer ordenParametro) {
        this.ordenParametro = ordenParametro;
    }

    public Collection<PublicadorWsParametrosToMapManagebean> getPublicadorWsParametrosToMapManagebeanCollection() {
        return publicadorWsParametrosToMapManagebeanCollection;
    }

    public void setPublicadorWsParametrosToMapManagebeanCollection(Collection<PublicadorWsParametrosToMapManagebean> publicadorWsParametrosToMapManagebeanCollection) {
        this.publicadorWsParametrosToMapManagebeanCollection = publicadorWsParametrosToMapManagebeanCollection;
    }

    public PublicadorWsOperaciones getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(PublicadorWsOperaciones idOperacion) {
        this.idOperacion = idOperacion;
    }

    public PublicadorWsClases getIdClaseLlave() {
        return idClaseLlave;
    }

    public void setIdClaseLlave(PublicadorWsClases idClaseLlave) {
        this.idClaseLlave = idClaseLlave;
    }

    public PublicadorWsClases getIdClaseValor() {
        return idClaseValor;
    }

    public void setIdClaseValor(PublicadorWsClases idClaseValor) {
        this.idClaseValor = idClaseValor;
    }

    public PublicadorWsClases getIdClaseCollection() {
        return idClaseCollection;
    }

    public void setIdClaseCollection(PublicadorWsClases idClaseCollection) {
        this.idClaseCollection = idClaseCollection;
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
        if (!(object instanceof PublicadorWsParametrosOperaciones)) {
            return false;
        }
        PublicadorWsParametrosOperaciones other = (PublicadorWsParametrosOperaciones) object;
        if ((this.idParametroOperacion == null && other.idParametroOperacion != null) || (this.idParametroOperacion != null && !this.idParametroOperacion.equals(other.idParametroOperacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.entity.PublicadorWsParametrosOperaciones[ idParametroOperacion=" + idParametroOperacion + " ]";
    }

}
