/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.faces;

import com.unico.ws.entity.MetodosOperacionesJsf;
import com.unico.ws.entity.PublicadorWsMetodosOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosMetodosOperaciones;
import com.unico.ws.exception.DynamicWSException;
import com.unico.ws.helper.WSDynamicUtil;
import com.unico.ws.operation.OperationRequestParamWS;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author T13237
 */
public class JSFWSHelper {

    /**
     * verifica si el metodo a invocar es un evento de tipo JSF
     * @param methodToInvoke    
     * @return 
     */
    public boolean isMethodEventJSF(PublicadorWsMetodosOperaciones methodToInvoke) {
        boolean respuesta = false;
        if (methodToInvoke.getMetodosOperacionesJsfList() != null && !methodToInvoke.getMetodosOperacionesJsfList().isEmpty()) {
            //Se realiza la invocacion del evento            

            respuesta = true;
        }

        return respuesta;
    }

    /**
     * 
     * @param methodToInvoke
     * @param paramWS
     * @param request
     * @param response
     * @param ctx 
     */
     public void realizarInvocacionMetodoEventJSF(PublicadorWsMetodosOperaciones methodToInvoke, Map<Integer, OperationRequestParamWS>mapParametrosWS, HttpServletRequest request, HttpServletResponse response, ServletContext ctx) {
         //Se busca la instancia del manageBean           
            Object instanceManageBeanJSF;
            String manageBeanClass = methodToInvoke.getIdClasePrincipalMetdOperacion().getPackageClass() + "." + methodToInvoke.getIdClasePrincipalMetdOperacion().getNombreCLase();
            try {
                instanceManageBeanJSF = FacesUtil.findBean(methodToInvoke.getIdClasePrincipalMetdOperacion().getAliasClase(), Class.forName(manageBeanClass), request, response, ctx);
                //SE evalua los tipo de eventos
                List<PublicadorWsParametrosMetodosOperaciones> listPArametros = methodToInvoke.getPublicadorWsParametrosMetodosOperacionesList();
                //Evento ACtionEvent
                Class[] parametrosMetodo = new Class[listPArametros.size()];

                int contador = 0;
                for (PublicadorWsParametrosMetodosOperaciones parametroMetodoOper : listPArametros) {
                    parametrosMetodo[contador] = Class.forName(parametroMetodoOper.getIdClase().getPackageClass() + "." + parametroMetodoOper.getIdClase().getNombreCLase());
                    contador++;
                }

                Class infoClassManageBean = instanceManageBeanJSF.getClass();

                Method metodoInfo = infoClassManageBean.getDeclaredMethod(methodToInvoke.getMetodoOperacion(), parametrosMetodo);
                //Si es el metodo es del tipo ValueChangeEvent
                if (metodoInfo.getParameterTypes() != null && metodoInfo.getParameterTypes().length == 1 && metodoInfo.getParameterTypes()[0].equals(ValueChangeEvent.class) && metodoInfo.getReturnType().equals(Void.TYPE)) {
                    //Crea el objecto de tipo HTML
                    MetodosOperacionesJsf jsfIvokeParam = methodToInvoke.getMetodosOperacionesJsfList().get(0);
                    Object instanceHTML = WSDynamicUtil.getInstance().createNewInstance(null, null, jsfIvokeParam.getIdClassUIComponentJSF().getPackageClass() + "." + jsfIvokeParam.getIdClassUIComponentJSF().getNombreCLase(),this.getClass().getClassLoader());
                    //Obtviene la instancia de parametro de entrada del WS
                    Object instanceParamWS=mapParametrosWS.get(jsfIvokeParam.getIdParametroOperacionWS().getOrdenParametro()).getInstance();
                    //Extrae el valor del parametro del obj del WS
                    Object valueNewEvent = WSDynamicUtil.getInstance().invokeMethod(instanceParamWS, instanceParamWS.getClass().getName(), new Class[]{}, jsfIvokeParam.getNombreGetAtributoParamWS(), null);
                    //Crea el objecto ValueChangeEvent
                    String[] parametrosClassConstructor = new String[3];
                    parametrosClassConstructor[0] = "javax.faces.component.UIComponent";
                    parametrosClassConstructor[1] = "java.lang.Object";
                    parametrosClassConstructor[2] = "java.lang.Object";

                    Object[] parametrosValueConst = new Object[3];
                    parametrosValueConst[0] = instanceHTML;
                    parametrosValueConst[1] = null;
                    parametrosValueConst[2] = valueNewEvent;

                    Object valueChangeEvent = WSDynamicUtil.getInstance().createNewInstance(parametrosClassConstructor, parametrosValueConst,
                            jsfIvokeParam.getIdClassParamEvent().getPackageClass() + "." + jsfIvokeParam.getIdClassParamEvent().getNombreCLase()
                    ,this.getClass().getClassLoader());

                    //Se invoca la accion correspondiente en el managebean
                    WSDynamicUtil.getInstance().invokeMethod(instanceManageBeanJSF, instanceManageBeanJSF.getClass().getName(), new Class[]{valueChangeEvent.getClass()}, methodToInvoke.getMetodoOperacion(), new Object[]{valueChangeEvent});
                    System.out.println("Se realiza la invocacion del metodo public void xxxx( ValueChangeEvent event) de forma exitosa!!");
                }

            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | DynamicWSException ex) {
                Logger.getLogger(JSFWSHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
     
     }
    
    /*  public boolean evaluarMethodoJSF( listParamJSF,Object InstanceManageBeanJSF){
   
       //Evalua si es un metodo de tipo action event
       
       return true;
   } */
}
