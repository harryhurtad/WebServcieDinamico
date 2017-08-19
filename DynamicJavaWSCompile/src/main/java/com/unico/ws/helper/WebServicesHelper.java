/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;

import com.unico.ws.entity.PublicadorWsEstructura;
import com.unico.ws.webservices.ClassDynamicWSDTO;
import com.unico.ws.webservices.InterfaceToPublishDTO;
import com.unico.ws.webservices.MethodToPublishWSDTO;
import com.unico.ws.webservices.ParameterWSDynamicDTO;
import com.unico.ws.webservices.WebServicesToPublishDTO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T13237
 */
public class WebServicesHelper {

    public static void main(String[] args) {
        try {
            WebServicesHelper instance = new WebServicesHelper();

            Map<Integer, InterfaceToPublishDTO> mapWebServicesInterfaceToPublish = new HashMap<>();
            Map<Integer, WebServicesToPublishDTO> mapWebServicesWebToPublish = new HashMap<>();
            WebServicesHelper.loadInfoDynamicInterfaceWS(mapWebServicesInterfaceToPublish);
            WebServicesHelper.loadInfoDynamicServiceWS(mapWebServicesWebToPublish);
            String dirScr = "E:\\temp\\DynamicClassCompile\\scr";
            //Crear Interface WS
            instance.generarClassFileWS(dirScr, mapWebServicesInterfaceToPublish.get(1));
            //Crear WS
//            instance.generarClassServiceFileWS(dirScr, mapWebServicesWebToPublish.get(1));

        } catch (IOException ex) {
            Logger.getLogger(WebServicesHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loadInfoDynamicInterfaceWS(Map<Integer, InterfaceToPublishDTO> mapWebServicesToPublish) {
        //Crean las clases  a utilizar en los imports
        ClassDynamicWSDTO cdwsdto1 = new ClassDynamicWSDTO(1, "com.unico.ws.webservice.CalculadoraDTO", "CalculadoraDTO", new File("E:\\temp\\DynamicClassCompile\\schema\\calculadora.xsd"), null);
        ClassDynamicWSDTO cdwsdto2 = new ClassDynamicWSDTO(2, "com.unico.ws.webservice.EmployeeDTO", "EmployeeDTO", new File("E:\\temp\\DynamicClassCompile\\schema\\employee.xsd"), new String[]{"com.unico.ws.webservice.CiudadDTO"});
        ClassDynamicWSDTO cdwsdto3 = new ClassDynamicWSDTO(3, "java.lang.Integer", "Integer", null, null);
        ClassDynamicWSDTO cdwsdto4 = new ClassDynamicWSDTO(4, "com.unico.ws.helper.WSDynamicUtil", "WebServicesHelper", null, null);
        ClassDynamicWSDTO cdwsdto5 = new ClassDynamicWSDTO(5, "javax.jws.WebService", "WebService", null, null);
        ClassDynamicWSDTO cdwsdto6 = new ClassDynamicWSDTO(5, "javax.jws.WebMethod", "WebMethod", null, null);

        //Define las operaciones del WS
        ParameterWSDynamicDTO paramOper1 = new ParameterWSDynamicDTO(1, "entrada", cdwsdto1);
        MethodToPublishWSDTO operacion1 = new MethodToPublishWSDTO(1, "realizarSuma", null, new ParameterWSDynamicDTO[]{paramOper1});

        ParameterWSDynamicDTO param1Oper2 = new ParameterWSDynamicDTO(1, "dato1", cdwsdto3);
        ParameterWSDynamicDTO param2Oper2 = new ParameterWSDynamicDTO(2, "dato2", cdwsdto3);

        MethodToPublishWSDTO operacion2 = new MethodToPublishWSDTO(2, "realizarResta", null, new ParameterWSDynamicDTO[]{param1Oper2, param2Oper2});

        ParameterWSDynamicDTO paramOper3 = new ParameterWSDynamicDTO(1, "empleado", cdwsdto2);
        MethodToPublishWSDTO operacion3 = new MethodToPublishWSDTO(3, "buscarEmpleado", null, new ParameterWSDynamicDTO[]{paramOper3});
        ArrayList<MethodToPublishWSDTO> listaOperaciones = new ArrayList();
        listaOperaciones.add(operacion1);
        listaOperaciones.add(operacion2);
        listaOperaciones.add(operacion3);
//Se crea el WS
        InterfaceToPublishDTO interfaceToPublish = new InterfaceToPublishDTO(new ClassDynamicWSDTO[]{cdwsdto1, cdwsdto2, cdwsdto3, cdwsdto4, cdwsdto5, cdwsdto6},
                "com.unico.ws.webservice", "UnicoExampleWS", "http://com.prueba.unico.ws/", listaOperaciones);
        mapWebServicesToPublish.put(1, interfaceToPublish);
    }

    public static void loadInfoDynamicServiceWS(Map<Integer, WebServicesToPublishDTO> mapWebServicesToPublish) {
        //Crean las clases  a utilizar en los imports
        ClassDynamicWSDTO cdwsdto1 = new ClassDynamicWSDTO(1, "com.unico.ws.webservice.CalculadoraDTO", "CalculadoraDTO", new File("E:\\temp\\DynamicClassCompile\\schema\\calculadora.xsd"), null);
        ClassDynamicWSDTO cdwsdto2 = new ClassDynamicWSDTO(2, "com.unico.ws.webservice.EmployeeDTO", "EmployeeDTO", new File("E:\\temp\\DynamicClassCompile\\schema\\employee.xsd"), new String[]{"com.unico.ws.webservice.CiudadDTO"});
        ClassDynamicWSDTO cdwsdto3 = new ClassDynamicWSDTO(3, "java.lang.Integer", "Integer", null, null);
        ClassDynamicWSDTO cdwsdto4 = new ClassDynamicWSDTO(4, "com.unico.ws.helper.WSDynamicUtil", "WebServicesHelper", null, null);
       // ClassDynamicWSDTO cdwsdto5 = new ClassDynamicWSDTO(5, "com.unico.ws.webservice.UnicoExampleWS", "UnicoExampleWS", null, null);
        ClassDynamicWSDTO cdwsdto6 = new ClassDynamicWSDTO(6, "javax.jws.WebService", "WebService", null, null);
        ClassDynamicWSDTO cdwsdto7 = new ClassDynamicWSDTO(7, "com.unico.ws.interfaces.DynamicExcecutionSessionBeanRemote", "DynamicExcecutionSessionBeanRemote", null, null);
        ClassDynamicWSDTO cdwsdto8 = new ClassDynamicWSDTO(8, "javax.ejb.EJB", "EJB", null, null);
        ClassDynamicWSDTO cdwsdto9 = new ClassDynamicWSDTO(9, "java.util.logging.Level", "Level", null, null);
        ClassDynamicWSDTO cdwsdto10 = new ClassDynamicWSDTO(10, "java.util.logging.Logger", "Logger", null, null);
        ClassDynamicWSDTO cdwsdto11 = new ClassDynamicWSDTO(11, "javax.naming.InitialContext", "InitialContext", null, null);
        ClassDynamicWSDTO cdwsdto12 = new ClassDynamicWSDTO(12, "javax.naming.NamingException", "NamingException", null, null);
        ClassDynamicWSDTO cdwsdto13 = new ClassDynamicWSDTO(13, "javax.jws.WebMethod", "WebMethod", null, null);
//Define las operaciones del WS
        ParameterWSDynamicDTO paramOper1 = new ParameterWSDynamicDTO(1, "entrada", cdwsdto1);
        MethodToPublishWSDTO operacion1 = new MethodToPublishWSDTO(1, "realizarSuma", null, new ParameterWSDynamicDTO[]{paramOper1});

        ParameterWSDynamicDTO param1Oper2 = new ParameterWSDynamicDTO(1, "dato1", cdwsdto3);
        ParameterWSDynamicDTO param2Oper2 = new ParameterWSDynamicDTO(2, "dato2", cdwsdto3);

        MethodToPublishWSDTO operacion2 = new MethodToPublishWSDTO(2, "realizarResta", null, new ParameterWSDynamicDTO[]{param1Oper2, param2Oper2});

        ParameterWSDynamicDTO paramOper3 = new ParameterWSDynamicDTO(1, "empleado", cdwsdto2);
        MethodToPublishWSDTO operacion3 = new MethodToPublishWSDTO(3, "buscarEmpleado", null, new ParameterWSDynamicDTO[]{paramOper3});
        ArrayList<MethodToPublishWSDTO> listaOperaciones = new ArrayList();
        listaOperaciones.add(operacion1);
        listaOperaciones.add(operacion2);
        listaOperaciones.add(operacion3);
//Se crea el WS
        WebServicesToPublishDTO webServicesUnico = new WebServicesToPublishDTO("UnicoExampleWService",
                "UnicoExampleWSPort", "http://com.prueba.unico.ws/",
                "com.unico.ws.webservice.UnicoExampleWS",
                "com.unico.ws.webservice", "UnicoExampleWS",
                listaOperaciones,
                "http://localhost:17001/UnicoTestAPPWS-web/CalculadoraServices",
                new ClassDynamicWSDTO[]{cdwsdto1, cdwsdto2, cdwsdto3, cdwsdto4,  cdwsdto6, cdwsdto7, cdwsdto8, cdwsdto9, cdwsdto10, cdwsdto11, cdwsdto12,cdwsdto13});

        mapWebServicesToPublish.put(1, webServicesUnico);
    }

    public void generarClassFileWS(String dirScr, InterfaceToPublishDTO interfaceToPublish) throws IOException {

        try {

            Map root = new HashMap();
            //Pasa la plantilla a un directorio temporal
            File dirTemp = File.createTempFile("dynamicWS_freemaker", "");
            createDirectory(dirTemp);
            InputStream in = this.getClass().getResourceAsStream("/templates/TemplateClassDynamicInterfaceWS.txt");
            int dato;
            File output = new File(dirTemp, "TemplateClassDynamicInterfaceWS.txt");
            try (OutputStream outFile = new FileOutputStream(output)) {
                while ((dato = in.read()) != -1) {
                    outFile.write(dato);
                }
            }
            //Comfigura el api de FREEMAKER
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_24);
            cfg.setDirectoryForTemplateLoading(dirTemp);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);

            /* Get the template (uses cache internally) */
            Template temp = cfg.getTemplate(output.getName());
            //Pasa los parametros para la generacion de la clase
            root.put("package", interfaceToPublish.getPackage_ws());
            root.put("imports", interfaceToPublish.getListImports());
            root.put("name_class", interfaceToPublish.getNameInterface());
            root.put("operaciones", interfaceToPublish.getListaOperaciones().toArray());
            root.put("targetNameSpace", interfaceToPublish.getTargetNamespace());
            /* Merge data-model with template */
            String nameClassFile = interfaceToPublish.getPackage_ws().replaceAll("\\.", "/") + "//" + interfaceToPublish.getNameInterface() + ".java";
            File sourceFile = new File(dirScr, nameClassFile);

            //Geenra el archivo
            try (Writer out = new OutputStreamWriter(new FileOutputStream(sourceFile))) {
                //Geenra el archivo
                temp.process(root, out);
            }

        } catch (TemplateException ex) {
            Logger.getLogger(WebServicesHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void generarClassServiceFileWS(String dirScr,Set<String> listImports, PublicadorWsEstructura webServicesUnico) throws IOException {

        try {

            Map root = new HashMap();

            //Pasa la plantilla a un directorio temporal
            File dirTemp = File.createTempFile("dynamicWS_freemaker", "");
            createDirectory(dirTemp);
            InputStream in = this.getClass().getResourceAsStream("/templates/TemplateClassDynamicServiceWS.txt");
            int dato;
            File output = new File(dirTemp, "TemplateClassDynamicServiceWS.txt");
            try (OutputStream outFile = new FileOutputStream(output)) {
                while ((dato = in.read()) != -1) {
                    outFile.write(dato);
                }
            }
            //Comfigura el api de FREEMAKER
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_24);
            cfg.setDirectoryForTemplateLoading(dirTemp);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);

            /* Get the template (uses cache internally) */
            Template temp = cfg.getTemplate(output.getName());
            //Pasa los parametros para la generacion de la clase
            root.put("package", webServicesUnico.getPackageWs());
            root.put("imports", listImports);
            root.put("name_service", webServicesUnico.getNombreWs());
           // root.put("name_interface", webServicesUnico.getNameInterface());
            root.put("portName", webServicesUnico.getPortName());
            root.put("jndi_ejb_interface", webServicesUnico.getIdClassEJBRemote().getJndi());   
            root.put("ejbInterfaceRemote", webServicesUnico.getIdClassEJBRemote().getNombreCLase());   
            
             
         //   root.put("endpointInterface", webServicesUnico.getEndpointInterface());
            root.put("operaciones", webServicesUnico.getPublicadorWsOperacionesCollection());
            root.put("targetNameSpace", webServicesUnico.getTargetNamespace());
            /* Merge data-model with template */
            String nameClassFile = webServicesUnico.getPackageWs().replaceAll("\\.", "/") + "//" + webServicesUnico.getNombreWs() + ".java";
            //Crea el directorio del WS a crear
            File directorySource=new File(dirScr, webServicesUnico.getPackageWs().replaceAll("\\.", "/"));
            if (!directorySource.exists()) {
                directorySource.mkdirs();
            }
           // createDirectory(directorySource);
            
            File sourceFile = new File(dirScr, nameClassFile);

            //Geenra el archivo
            try (Writer out = new OutputStreamWriter(new FileOutputStream(sourceFile))) {
                //Geenra el archivo
                temp.process(root, out);
            }

        } catch (TemplateException ex) {
            Logger.getLogger(WebServicesHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param drecotry
     * @throws IOException
     */
    private void createDirectory(File drecotry) throws IOException {

        if (drecotry.exists()) {
            delete(drecotry);
        }

        if (!drecotry.exists() && !(drecotry.mkdirs())) {
            throw new IOException("Could not create temp directory: " + drecotry.getAbsolutePath());
        }

    }

    /**
     * Elimina los archivos y carpetas generadas
     *
     * @param file
     * @throws IOException
     */
    private void delete(File file)
            throws IOException {

        if (file.isDirectory()) {

            //directory is empty, then delete it
            if (file.list().length == 0) {

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            } else {

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        } else {
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }

}
