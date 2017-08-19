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

/**
 *
 * @author T13237
 */
@Entity
@Table(name = "PUBLICADOR_WS_EJECUCION_OPERACION_CLIENTE")
@NamedQueries({
    @NamedQuery(name = "PublicadorWsEjecucionOperacionCliente.findAll", query = "SELECT p FROM PublicadorWsEjecucionOperacionCliente p"),
    @NamedQuery(name = "PublicadorWsEjecucionOperacionCliente.findByIdEjecucionOperacion", query = "SELECT p FROM PublicadorWsEjecucionOperacionCliente p WHERE p.idEjecucionOperacion = :idEjecucionOperacion"),
    @NamedQuery(name = "PublicadorWsEjecucionOperacionCliente.findByIdOperacion", query = "SELECT p FROM PublicadorWsEjecucionOperacionCliente p WHERE p.idOperacion.idOperacion = :idOperacion ORDER BY p.ordenEjecucion ASC"),
    @NamedQuery(name = "PublicadorWsEjecucionOperacionCliente.findByOrdenEjecucion", query = "SELECT p FROM PublicadorWsEjecucionOperacionCliente p WHERE p.ordenEjecucion = :ordenEjecucion"),
     })
public class PublicadorWsEjecucionOperacionCliente implements Serializable {

   
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEjecucionOperacion")
    private Integer idEjecucionOperacion;
    @Column(name = "ordenEjecucion")
    private Integer ordenEjecucion;
    @JoinColumn(name = "idOperacion", referencedColumnName = "idOperacion")
    @ManyToOne(optional = false,cascade = CascadeType.REFRESH)
    private PublicadorWsOperaciones idOperacion;
    @JoinColumn(name = "idMetodoOperacion", referencedColumnName = "idMetodoOperacion")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private PublicadorWsMetodosOperaciones idMetodoOperacion;
    @JoinColumn(name = "idClassManageBeanFind", referencedColumnName = "idClase")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private PublicadorWsClases idClassManageBeanFind;
    @JoinColumn(name = "idClaseToBuildInst", referencedColumnName = "idClase")
    @ManyToOne(cascade = CascadeType.REFRESH)
    private PublicadorWsClases idClaseToBuildInst;
     @OneToMany(cascade = CascadeType.REFRESH,mappedBy = "idResultEjecInstClass",fetch = FetchType.EAGER)
    private List<ParametrosToMapMetodos> parametrosToMapMetodosList;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "idEjecucionOperacion",fetch = FetchType.EAGER)
    private List<ParametrosToMapInstClass> parametrosToMapInstClassList;
  /*  @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "idEjecucionOperacion")
    private List<PublicadorWsSetValueInstClase> publicadorWsSetValueInstClaseList;*/

    public PublicadorWsEjecucionOperacionCliente() {
    }

    public PublicadorWsEjecucionOperacionCliente(Integer idEjecucionOperacion) {
        this.idEjecucionOperacion = idEjecucionOperacion;
    }


    public Integer getIdEjecucionOperacion() {
        return idEjecucionOperacion;
    }

    public void setIdEjecucionOperacion(Integer idEjecucionOperacion) {
        this.idEjecucionOperacion = idEjecucionOperacion;
    }

    public Integer getOrdenEjecucion() {
        return ordenEjecucion;
    }

    public void setOrdenEjecucion(Integer ordenEjecucion) {
        this.ordenEjecucion = ordenEjecucion;
    }

   
    public PublicadorWsOperaciones getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(PublicadorWsOperaciones idOperacion) {
        this.idOperacion = idOperacion;
    }

    public PublicadorWsMetodosOperaciones getIdMetodoOperacion() {
        return idMetodoOperacion;
    }

    public void setIdMetodoOperacion(PublicadorWsMetodosOperaciones idMetodoOperacion) {
        this.idMetodoOperacion = idMetodoOperacion;
    }

    public PublicadorWsClases getIdClassManageBeanFind() {
        return idClassManageBeanFind;
    }

    public void setIdClassManageBeanFind(PublicadorWsClases idClassManageBeanFind) {
        this.idClassManageBeanFind = idClassManageBeanFind;
    }

    public PublicadorWsClases getIdClaseToBuildInst() {
        return idClaseToBuildInst;
    }

    public void setIdClaseToBuildInst(PublicadorWsClases idClaseToBuildInst) {
        this.idClaseToBuildInst = idClaseToBuildInst;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEjecucionOperacion != null ? idEjecucionOperacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicadorWsEjecucionOperacionCliente)) {
            return false;
        }
        PublicadorWsEjecucionOperacionCliente other = (PublicadorWsEjecucionOperacionCliente) object;
        if ((this.idEjecucionOperacion == null && other.idEjecucionOperacion != null) || (this.idEjecucionOperacion != null && !this.idEjecucionOperacion.equals(other.idEjecucionOperacion))) {
            return false;
        }
        return true;
    }
    
     public List<ParametrosToMapMetodos> getParametrosToMapMetodosList() {
        return parametrosToMapMetodosList;
    }

    public void setParametrosToMapMetodosList(List<ParametrosToMapMetodos> parametrosToMapMetodosList) {
        this.parametrosToMapMetodosList = parametrosToMapMetodosList;
    }

    public List<ParametrosToMapInstClass> getParametrosToMapInstClassList() {
        return parametrosToMapInstClassList;
    }

    public void setParametrosToMapInstClassList(List<ParametrosToMapInstClass> parametrosToMapInstClassList) {
        this.parametrosToMapInstClassList = parametrosToMapInstClassList;
    }

    @Override
    public String toString() {
        return "com.unico.ws.entity.PublicadorWsEjecucionOperacionCliente[ idEjecucionOperacion=" + idEjecucionOperacion + " ]";
    }

   /* public List<PublicadorWsSetValueInstClase> getPublicadorWsSetValueInstClaseList() {
        return publicadorWsSetValueInstClaseList;
    }

    public void setPublicadorWsSetValueInstClaseList(List<PublicadorWsSetValueInstClase> publicadorWsSetValueInstClaseList) {
        this.publicadorWsSetValueInstClaseList = publicadorWsSetValueInstClaseList;
    }*/

   
    
}
