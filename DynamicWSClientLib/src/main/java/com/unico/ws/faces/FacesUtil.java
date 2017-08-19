/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.faces;

import com.unico.ws.UnicoWSServlet;
import com.unico.ws.entity.PublicadorWsMetodosOperaciones;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.FactoryFinder;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author T13237
 */
public class FacesUtil {
     public static FacesContext getFacesContext(
        HttpServletRequest request, HttpServletResponse response)
    {
        // Get current FacesContext.
        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Check current FacesContext.
        if (facesContext == null) {

            // Create new Lifecycle.
            LifecycleFactory lifecycleFactory = (LifecycleFactory)
             FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY); 
            Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

            // Create new FacesContext.
            FacesContextFactory contextFactory  = (FacesContextFactory)
                FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
            facesContext = contextFactory.getFacesContext(
                request.getSession().getServletContext(), request, response, lifecycle);

            // Create new View.
            UIViewRoot view = facesContext.getApplication().getViewHandler().createView(
                facesContext, "");
            facesContext.setViewRoot(view);                

            // Set current FacesContext.
            FacesContextWrapper.setCurrentInstance(facesContext);
        }

        return facesContext;
    }

    // Helpers -----------------------------------------------------------------------------------

    // Wrap the protected FacesContext.setCurrentInstance() in a inner class.
    private static abstract class FacesContextWrapper extends FacesContext {
        protected static void setCurrentInstance(FacesContext facesContext) {
            FacesContext.setCurrentInstance(facesContext);
        }
    }  
    
     public static Object findBean(String beanName, Class backingBeanCLass, HttpServletRequest request, HttpServletResponse response,ServletContext ctx) {
        FacesContext context = FacesContext.getCurrentInstance();
        Object instance;
        if (context == null) {
            context = FacesUtil.getFacesContext(request, response);
        }
        instance = context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
        if (instance == null) {
            try {
                instance = backingBeanCLass.newInstance();
                ctx.setAttribute(beanName, instance);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(UnicoWSServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return instance;

    }
    
   


}
