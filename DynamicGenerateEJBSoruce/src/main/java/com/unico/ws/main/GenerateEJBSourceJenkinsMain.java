/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.main;

import com.unico.ws.entity.ConstantesParametrizacionWs;
import com.unico.ws.entity.PublicadorWsEstructura;
import com.unico.ws.exception.DynamicWSException;
import com.unico.ws.helper.GenerarEJBWSClientHelper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T13237
 */
public class GenerateEJBSourceJenkinsMain {

    public static void main(String[] args) throws IOException {
        String rutaArchivoProperties = args[0];
        String rutaWorkspaceJenkins=args[1];
        GenerarEJBWSClientHelper helper = new GenerarEJBWSClientHelper(rutaArchivoProperties);

        ConstantesParametrizacionWs constantePropertiesEJBSource = helper.getConstanteParametrizacionWS("RUTA_PROPERTIES_EJB_SOURCE");
        String rutaPropertiesEJBSource = constantePropertiesEJBSource.getValorParametro();
        Properties properties = helper.cargarPropiedades(rutaPropertiesEJBSource);

        //Lectura de los directorios de generacion de fuentes en properties
        String rutaInterfazEJB = (String) properties.get("INTERFAZ_REMOTA_EJB_DIRECTORIO");
        String rutaProyectoEJB = (String) properties.get("PROYECTO_EJB_DIRECTORIO");
        String rutaEJB = rutaWorkspaceJenkins+"\\"+rutaProyectoEJB + "\\src\\main\\java";
        rutaInterfazEJB=rutaWorkspaceJenkins+"\\"+rutaInterfazEJB+"\\src\\main\\java";
        Logger.getLogger(GenerateEJBSourceJenkinsMain.class.getName()).info("Ruta1:".concat(rutaEJB));
         Logger.getLogger(GenerateEJBSourceJenkinsMain.class.getName()).info("Ruta2:".concat(rutaInterfazEJB));
        String paqueteEJB = (String) properties.get("EJB_PAQUETE");
        //obtiene todos los WS parametrizados 
        List<PublicadorWsEstructura> listaEstructura = helper.getListaWS();
        for (PublicadorWsEstructura estructuraWS : listaEstructura) {
            try {
                //Generacion de fuentes Interfaz e implementacion
                helper.generarClassFileWS(estructuraWS, rutaInterfazEJB, rutaEJB, paqueteEJB);
            } catch (DynamicWSException ex) {
                Logger.getLogger(GenerateEJBSourceJenkinsMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //
        //Generacion de descriptor de Weblogic            
        helper.generarWeblogicEJBDescriptor(rutaProyectoEJB, helper.getListaWS());

    }
}
