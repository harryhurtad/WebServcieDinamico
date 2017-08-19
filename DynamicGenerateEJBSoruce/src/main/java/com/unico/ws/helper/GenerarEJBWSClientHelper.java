/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;

import com.unico.ws.constantes.DynamicWSMessageException;
import com.unico.ws.entity.ConstantesParametrizacionWs;
import com.unico.ws.entity.PublicadorWsEstructura;
import com.unico.ws.entity.PublicadorWsOperaciones;
import com.unico.ws.exception.DynamicWSException;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author T13237
 */
public final class GenerarEJBWSClientHelper {

    private final EntityManagerFactory emf;

    public GenerarEJBWSClientHelper(String pathProperties) throws IOException {
        Properties prop = cargarPropiedades(pathProperties);
        emf = Persistence.createEntityManagerFactory("com.unico.ws_Dynamic-commonsWS_jar_1.0PU", prop);
    }
    
     public GenerarEJBWSClientHelper(Properties prop) throws IOException {        
        emf = Persistence.createEntityManagerFactory("com.unico.ws_Dynamic-commonsWS_jar_1.0PU", prop);
    }

    public void generarClassFileWS(PublicadorWsEstructura estructuraWS, String rutaInterfazEJB, String rutaEJB, String paqueteEJB) throws IOException, DynamicWSException {

             
        List<PublicadorWsOperaciones> listaMetodosOper = estructuraWS.getPublicadorWsOperacionesCollection();

        Map root;
        File sourceFile;

        int dato;
        //Pasa la plantilla a un directorio temporal
        String default_tmp = System.getProperty("java.io.tmpdir");
        File dirTemp = File.createTempFile(default_tmp, "ejbDynamicWSClient_freemaker");
        dirTemp.delete();
        dirTemp.mkdirs();
        InputStream in = this.getClass().getResourceAsStream("/templates/TemplateClassEJBDynamicInterfaceWS.txt");

        File outputTemplateInterface = new File(dirTemp, "TemplateClassEJBDynamicInterfaceWS.txt");
        try (OutputStream outFile = new FileOutputStream(outputTemplateInterface)) {
            while ((dato = in.read()) != -1) {
                outFile.write(dato);
            }
        }
        //Plantilla CLase EJB
        dato = 0;
        in = this.getClass().getResourceAsStream("/templates/TemplateClassEJBClientDynamicWS.txt");

        File outputTemplateEJB = new File(dirTemp, "TemplateClassEJBClientDynamicWS.txt");
        try (OutputStream outFile = new FileOutputStream(outputTemplateEJB)) {
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
        //Pasa los parametros para la generacion de la clase
        root = new HashMap();

        String nombreInterfazEJB = estructuraWS.getIdClassEJBRemote().getNombreCLase();
        String paqueteInterfazEJB = estructuraWS.getIdClassEJBRemote().getPackageClass();
        String nombreEJB = null;
        if (nombreInterfazEJB.endsWith("Remote")) {
            nombreEJB = nombreInterfazEJB.substring(0, nombreInterfazEJB.lastIndexOf("Remote"));
        } else {
            // Lanzar excepcion sobre el nombre incorrecto de la Interfaz
            throw new DynamicWSException(DynamicWSMessageException.ERROR_NOMBRE_INTERFAZ_EJB);
        }

        root.put("packageInterface", paqueteInterfazEJB);
        root.put("packageClass", paqueteEJB);
        root.put("name_interface", nombreInterfazEJB);
        root.put("name_ejb", nombreEJB);
        root.put("operaciones", listaMetodosOper);
        root.put("nombre_ws", estructuraWS.getNombreWs());

        /* Merge data-model with template */
        createDirectory(new File(rutaInterfazEJB + "\\", paqueteInterfazEJB.replaceAll("\\.", "/")));
        String nameInterfaceClassFile = paqueteInterfazEJB.replaceAll("\\.", "/") + "//" + nombreInterfazEJB + ".java";
        sourceFile = new File(rutaInterfazEJB, nameInterfaceClassFile);
        generateJavaSCR(cfg, outputTemplateInterface, sourceFile, root);
         Logger.getLogger(GenerarEJBWSClientHelper.class.getName()).info("Interfaz source:".concat(sourceFile.getAbsolutePath()));
        //Crea impl EJB
        createDirectory(new File(rutaEJB + "", paqueteEJB.replaceAll("\\.", "/")));
        String nameEJBClassFile = paqueteEJB.replaceAll("\\.", "/") + "//" + nombreEJB + ".java";
        sourceFile = new File(rutaEJB, nameEJBClassFile);
        generateJavaSCR(cfg, outputTemplateEJB, sourceFile, root);
        Logger.getLogger(GenerarEJBWSClientHelper.class.getName()).info("Impl EJB source:".concat(sourceFile.getAbsolutePath()));

    }

    private void generateJavaSCR(Configuration cfg, File templateFile, File sourceFile, Map root) {

        try {

            /* Get the template (uses cache internally) */
            Template temp = cfg.getTemplate(templateFile.getName());

            //Geenra el archivo
            try (Writer out = new OutputStreamWriter(new FileOutputStream(sourceFile))) {
                //Geenra el archivo
                temp.process(root, out);
            } catch (IOException ex) {
                Logger.getLogger(GenerarEJBWSClientHelper.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (TemplateException ex) {
            Logger.getLogger(GenerarEJBWSClientHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedTemplateNameException ex) {
            Logger.getLogger(GenerarEJBWSClientHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(GenerarEJBWSClientHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerarEJBWSClientHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param drecotry
     * @throws IOException
     */
    private void createDirectory(File drecotry) throws IOException {

        if (!drecotry.exists()) {
            drecotry.mkdirs();
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

    public Properties cargarPropiedades(String rutaProperties) throws FileNotFoundException, IOException {
        Properties datos = new Properties();
        InputStream is = new BufferedInputStream(new FileInputStream(rutaProperties));
        datos.load(is);
        return datos;
    }

    public ConstantesParametrizacionWs getConstanteParametrizacionWS(String nombreParametro) {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNamedQuery("ConstantesParametrizacionWs.findByNombreParametro");
        q.setParameter("nombreParametro", nombreParametro);
        ConstantesParametrizacionWs constante = (ConstantesParametrizacionWs) q.getSingleResult();
        return constante;
    }

    public PublicadorWsEstructura getEstructuraWS(String nombreWS) {
        PublicadorWsEstructura estructura;

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNamedQuery("PublicadorWsEstructura.findByNombreWs");
        q.setParameter("nombreWs", nombreWS);
        estructura = (PublicadorWsEstructura) q.getSingleResult();

        return estructura;
    }

    public List<PublicadorWsEstructura> getListaWS() {

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createNamedQuery("PublicadorWsEstructura.findAll");
        List<PublicadorWsEstructura> ListaWS = (List<PublicadorWsEstructura>) q.getResultList();
        return ListaWS;
    }

    private List<PublicadorWsOperaciones> getListMetodosOperacionWS(String nombreWS) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.unico.ws_Dynamic-commonsWS_jar_1.0PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<PublicadorWsOperaciones> listMetodosOper = null;
        try {
            Query q = em.createNamedQuery("PublicadorWsEstructura.findByNombreWs");
            q.setParameter("nombreWs", nombreWS);
            PublicadorWsEstructura estructura = (PublicadorWsEstructura) q.getSingleResult();
            listMetodosOper = estructura.getPublicadorWsOperacionesCollection();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return listMetodosOper;
    }

    private List<WeblogicEJBDescriptorDTO> generarListaWeblogicEJBDescriptor( List<PublicadorWsEstructura> listaEstructuraWS) {


        List<WeblogicEJBDescriptorDTO> listaWeblogicEJBDescriptor = new ArrayList<>();
        for (PublicadorWsEstructura estructuraWS : listaEstructuraWS) {
            String interfazEJBName = estructuraWS.getIdClassEJBRemote().getNombreCLase();
            String ejbName = interfazEJBName.substring(0, interfazEJBName.lastIndexOf("Remote"));
            String businessRemote = estructuraWS.getIdClassEJBRemote().getPackageClass() + "." + interfazEJBName;
            String jndiName = estructuraWS.getIdClassEJBRemote().getJndi();
            WeblogicEJBDescriptorDTO descriptorEJB = new WeblogicEJBDescriptorDTO(ejbName, businessRemote, jndiName);
            listaWeblogicEJBDescriptor.add(descriptorEJB);
        }
        return listaWeblogicEJBDescriptor;
    }

    public void generarWeblogicEJBDescriptor(String rutaProyecto,List<PublicadorWsEstructura> listaEstructura) throws IOException {
        Map root = new HashMap();
        File sourceFile;
        int dato;

        List<WeblogicEJBDescriptorDTO> listaWeblogicEJBDescriptor = generarListaWeblogicEJBDescriptor(listaEstructura);

        //Pasa la plantilla a un directorio temporal
        String default_tmp = System.getProperty("java.io.tmpdir");
        File dirTemp = File.createTempFile(default_tmp, "weblogicDescriptor_freemaker");
        dirTemp.delete();
        dirTemp.mkdirs();
        InputStream in = this.getClass().getResourceAsStream("/templates/TemplateWeblogicEJBDescriptorWS.txt");

        File outputTemplate = new File(dirTemp, "TemplateWeblogicEJBDescriptorWS.txt");
        try (OutputStream outFile = new FileOutputStream(outputTemplate)) {
            while ((dato = in.read()) != -1) {
                outFile.write(dato);
            }
        }

        //Configura el api de FREEMAKER
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_24);
        cfg.setDirectoryForTemplateLoading(dirTemp);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);

        root.put("listaWeblogicEJBDescriptor", listaWeblogicEJBDescriptor);

        String rutaArchivo = rutaProyecto + "\\src\\main\\resources\\META-INF";//X:\\Sistema Unico\\Fuentes\\Desarrollo\\1.2.x\\Componentes\\ComponentesArquetipo\\WebServcieDinamico\\DynamicWSRemoteClient-ejb\\src\\main\\resources\\META-INF";
        String nombreArchivo = "weblogic-ejb-jar.xml";

        /* Merge data-model with template */
        createDirectory(new File(rutaArchivo));
        sourceFile = new File(rutaArchivo, nombreArchivo);
        generateJavaSCR(cfg, outputTemplate, sourceFile, root);
    }
}
