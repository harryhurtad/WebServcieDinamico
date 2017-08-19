/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.mb;

import com.unico.ws.helper.UsuarioDTO;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author T13237
 */
@ManagedBean(name = "loginJSFManagedBean")
@SessionScoped
public class LoginJSFManagedBean implements Serializable {

    private String nombreUsuario;
    private String password;
    private UsuarioDTO usuarioLogeado;
    private Set<UsuarioDTO> listUsuario;

    /**
     * Creates a new instance of LoginJSFManagedBean
     */
    public LoginJSFManagedBean() {
    }

    @PostConstruct
    public void Init() {
        listUsuario = new HashSet<>();
        //Usuario 1
        UsuarioDTO user1 = new UsuarioDTO(1, "pepe", "pepe123");
        UsuarioDTO user2 = new UsuarioDTO(2, "juan", "juan123");
        UsuarioDTO user3 = new UsuarioDTO(3, "camilo", "camilo123");
        UsuarioDTO user4 = new UsuarioDTO(4, "angela", "angela123");

        listUsuario.add(user1);
        listUsuario.add(user2);
        listUsuario.add(user3);
        listUsuario.add(user4);
    }

    public void realizarLogin() {

        if (this.nombreUsuario != null && !this.nombreUsuario.equals("") && this.password != null && !this.password.equals("")) {
            //Construlle un obj UsuarioDTO temportal
            UsuarioDTO usuarioToLogin = new UsuarioDTO(1, nombreUsuario, password);

            if (listUsuario.contains(usuarioToLogin)) {
                //
                Iterator<UsuarioDTO> elementos = listUsuario.iterator();
                while (elementos.hasNext()) {
                    UsuarioDTO usuarioBD = elementos.next();
                    if (usuarioBD.equals(usuarioToLogin)) {
                        usuarioBD.setFehcaInicioSesion(new Date());
                        setUsuarioLogeado(usuarioBD);
                        System.out.println("Usuario logeado!!!");
                        break;
                    }
                }

            }else{
                System.out.println("Usuario o contraseña Incorrectos!!!");
            }

        } else {
            System.out.println("Datos Incorrectos!!!");

        }

    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsuarioDTO getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(UsuarioDTO usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

}
