/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;

/**
 *
 * @author T13257
 */
public class WeblogicEJBDescriptorDTO {

    private String ejbName;
    private String businessRemote;
    private String jndiName;

    public WeblogicEJBDescriptorDTO(String ejbName, String businessRemote, String jndiName) {
        this.ejbName = ejbName;
        this.businessRemote = businessRemote;
        this.jndiName = jndiName;
    }

    public String getEjbName() {
        return ejbName;
    }

    public void setEjbName(String ejbName) {
        this.ejbName = ejbName;
    }

    public String getBusinessRemote() {
        return businessRemote;
    }

    public void setBusinessRemote(String businessRemote) {
        this.businessRemote = businessRemote;
    }

    public String getJndiName() {
        return jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }
    
}
