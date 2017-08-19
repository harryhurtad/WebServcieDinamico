/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.ejb;

import com.unico.ws.entity.PublicadorWsClases;
import com.unico.ws.entity.PublicadorWsEstructura;
import com.unico.ws.entity.PublicadorWsMetodosOperaciones;
import com.unico.ws.entity.PublicadorWsOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosMetodosOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosOperaciones;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author T13237
 */
public class TestingJPAMainClass {

    public static void main(String[] args) {
        TestingJPAMainClass instance = new TestingJPAMainClass();
        //instance.getInfo();
       // instance.getMetodosOperaciones();
       // instance.evaluateMethodOperation();
       instance.findPublicadorWsParametrosMetodosOperaciones();
    }

    public void getInfo() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.unico.ws_Dynamic-commonsWS_jar_1.0PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            //  em.persist(object);
            Query q = em.createNamedQuery("PublicadorWsEstructura.findByIdWs");
            q.setParameter("idWs", 1);
            PublicadorWsEstructura estructura = (PublicadorWsEstructura) q.getSingleResult();
            System.out.println(""+listImports(estructura.getPublicadorWsOperacionesCollection(), estructura.getIdClassEJBRemote()));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void getMetodosOperaciones(){
    
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.unico.ws_Dynamic-commonsWS_jar_1.0PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            //  em.persist(object);
            Query q = em.createNamedQuery("PublicadorWsMetodosOperaciones.findAll");
          
            List<PublicadorWsMetodosOperaciones> listMetodos = q.getResultList();
            System.out.println("lista Metodos"+listMetodos);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public Set<String> listImports(List<PublicadorWsOperaciones> listOperaciones, PublicadorWsClases ejbRomet) {
        Set<String> listaImports = new HashSet<>();
         String classNameToImport;
        for (PublicadorWsOperaciones operacion : listOperaciones) {
            for (PublicadorWsParametrosOperaciones parametro : operacion.getPublicadorWsParametrosOperacionesList()) {
                 classNameToImport = parametro.getIdClaseValor().getPackageClass() +"."+ parametro.getIdClaseValor().getNombreCLase();
                if (!listaImports.contains(classNameToImport)) {
                    listaImports.add(classNameToImport);
                }

            }
            //Evalua la clase de retorno
            if(operacion.getIdClaseRetornoOperacion()!=null){
                classNameToImport=operacion.getIdClaseRetornoOperacion().getPackageClass()+"."+operacion.getIdClaseRetornoOperacion().getNombreCLase();
                if (!listaImports.contains(classNameToImport)) {
                    listaImports.add(classNameToImport);
                }
            }
             
           
        }
        listaImports.add(ejbRomet.getPackageClass() +"."+ ejbRomet.getNombreCLase());
        return listaImports;
    }

    
    
    public void findPublicadorWsParametrosMetodosOperaciones(){
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.unico.ws_Dynamic-commonsWS_jar_1.0PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
                 Query q = em.createNamedQuery("PublicadorWsParametrosMetodosOperaciones.findAll");
         List<PublicadorWsParametrosMetodosOperaciones>  listInfo=   q.getResultList();
            System.out.println(""+listInfo);
             em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    } 
    
    public void evaluateMethodOperation(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.unico.ws_Dynamic-commonsWS_jar_1.0PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            //  em.persist(object);
            Query q = em.createNamedQuery("PublicadorWsMetodosOperaciones.findByIdOperacion");
            q.setParameter("idOperacion", 4);
          
            List<PublicadorWsMetodosOperaciones> listMetodos = q.getResultList();
            for(PublicadorWsMetodosOperaciones metodos:listMetodos){
                if(metodos.getMetodosOperacionesJsfList()!=null){
                    System.out.println("Si tiene evento especial");
                }else{
                    System.out.println("No tiene evento especial");
                }
            }
           
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    
    }
}
