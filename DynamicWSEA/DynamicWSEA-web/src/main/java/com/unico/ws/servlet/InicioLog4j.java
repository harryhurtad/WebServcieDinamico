/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.servlet;

import com.unico.ws.constantes.DynamicWS;
import com.unico.ws.interfaces.DynamicParametrizacionSessionBeanRemote;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author T13257
 */
public class InicioLog4j extends HttpServlet {

    public static final Logger LOGGER = Logger.getLogger(InicioLog4j.class);
    private String rutaLog4jProperties;
    private static final long serialVersionUID = 1L;
    @EJB(lookup = "ejb/DynamicParametrizacionSessionBean")
    private DynamicParametrizacionSessionBeanRemote dynamicParametrizacionSessionBean;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init() {

        Properties configProps = new Properties();
        try {
            //dynamicParametrizacionSessionBean = (DynamicParametrizacionSessionBeanLocal) new InitialContext().lookup("java:global:DynamicParametrizacionSessionBean");
            rutaLog4jProperties = dynamicParametrizacionSessionBean.buscarParametroPorNombre(DynamicWS.RUTA_PROPERTIES_LOG4J).getValorParametro();
            configProps.clear();
            String rutaLog4j = dynamicParametrizacionSessionBean.buscarParametroPorNombre(DynamicWS.RUTA_LOG_WS).getValorParametro();
            System.setProperty("ruta.log4j", rutaLog4j);
            FileInputStream fis = new FileInputStream(new File(rutaLog4jProperties));
            configProps.load(fis);
            PropertyConfigurator.configure(configProps);
            LOGGER.info("iniciando Log del Publicador Dinámico de Web Services .... ");
        } catch (IOException e) {
            LOGGER.error("Error al configurar el Log usando la librería LOG4J", e);
        } catch (Exception e) {
            LOGGER.error("Error al configurar el Log usando la librería LOG4J", e);
        }
    }
}
