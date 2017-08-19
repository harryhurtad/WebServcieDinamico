/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.manageBean;


import com.unico.ws.constantes.DynamicWS;
import com.unico.ws.constantes.DynamicWSMessageException;
import com.unico.ws.dto.PuplishWSDTO;
import com.unico.ws.exception.DynamicWSException;

import com.unico.ws.interfaces.PublicDynamicWSSessionBeanLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author T13237
 */
@ManagedBean
@ApplicationScoped
public class PublishWSJSFManagedBean {

  

    private List<PuplishWSDTO> listWSToPublish;
    private List<PuplishWSDTO> listWSSelected;
    private PuplishWSDTO selectWSTOPublish;
    @EJB
    private PublicDynamicWSSessionBeanLocal bean; 
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(PublishWSJSFManagedBean.class);
    
    
     /**
     * Creates a new instance of PublishWSJSFManagedBean
     */
    public PublishWSJSFManagedBean() {
    }
    
    public String realizarPublicacionWS(){
       String navigation="";
        try {
            listWSToPublish=  bean.publicarWS();
            navigation="publishWS.xhtml?faces-redirect=true";
        } catch (DynamicWSException  ex) {
            LOGGER.error(DynamicWSMessageException.ERROR_PUPLISHS_ALL_WS, ex);
            FacesContext context = FacesContext.getCurrentInstance();         
            context.addMessage("ERROR", new FacesMessage(FacesMessage.SEVERITY_ERROR,"", ex.getMessage()) );
        }
        return navigation;
   
    }
    public boolean isRenderedTableWS(){
        return listWSToPublish!=null &&!listWSToPublish.isEmpty();
    }
      public String recargarContexto(){
          bean.loadDynamicPublicWSContext();
          FacesContext context = FacesContext.getCurrentInstance();         
          context.addMessage("INFO", new FacesMessage(FacesMessage.SEVERITY_INFO,"", DynamicWS.RECARGA_CONTEXTO ) );
           LOGGER.info("******->Recarga del contexto Exitosamente!!!");
          return "index.xhtml";
      }
    
    public void deshabilitarWS(){
        listWSToPublish=bean.deshabilitarWebServices(listWSSelected);
    }
    
    public void habilitarWS(){
    
        try {
            listWSToPublish=bean.habilitarWebServices(listWSSelected);
        } catch (Exception ex) {
             LOGGER.error(DynamicWSMessageException.ERROR_ENABLE_WS, ex);
            FacesContext context = FacesContext.getCurrentInstance();   
            context.addMessage("ERROR", new FacesMessage(FacesMessage.SEVERITY_ERROR,"",  DynamicWSMessageException.ERROR_ENABLE_WS) );
        }
    }
  /*  public void realizarInvocacionJNDI(){
        try {
          //  InitialContext ic = new InitialContext();
            DynamicExcecutionSessionBeanRemote remote = (DynamicExcecutionSessionBeanRemote)new InitialContext().lookup("ejb/DynamicExcecutionSessionBean");
            System.out.println("valor bean:"+remote);
        } catch (NamingException ex) {
            Logger.getLogger(PublishWSJSFManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }*/

    public List<PuplishWSDTO> getListWSToPublish() {
        return listWSToPublish;
    }

    public void setListWSToPublish(List<PuplishWSDTO> listWSToPublish) {
        this.listWSToPublish = listWSToPublish;
    }

    public List<PuplishWSDTO> getListWSSelected() {
        return listWSSelected;
    }

    public void setListWSSelected(List<PuplishWSDTO> listWSSelected) {
        this.listWSSelected = listWSSelected;
    }

    public PuplishWSDTO getSelectWSTOPublish() {
        return selectWSTOPublish;
    }

    public void setSelectWSTOPublish(PuplishWSDTO selectWSTOPublish) {
        this.selectWSTOPublish = selectWSTOPublish;
    }
    
     
    

   
    
}
