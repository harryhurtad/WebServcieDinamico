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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "PUBLICADOR_WS_CLASES")
@NamedQueries({
    @NamedQuery(name = "PublicadorWsClases.findAll", query = "SELECT p FROM PublicadorWsClases p"),
    @NamedQuery(name = "PublicadorWsClases.findByIdClase", query = "SELECT p FROM PublicadorWsClases p WHERE p.idClase = :idClase"),
    @NamedQuery(name = "PublicadorWsClases.findByNombreCLase", query = "SELECT p FROM PublicadorWsClases p WHERE p.nombreCLase = :nombreCLase"),
    @NamedQuery(name = "PublicadorWsClases.findByAliasClase", query = "SELECT p FROM PublicadorWsClases p WHERE p.aliasClase = :aliasClase"),
    @NamedQuery(name = "PublicadorWsClases.findByPackageClass", query = "SELECT p FROM PublicadorWsClases p WHERE p.packageClass = :packageClass"),
    @NamedQuery(name = "PublicadorWsClases.findByJndi", query = "SELECT p FROM PublicadorWsClases p WHERE p.jndi = :jndi")})
public class PublicadorWsClases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "idClase")
    private Integer idClase;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombreCLase")
    private String nombreCLase;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "aliasClase")
    private String aliasClase;
    @Basic(optional = false)
    @Column(name = "packageClass")
    private String packageClass;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "archivoXSD")
    private String archivoXSD;
    @Size(max = 100)
    @Column(name = "JNDI")
    private String jndi;
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "publicadorWsClasesPrincipal",fetch = FetchType.EAGER)
    private List<PublicadorWsDependenciaClases> publicadorWsClasesDependenciaList;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "idClasePrincipal",fetch = FetchType.EAGER)
    private List<PublicadorWsClasesAtributos> publicadorWsClasesAtributosList;
   

    public PublicadorWsClases() {
    }

    public PublicadorWsClases(Integer idClase) {
        this.idClase = idClase;
    }

    public PublicadorWsClases(Integer idClase, String nombreCLase, String packageClass) {
        this.idClase = idClase;
        this.nombreCLase = nombreCLase;
        this.packageClass = packageClass;
    }

    public Integer getIdClase() {
        return idClase;
    }

    public void setIdClase(Integer idClase) {
        this.idClase = idClase;
    }

    public String getNombreCLase() {
        return nombreCLase;
    }

    public void setNombreCLase(String nombreCLase) {
        this.nombreCLase = nombreCLase;
    }

    public String getPackageClass() {
        return packageClass;
    }

    public void setPackageClass(String packageClass) {
        this.packageClass = packageClass;
    }

    public String getArchivoXSD() {
        return archivoXSD;
    }

    public void setArchivoXSD(String archivoXSD) {
        this.archivoXSD = archivoXSD;
    }

    public String getJndi() {
        return jndi;
    }

    public void setJndi(String jndi) {
        this.jndi = jndi;
    }

    public List<PublicadorWsDependenciaClases> getPublicadorWsClasesDependenciaList() {
        return publicadorWsClasesDependenciaList;
    }

    public void setPublicadorWsClasesDependenciaList(List<PublicadorWsDependenciaClases> publicadorWsClasesDependenciaList) {
        this.publicadorWsClasesDependenciaList = publicadorWsClasesDependenciaList;
    }

    public String getAliasClase() {
        return aliasClase;
    }

    public void setAliasClase(String aliasClase) {
        this.aliasClase = aliasClase;
    }
    
     public List<PublicadorWsClasesAtributos> getPublicadorWsClasesAtributosList() {
        return publicadorWsClasesAtributosList;
    }

    public void setPublicadorWsClasesAtributosList(List<PublicadorWsClasesAtributos> publicadorWsClasesAtributosList) {
        this.publicadorWsClasesAtributosList = publicadorWsClasesAtributosList;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClase != null ? idClase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicadorWsClases)) {
            return false;
        }
        PublicadorWsClases other = (PublicadorWsClases) object;
        if ((this.idClase == null && other.idClase != null) || (this.idClase != null && !this.idClase.equals(other.idClase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.exception.PublicadorWsClases[ idClase=" + idClase + " ]";
    } 
  
}
