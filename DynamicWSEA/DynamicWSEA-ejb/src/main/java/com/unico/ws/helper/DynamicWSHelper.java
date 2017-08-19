/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;

import com.sun.codemodel.internal.JCodeModel;
import com.sun.tools.internal.xjc.api.S2JJAXBModel;
import com.sun.tools.internal.xjc.api.SchemaCompiler;
import com.sun.tools.internal.xjc.api.XJC;
import com.unico.ws.constantes.DynamicWSMessageException;
import com.unico.ws.dto.PuplishWSDTO;
import com.unico.ws.ejb.PublicDynamicWSSessionBean;
import com.unico.ws.entity.PublicadorWsClases;
import com.unico.ws.entity.PublicadorWsDependenciaClases;
import com.unico.ws.entity.PublicadorWsEstructura;
import com.unico.ws.entity.PublicadorWsOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosOperaciones;
import com.unico.ws.exception.CompilerWSClassException;
import com.unico.ws.exception.DynamicWSException;
import com.unico.ws.interfaces.DynamicWSContextSingletonBeanLocal;
import com.unico.ws.main.GenerateJaxbMain;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Endpoint;
import org.xml.sax.InputSource;

/**
 *
 * @author T13237
 */
public class DynamicWSHelper {

    private static DynamicWSHelper instance;
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DynamicWSHelper.class);

    private DynamicWSHelper() {

    }

    public static DynamicWSHelper getInstance() {

        if (instance == null) {
            instance = new DynamicWSHelper();

        }
        return instance;
    }

    public void realizarCreacionObjectsJAXB(PublicadorWsClases cdwsdto, String directorioSalida) throws DynamicWSException {
        try {
//               String schemaPath = "E:\\temp\\DynamicClassCompile\\schema\\testing.xsd";
            try ( //String schemaPath = "E:\\temp\\DynamicClassCompile\\schema\\calculadora.xsd";
                    InputStream inFileXSD = new ByteArrayInputStream(cdwsdto.getArchivoXSD().getBytes())) {
                //               String schemaPath = "E:\\temp\\DynamicClassCompile\\schema\\testing.xsd";
                String paquete = cdwsdto.getPackageClass();
                // Setup schema compiler
                SchemaCompiler sc = XJC.createSchemaCompiler();
                //  sc.forcePackageName("com.prueba.pojo");
                sc.forcePackageName(paquete);
                // Setup SAX InputSource
                InputSource is = new InputSource(inFileXSD);
                is.setSystemId("1");//Identificador TO-DO Revisar
                // Parse & build
                if(is==null){
                    throw new DynamicWSException(DynamicWSMessageException.ERROR_CREATE_COMPLEX_OBJECT+" "+cdwsdto.getNombreCLase());
                }
                sc.parseSchema(is);//TO-DO Realizar validacion!!!
                S2JJAXBModel model = sc.bind();
                JCodeModel jCodeModel = model.generateCode(null, null);
                //generacion de los archivos
                jCodeModel.build(new File(directorioSalida));
            }
        } catch (java.lang.InternalError |FileNotFoundException ex) {
            LOGGER.error(DynamicWSMessageException.ERROR_GENERATE_JAXB,ex);
        } catch (IOException ex) {
            LOGGER.error(DynamicWSMessageException.ERROR_GENERATE_JAXB,ex);
        }
    }

    /**
     * Realiza la publicacion del WS
     * @param wsServices
     * @param bean
     * @param directorioFuentes
     * @param directorioSalida
     * @return
     * @throws DynamicWSException 
     */
    public PuplishWSDTO createWSEndPoint(PublicadorWsEstructura wsServices, DynamicWSContextSingletonBeanLocal bean) throws DynamicWSException {

        WebServicesHelper helperWS = new WebServicesHelper();
        PuplishWSDTO puplishWSDTO = null;
        Set<String> listImportsClass = new HashSet<>();
        Set<PublicadorWsClases> listClassesGen = new HashSet<>();
        String className;      
        //Obtiene el WS
        try {
            List<PublicadorWsOperaciones> listOperacionesWS = wsServices.getPublicadorWsOperacionesCollection();
            //Se realiza la compilacion de los obj complex
            for (PublicadorWsOperaciones operaciones : listOperacionesWS) {
                List<PublicadorWsParametrosOperaciones> listParametrosOper = operaciones.getPublicadorWsParametrosOperacionesList();
                //Parametros de las operaciones
                for (PublicadorWsParametrosOperaciones parametro : listParametrosOper) {
                    if (parametro.getIdClaseValor().getArchivoXSD() != null && !listClassesGen.contains(parametro.getIdClaseValor())) {
                        //Se compilan las fuentes    
                        DynamicWSHelper.getInstance().realizarCreacionObjectsJAXB(parametro.getIdClaseValor(), bean.getDirectorioFuentes());
                        listClassesGen.add(parametro.getIdClaseValor());
                    }

                }
                //Lista de imports
                DynamicWSHelper.getInstance().listImports(listImportsClass, listOperacionesWS);
            }

            //Adiciona el ejbRemoto a la lista de imports 
            PublicadorWsClases classEJBRemote=  wsServices.getIdClassEJBRemote();
            className=classEJBRemote.getPackageClass()+"."+classEJBRemote.getNombreCLase();
            listImportsClass.add(className);
            //Crear Interface WS
            // instance.generarClassFileWS(directorioFuentes, interfaceWS);
            //Crear WS
            helperWS.generarClassServiceFileWS(bean.getDirectorioFuentes(), listImportsClass, wsServices);

            //Realiza la compilacion de los obj JAXB
            //Extrae la ubicacion del directorio de compilacion 
            String extraCompilerPath=System.getProperty("com.ath.dynamicws.compile.directory");
            String pathDomain=System.getProperty("weblogic.domainDir");
            pathDomain=pathDomain.concat(File.separator).concat(extraCompilerPath); 
            File directoryLibCompile=new File(pathDomain);
            StringBuilder extraPathCompLib=new StringBuilder();
            for(File file:directoryLibCompile.listFiles()){
                extraPathCompLib.append(file.getAbsolutePath());
                extraPathCompLib.append(";");
            }
            //Adiciona directorio de salida a la classpath
            extraPathCompLib.append(bean.getDirectorioSalida());
            
            DynamicHelper compiler = new DynamicHelper(PublicDynamicWSSessionBean.class.getClassLoader(),extraPathCompLib.toString());
            
            compiler.setPathDirOutput(bean.getDirectorioSalida());
            compiler.addSourceDir(new File(bean.getDirectorioFuentes()));

            for (PublicadorWsClases classToImport : listClassesGen) {

                if (classToImport.getPublicadorWsClasesDependenciaList() != null && !classToImport.getPublicadorWsClasesDependenciaList().isEmpty()) {
                    for (PublicadorWsDependenciaClases classDependencia : classToImport.getPublicadorWsClasesDependenciaList()) {

                        PublicadorWsClases classImportTmp = classDependencia.getPublicadorWsClasesDependencia();
                        className = classImportTmp.getPackageClass() + "." + classImportTmp.getNombreCLase();
                         compiler.loadClass(className);
                       // String classPath = DynamicHelper.extractClasspath(dependece.getClassLoader());
                      //  compiler.setCompileClasspath(classPath);
                      //  compiler.setParentClassLoader(dependece.getClassLoader());
                    }
                }

                if (classToImport.getArchivoXSD() != null) {
                    //Se compilan las fuentes 
                    className = classToImport.getPackageClass() + "." + classToImport.getNombreCLase();
                    compiler.loadClass(className);
                   // String classPath = DynamicHelper.extractClasspath(classToCompile.getClassLoader());
                    //compiler.setCompileClasspath(classPath);
                }
            }

            //Realiza la compilacion del WS          
            Class instanceWS = compiler.loadClass(wsServices.getPackageWs() + "." + wsServices.getNombreWs());
            ClassLoader context = Thread.currentThread().getContextClassLoader();
            try {
                Thread.currentThread().setContextClassLoader(instanceWS.getClassLoader());
                String URLToPublish="http://"+bean.getHostWS()+":"+bean.getPortWS()+"/"+ wsServices.getContextoWS()+"/"+wsServices.getNombreWs();

                Endpoint endpoint = Endpoint.publish(URLToPublish, instanceWS.newInstance());
                //Adiciona el WS Creado
                puplishWSDTO = new PuplishWSDTO(wsServices.getIdWs(),wsServices.getNombreWs(),URLToPublish, endpoint, true);

            } finally {
                Thread.currentThread().setContextClassLoader(context);
            }

        } catch (InstantiationException | IllegalAccessException  ex) {
             LOGGER.error(DynamicWSMessageException.ERROR_PUPLISH_WS+wsServices.getNombreWs(),ex);
            throw new DynamicWSException(DynamicWSMessageException.ERROR_PUPLISH_WS+wsServices.getNombreWs());
         } catch ( ClassNotFoundException| CompilerWSClassException ex){
              LOGGER.error(DynamicWSMessageException.ERROR_COMPILE_CLASS_PUBLISH_WS+wsServices.getNombreWs(),ex);
               throw new DynamicWSException(DynamicWSMessageException.ERROR_COMPILE_CLASS_PUBLISH_WS+wsServices.getNombreWs());
         }catch(IOException ex){
              LOGGER.error(DynamicWSMessageException.ERROR_GENERATE_CLASS_PUBLISH_WS+wsServices.getNombreWs(),ex);
               throw new DynamicWSException(DynamicWSMessageException.ERROR_GENERATE_CLASS_PUBLISH_WS+wsServices.getNombreWs());
         }   
        
        return puplishWSDTO;
    }

    /**
     * Obtiene el listado de imports a utilizar en la plantilla WS
     *
     * @param listaImports
     * @param listOperaciones
     *
     */
    public void listImports(Set<String> listaImports, List<PublicadorWsOperaciones> listOperaciones) {

        String classNameToImport;
        for (PublicadorWsOperaciones operacion : listOperaciones) {
            for (PublicadorWsParametrosOperaciones parametro : operacion.getPublicadorWsParametrosOperacionesList()) {
                classNameToImport = parametro.getIdClaseValor().getPackageClass() + "." + parametro.getIdClaseValor().getNombreCLase();
                if (!listaImports.contains(classNameToImport)) {
                    listaImports.add(classNameToImport);
                }

            }
            //Evalua la clase de retorno
            if (operacion.getIdClaseRetornoOperacion() != null) {
                classNameToImport = operacion.getIdClaseRetornoOperacion().getPackageClass() + "." + operacion.getIdClaseRetornoOperacion().getNombreCLase();
                if (!listaImports.contains(classNameToImport)) {
                    listaImports.add(classNameToImport);
                }
            }

        }       

    }
}
