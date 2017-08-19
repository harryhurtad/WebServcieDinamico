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
@Table(name = "PUBLICADOR_WS_PARAMETROS_TO_MAP_MANAGEBEAN")
@NamedQueries({
    @NamedQuery(name = "PublicadorWsParametrosToMapManagebean.findAll", query = "SELECT p FROM PublicadorWsParametrosToMapManagebean p"),
    @NamedQuery(name = "PublicadorWsParametrosToMapManagebean.findByIdMetodoToMap", query = "SELECT p FROM PublicadorWsParametrosToMapManagebean p WHERE p.idMetodoToMap = :idMetodoToMap"),
    @NamedQuery(name = "PublicadorWsParametrosToMapManagebean.findByIdOperacion", query = "SELECT p FROM PublicadorWsParametrosToMapManagebean p WHERE p.operacion.idOperacion = :idOperacion "),
    @NamedQuery(name = "PublicadorWsParametrosToMapManagebean.findByNombreGetFieldWS", query = "SELECT p FROM PublicadorWsParametrosToMapManagebean p WHERE p.nombreGetFieldWS = :nombreGetFieldWS"),
    @NamedQuery(name = "PublicadorWsParametrosToMapManagebean.findByNombreAtributoManageBean", query = "SELECT p FROM PublicadorWsParametrosToMapManagebean p WHERE p.nombreAtributoManageBean = :nombreAtributoManageBean")})
public class PublicadorWsParametrosToMapManagebean implements Serializable {

       private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMetodoToMap")
    private Integer idMetodoToMap;
    
    @Basic(optional = false)   
    @Size(min = 1, max = 100)
    @Column(name = "nombreGetFieldWS")
    private String nombreGetFieldWS;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombreAtributoManageBean")
    private String nombreAtributoManageBean;
    @JoinColumn(name = "idParametroOperacionWS", referencedColumnName = "idParametroOperacion")
    @ManyToOne(optional = false)
    private PublicadorWsParametrosOperaciones idParametroOperacionWS;   
    
      
    @JoinColumn(name = "idOperacion", referencedColumnName = "idOperacion")
    @ManyToOne(optional = false)
    private PublicadorWsOperaciones operacion;
    
     @JoinColumn(name = "idClaseManageBean", referencedColumnName = "idClase")
    @ManyToOne(optional = false)
    private PublicadorWsClases idClaseManageBean;
   
    @JoinColumn(name = "idClaseFielManageBean", referencedColumnName = "idClase")
    @ManyToOne(optional = false)
    private PublicadorWsClases idClaseAtributoManageBean;

    
     
    public PublicadorWsParametrosToMapManagebean() {
    }

    public PublicadorWsParametrosToMapManagebean(Integer idMetodoToMap) {
        this.idMetodoToMap = idMetodoToMap;
    }

    public PublicadorWsParametrosToMapManagebean(Integer idMetodoToMap, String nombreGetFieldWS, String nombreAtributoManageBean) {
        this.idMetodoToMap = idMetodoToMap;
        this.nombreGetFieldWS = nombreGetFieldWS;
        this.nombreAtributoManageBean = nombreAtributoManageBean;
    }

    public Integer getIdMetodoToMap() {
        return idMetodoToMap;
    }

    public void setIdMetodoToMap(Integer idMetodoToMap) {
        this.idMetodoToMap = idMetodoToMap;
    }

    public String getNombreGetFieldWS() {
        return nombreGetFieldWS;
    }

    public void setNombreGetFieldWS(String nombreGetFieldWS) {
        this.nombreGetFieldWS = nombreGetFieldWS;
    }

    public String getNombreAtributoManageBean() {
        return nombreAtributoManageBean;
    }

    public void setNombreAtributoManageBean(String nombreAtributoManageBean) {
        this.nombreAtributoManageBean = nombreAtributoManageBean;
    }

    public PublicadorWsParametrosOperaciones getIdParametroOperacionWS() {
        return idParametroOperacionWS;
    }

    public void setIdParametroOperacionWS(PublicadorWsParametrosOperaciones idParametroOperacionWS) {
        this.idParametroOperacionWS = idParametroOperacionWS;
    }

    public PublicadorWsClases getIdClaseAtributoManageBean() {
        return idClaseAtributoManageBean;
    }

    public void setIdClaseAtributoManageBean(PublicadorWsClases idClaseAtributoManageBean) {
        this.idClaseAtributoManageBean = idClaseAtributoManageBean;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMetodoToMap != null ? idMetodoToMap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicadorWsParametrosToMapManagebean)) {
            return false;
        }
        PublicadorWsParametrosToMapManagebean other = (PublicadorWsParametrosToMapManagebean) object;
        if ((this.idMetodoToMap == null && other.idMetodoToMap != null) || (this.idMetodoToMap != null && !this.idMetodoToMap.equals(other.idMetodoToMap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unico.ws.entity.PublicadorWsParametrosToMapManagebean[ idMetodoToMap=" + idMetodoToMap + " ]";
    }

    public PublicadorWsOperaciones getOperacion() {
        return operacion;
    }

    public PublicadorWsClases getIdClaseManageBean() {
        return idClaseManageBean;
    }

    public void setIdClaseManageBean(PublicadorWsClases idClaseManageBean) {
        this.idClaseManageBean = idClaseManageBean;
    }

   
    
    
    
}
