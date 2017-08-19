/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.manageBean;

import com.unico.ws.entity.PublicadorWsOperaciones;
import com.unico.ws.example.DynamicSessionBeanLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author T13237
 */
@ManagedBean
@SessionScoped
public class CalculadoraManagedBean {

      @EJB
    private DynamicSessionBeanLocal dynamicSessionBean;

 
    private Object calculadoraDTO;
    private final String NOMBRE_CLASE="com.prueba.pojo.CalculadoraDTO";
    
    /**
     * Creates a new instance of CalculadoraManagedBean
     */
    public CalculadoraManagedBean() {
    }
    
      public void cargarClaseDynamica() {

        calculadoraDTO = dynamicSessionBean.cargarClaseDynamica(NOMBRE_CLASE);
        System.out.println("Realizacion del cargue exitosamente");

    }
      
      
      public void realizarConsultaJPA(){
        List<PublicadorWsOperaciones> resultado=  dynamicSessionBean.buscarOperacionesWS();
        for(PublicadorWsOperaciones operacion:resultado){
            System.err.println("Las operaciones son: "+operacion);
        }
        
         dynamicSessionBean.setOperacionWS(resultado.get(1));
      }
    
      public void  realizarSumaRemota(){
         if(calculadoraDTO!=null){
            
             try {
               //   DataConnectorHelper.getInstance().invokeMethod(calculadoraDTO, NOMBRE_CLASE, new Class[]{Integer.class}, "setIdOperacion", new Integer[]{1});
               // DataConnectorHelper.getInstance().invokeMethod(calculadoraDTO, NOMBRE_CLASE, new Class[]{Integer.class}, "setDato1", new Integer[]{2});
               // DataConnectorHelper.getInstance().invokeMethod(calculadoraDTO, NOMBRE_CLASE, new Class[]{Integer.class}, "setDato2", new Integer[]{8});
                //  dynamicSessionBean.realizarSumaRemota(calculadoraDTO);
            } catch (Exception ex) {
                Logger.getLogger(CalculadoraManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
           
         }else{
             System.err.println("La instancia del obj es Nula!!");
         }
          
      } 
}
