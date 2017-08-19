/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.webservice;

import java.io.Serializable;

/**
 *
 * @author T13237
 */
public class RespuestaDTO implements Serializable {
        private final static long serialVersionUID = 12343L;
        protected String codigo;
         protected OperacionDTO operacionDTO;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public OperacionDTO getOperacionDTO() {
        return operacionDTO;
    }

    public void setOperacionDTO(OperacionDTO operacionDTO) {
        this.operacionDTO = operacionDTO;
    }
         
         
}
