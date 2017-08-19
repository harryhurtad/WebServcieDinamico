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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "PUBLICADOR_WS_ESTRUCTURA")
@NamedQueries({
    @NamedQuery(name = "PublicadorWsEstructura.findAll", query = "SELECT p FROM PublicadorWsEstructura p WHERe p.habilitado = true"),
    @NamedQuery(name = "PublicadorWsEstructura.findByIdWs", query = "SELECT p FROM PublicadorWsEstructura p WHERE p.idWs = :idWs AND p.habilitado = true"),
    @NamedQuery(name = "PublicadorWsEstructura.findByNombreWs", query = "SELECT p FROM PublicadorWsEstructura p WHERE p.nombreWs = :nombreWs AND p.habilitado = true"),
    @NamedQuery(name = "PublicadorWsEstructura.findByPortName", query = "SELECT p FROM PublicadorWsEstructura p WHERE p.portName = :portName AND p.habilitado = true"),
    @NamedQuery(name = "PublicadorWsEstructura.findByTargetNamespace", query = "SELECT p FROM PublicadorWsEstructura p WHERE p.targetNamespace = :targetNamespace AND p.habilitado = true"),
    @NamedQuery(name = "PublicadorWsEstructura.findByPackageWs", query = "SELECT p FROM PublicadorWsEstructura p WHERE p.packageWs = :packageWs AND p.habilitado = true"),
    @NamedQuery(name = "PublicadorWsEstructura.findByDescripcionWs", query = "SELECT p FROM PublicadorWsEstructura p WHERE p.descripcionWs = :descripcionWs AND p.habilitado = true"),
    @NamedQuery(name = "PublicadorWsEstructura.findByHabilitado", query = "SELECT p FROM PublicadorWsEstructura p WHERE p.habilitado = :habilitado")})
public class PublicadorWsEstructura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idWs")
    private Integer idWs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombreWs")
    private String nombreWs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "portName")
    private String portName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "targetNamespace")
    private String targetNamespace;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "package_ws")
    private String packageWs;
    @Size(max = 100)
    @Column(name = "descripcionWs")
    private String descripcionWs;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "contextoWS")
    private String contextoWS;
    @Column(name = "contexto_app_cliente")
    private String contexto_app_cliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habilitado")
    private boolean habilitado;   
    @JoinTable(name = "PUBLICADOR_WS_ESTRUCTURA_OPERACIONES", joinColumns = {
        @JoinColumn(name = "idWs", referencedColumnName = "idWs") }, inverseJoinColumns = {
        @JoinColumn(name = "idOperacion", referencedColumnName = "idOperacion")})   
    @ManyToMany(cascade = CascadeType.REFRESH)
    private List<PublicadorWsOperaciones> publicadorWsOperacionesCollection;
   
    @ManyToOne(cascade = CascadeType.REFRESH) 
    @JoinColumn(name="idClassEJBRemote")
    private  PublicadorWsClases idClassEJBRemote;

    public PublicadorWsEstructura() {
    }

    public PublicadorWsEstructura(Integer idWs) {
        this.idWs = idWs;
    }

    public PublicadorWsEstructura(Integer idWs, String nombreWs, String portName, String targetNamespace, String packageWs, String contextoWS,String contexto_app_cliente, boolean habilitado) {
        this.idWs = idWs;
        this.nombreWs = nombreWs;
        this.portName = portName;
        this.targetNamespace = targetNamespace;
        this.packageWs = packageWs;
        this.contextoWS = contextoWS;
        this.contexto_app_cliente=contexto_app_cliente;
        this.habilitado = habilitado;
    }

    public Integer getIdWs() {
        return idWs;
    }

    public void setIdWs(Integer idWs) {
        this.idWs = idWs;
    }

    public String getNombreWs() {
        return nombreWs;
    }

    public void setNombreWs(String nombreWs) {
        this.nombreWs = nombreWs;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getTargetNamespace() {
        return targetNamespace;
    }

    public void setTargetNamespace(String targetNamespace) {
        this.targetNamespace = targetNamespace;
    }

    public String getPackageWs() {
        return packageWs;
    }

    public void setPackageWs(String packageWs) {
        this.packageWs = packageWs;
    }

    public String getDescripcionWs() {
        return descripcionWs;
    }

    public void setDescripcionWs(String descripcionWs) {
        this.descripcionWs = descripcionWs;
    }

    public String getContextoWS() {
        return contextoWS;
    }

    public void setContextoWS(String contextoWS) {
        this.contextoWS = contextoWS;
    }

    public String getContexto_app_cliente() {
        return contexto_app_cliente;
    }

    public void setContexto_app_cliente(String contexto_app_cliente) {
        this.contexto_app_cliente = contexto_app_cliente;
    }

    
     public boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public List<PublicadorWsOperaciones> getPublicadorWsOperacionesCollection() {
        return publicadorWsOperacionesCollection;
    }

    public void setPublicadorWsOperacionesCollection(List<PublicadorWsOperaciones> publicadorWsOperacionesCollection) {
        this.publicadorWsOperacionesCollection = publicadorWsOperacionesCollection;
    }

    public PublicadorWsClases getIdClassEJBRemote() {
        return idClassEJBRemote;
    }

    public void setIdClassEJBRemote(PublicadorWsClases idClassEJBRemote) {
        this.idClassEJBRemote = idClassEJBRemote;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idWs != null ? idWs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicadorWsEstructura)) {
            return false;
        }
        PublicadorWsEstructura other = (PublicadorWsEstructura) object;
        if ((this.idWs == null && other.idWs != null) || (this.idWs != null && !this.idWs.equals(other.idWs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.entity.PublicadorWsEstructura[ idWs=" + idWs + " ]";
    }
    
}
