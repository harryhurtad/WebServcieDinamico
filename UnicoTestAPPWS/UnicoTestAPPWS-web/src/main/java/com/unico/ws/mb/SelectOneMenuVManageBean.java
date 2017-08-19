/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.mb;


import com.unico.ws.webservice.UsuarioDTO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author T13237
 */
@ManagedBean(name = "selectOneMenuVManageBean")
@SessionScoped
public class SelectOneMenuVManageBean implements Serializable {

    private String valor;
    private List<UsuarioDTO> listaUsuarioSel;
    /**
     * Creates a new instance of SelectOneMenuVManageBean
     */
    public SelectOneMenuVManageBean() {
    }
    
    public void selectionOption(ValueChangeEvent event){  
        HtmlSelectOneMenu htmlSelectOneMenu=new HtmlSelectOneMenu();
        ValueChangeEvent changeEvent=new ValueChangeEvent(htmlSelectOneMenu, null, "1");
        System.out.println("here "+event.getNewValue());
    }
    
    public void validarUsuarioSeleccionados(){
        
        if(getListaUsuarioSel() !=null){
        
            for(UsuarioDTO usuario:getListaUsuarioSel() ){
                System.out.println("Usuario sel-> nombre:"+usuario.getUsuario()+"password: "+usuario.getClave());
            }
        }
    
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<UsuarioDTO> getListaUsuarioSel() {
        return listaUsuarioSel;
    }

    public void setListaUsuarioSel(List<UsuarioDTO> listaUsuarioSel) {
        this.listaUsuarioSel = listaUsuarioSel;
    }
    
    
    
    
}
