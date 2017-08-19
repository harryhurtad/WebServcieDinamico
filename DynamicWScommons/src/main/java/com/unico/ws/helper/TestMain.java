/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;

import com.unico.ws.constantes.StatusExecutionWSEnum;
import com.unico.ws.entity.PublicadorWsClases;
import com.unico.ws.entity.PublicadorWsDependenciaClases;
import com.unico.ws.entity.PublicadorWsEstadoInvocOper;
import com.unico.ws.entity.PublicadorWsOperaciones;
import com.unico.ws.exception.DynamicWSException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author T13237
 */
public class TestMain {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.unico.ws_Dynamic-commonsWS_jar_1.0PU");
    
    public static void main(String[] args) {
        try {
            // TestMain main =new TestMain();
            //  main.persist();
//      ValueChangeEvent changeEventTmp=new ValueChangeEvent(null, null, "1");
Object  htmlSelectOneMenu=WSDynamicUtil.getInstance().createNewInstance(null, null, "javax.faces.component.html.HtmlSelectOneMenu",TestMain.class.getClassLoader());
// HtmlSelectOneMenu htmlSelectOneMenu=new HtmlSelectOneMenu();
Object stringValue=WSDynamicUtil.getInstance().createNewInstance(new String[]{"java.lang.String"},new Object[]{"1"},"java.lang.String",TestMain.class.getClassLoader());

Object changeEvent=WSDynamicUtil.getInstance().createNewInstance(new String[]{"javax.faces.component.UIComponent","java.lang.Object","java.lang.Object"},
        new Object[]{htmlSelectOneMenu,null,stringValue},"javax.faces.event.ValueChangeEvent",TestMain.class.getClassLoader());
if(changeEvent instanceof ValueChangeEvent){
    ValueChangeEvent tmp=(ValueChangeEvent)changeEvent;
    System.out.println("Valor ValueChangeEvent: "+ tmp.getNewValue());
    
}


//String
// ValueChangeEvent changeEvent=new ValueChangeEvent(htmlSelectOneMenu, null, "1");
        } catch (DynamicWSException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    public void persist() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            //em.persist(object);
           // Query q=em.createNamedQuery("PublicadorWsDependenciaClases.findAll");
           // List<PublicadorWsDependenciaClases> listR=q.getResultList();
            Query q=em.createNamedQuery("PublicadorWsOperaciones.findByIdOperacion");  
            q.setParameter("idOperacion", 1);
           PublicadorWsOperaciones idOperacion=(PublicadorWsOperaciones)q.getSingleResult();
           PublicadorWsEstadoInvocOper intancia=new PublicadorWsEstadoInvocOper(idOperacion, "1234ggg", StatusExecutionWSEnum.INICIO);
          intancia.setFechaInicio(new Date());           
        //Crea el nuevo registro PublicadorWsEstadoInvocOper
       
           em.persist(intancia);
           // PublicadorWsClases entidad=(PublicadorWsClases)q.getSingleResult();
           // entidad.getPublicadorWsClasesDependenciaList();
           q.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    
}
