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
@Table(name = "PUBLICADOR_WS_CLASES_ATRIBUTOS")
@NamedQueries({
    @NamedQuery(name = "PublicadorWsClasesAtributos.findAll", query = "SELECT p FROM PublicadorWsClasesAtributos p"),
    @NamedQuery(name = "PublicadorWsClasesAtributos.findByIdAtributo", query = "SELECT p FROM PublicadorWsClasesAtributos p WHERE p.idAtributo = :idAtributo"),
    @NamedQuery(name = "PublicadorWsClasesAtributos.findByNombreAtributo", query = "SELECT p FROM PublicadorWsClasesAtributos p WHERE p.nombreAtributo = :nombreAtributo")})
public class PublicadorWsClasesAtributos implements Serializable {

   
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAtributo")
    private Integer idAtributo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombreAtributo")
    private String nombreAtributo;
    //@OneToMany( mappedBy = "idAtributoToSetValueClass")
   // private List<PublicadorWsSetValueInstClase> publicadorWsSetValueInstClaseList;
    @JoinColumn(name = "idClaseAtributo", referencedColumnName = "idClase")
    @ManyToOne(optional = false)
    private PublicadorWsClases idClaseAtributo;
    @JoinColumn(name = "idClasePrincipal", referencedColumnName = "idClase")
    @ManyToOne(optional = false)
    private PublicadorWsClases idClasePrincipal;
    
    @OneToMany(cascade = CascadeType.REFRESH, mappedBy = "idAtributoToSetValueClass",fetch = FetchType.EAGER)
    private List<ParametrosToMapInstClass> parametrosToMapInstClassList;
    
    
    public PublicadorWsClasesAtributos() {
    }

    public PublicadorWsClasesAtributos(Integer idAtributo) {
        this.idAtributo = idAtributo;
    }

    public PublicadorWsClasesAtributos(Integer idAtributo, String nombreAtributo) {
        this.idAtributo = idAtributo;
        this.nombreAtributo = nombreAtributo;
    }

    public Integer getIdAtributo() {
        return idAtributo;
    }

    public void setIdAtributo(Integer idAtributo) {
        this.idAtributo = idAtributo;
    }

    public String getNombreAtributo() {
        return nombreAtributo;
    }

    public void setNombreAtributo(String nombreAtributo) {
        this.nombreAtributo = nombreAtributo;
    }

  /*  public List<PublicadorWsSetValueInstClase> getPublicadorWsSetValueInstClaseList() {
        return publicadorWsSetValueInstClaseList;
    }

    public void setPublicadorWsSetValueInstClaseList(List<PublicadorWsSetValueInstClase> publicadorWsSetValueInstClaseList) {
        this.publicadorWsSetValueInstClaseList = publicadorWsSetValueInstClaseList;
    }*/

    public PublicadorWsClases getIdClaseAtributo() {
        return idClaseAtributo;
    }

    public void setIdClaseAtributo(PublicadorWsClases idClaseAtributo) {
        this.idClaseAtributo = idClaseAtributo;
    }

    public PublicadorWsClases getIdClasePrincipal() {
        return idClasePrincipal;
    }

    public void setIdClasePrincipal(PublicadorWsClases idClasePrincipal) {
        this.idClasePrincipal = idClasePrincipal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtributo != null ? idAtributo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicadorWsClasesAtributos)) {
            return false;
        }
        PublicadorWsClasesAtributos other = (PublicadorWsClasesAtributos) object;
        if ((this.idAtributo == null && other.idAtributo != null) || (this.idAtributo != null && !this.idAtributo.equals(other.idAtributo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.entity.PublicadorWsClasesAtributos[ idAtributo=" + idAtributo + " ]";
    }

    public List<ParametrosToMapInstClass> getParametrosToMapInstClassList() {
        return parametrosToMapInstClassList;
    }

    public void setParametrosToMapInstClassList(List<ParametrosToMapInstClass> parametrosToMapInstClassList) {
        this.parametrosToMapInstClassList = parametrosToMapInstClassList;
    }
    
}
