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
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T13237
 */
public class GenerateEJBSourcerMain {

    public static final String INTERFAZ_REMOTA_EJB_DIRECTORIO = "INTERFAZ_REMOTA_EJB_DIRECTORIO";
    public static final String EJB_PAQUETE = "EJB_PAQUETE";
    public static final String EJB_DIRECTORIO = "EJB_DIRECTORIO";

    public static void main(String[] args) {
        try {
            System.out.println("Bienvenidos a la Generacion del EJB por el API WS Dynamico");

            System.out.println("Digite el nombre del ws a generar las operaciones");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String nombreWS = br.readLine();
            Properties prop_paramPersistence_Xml = new Properties();    
            prop_paramPersistence_Xml.load(new GenerateEJBSourcerMain().getClass().getResourceAsStream("/META-INF/conexionBD.properties"));
            /*System.out.println("Digite la ruta del properties para GenerateEJBSource");
            br = new BufferedReader(new InputStreamReader(System.in));
            String pathPropertiesJDBC = br.readLine();*/
            // Leer parametrizacion en la Base de datos para encontrar la ruta
            // del properties que tiene la configuracion de las rutas de 
            // generacion de archivos
            GenerarEJBWSClientHelper helper = new GenerarEJBWSClientHelper(prop_paramPersistence_Xml);
            ConstantesParametrizacionWs constantePropertiesEJBSource = helper.getConstanteParametrizacionWS("RUTA_PROPERTIES_EJB_SOURCE");
            String rutaPropertiesEJBSource = constantePropertiesEJBSource.getValorParametro();
            Properties properties = helper.cargarPropiedades(rutaPropertiesEJBSource);

            //Lectura de los directorios de generacion de fuentes en properties
            String rutaInterfazEJB = (String) properties.get("INTERFAZ_REMOTA_EJB_DIRECTORIO");
            String rutaProyectoEJB = (String) properties.get("PROYECTO_EJB_DIRECTORIO");
            String rutaEJB = rutaProyectoEJB + "\\src\\main\\java";
            String paqueteEJB = (String) properties.get("EJB_PAQUETE");
             //Obtener informacion de los metodos de operacion;
             PublicadorWsEstructura estructuraWS = helper.getEstructuraWS(nombreWS);
            //Generacion de fuentes Interfaz e implementacion
            helper.generarClassFileWS(estructuraWS, rutaInterfazEJB, rutaEJB, paqueteEJB);

            //Generacion de descriptor de Weblogic            
            helper.generarWeblogicEJBDescriptor(rutaProyectoEJB,helper.getListaWS());
        } catch (IOException ex) {
            Logger.getLogger(GenerateEJBSourcerMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DynamicWSException ex) {
            Logger.getLogger(GenerateEJBSourcerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
