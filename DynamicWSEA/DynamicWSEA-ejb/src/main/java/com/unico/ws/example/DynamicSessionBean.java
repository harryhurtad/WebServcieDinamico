/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.example;

import com.unico.ws.entity.PublicadorWsOperaciones;
import com.unico.ws.exception.CompilerWSClassException;
import com.unico.ws.helper.DynamicHelper;
//import com.unico.ws.interfaces.DynamicExcecutionSessionBeanRemote;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;

/**
 *
 * @author T13237
 */
@Stateless
public class DynamicSessionBean implements DynamicSessionBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    /*@EJB(mappedName = "ejb/DynamicExcecutionSessionBean")
    DynamicExcecutionSessionBeanRemote beanRemote;
*/
    @PersistenceContext(unitName = "DynamicWS_0PU")
    private EntityManager mg;
    
    public static final Logger LOGGER = Logger.getLogger(DynamicSessionBean.class);

    @Override
    public List<PublicadorWsOperaciones> buscarOperacionesWS() {

        Query q = mg.createNamedQuery("PublicadorWsOperaciones.findAll", PublicadorWsOperaciones.class);
        List<PublicadorWsOperaciones> resultado = q.getResultList();
        return resultado;

    }

    public PublicadorWsOperaciones buscarOperacionWS(Integer idOperacion) {
        Query q = mg.createNamedQuery("PublicadorWsOperaciones.findByIdOperacion", PublicadorWsOperaciones.class);
        q.setParameter("idOperacion", idOperacion);
        PublicadorWsOperaciones result = (PublicadorWsOperaciones) (q.getSingleResult());
        return result;
    }

    @Override
    public void setOperacionWS(PublicadorWsOperaciones operacion) {

        operacion.setDescripcionOperacion("Prueba");
    }

    @Override
    public Object cargarClaseDynamica(String nameClass) {
        Object employeeInst = null;
        try {
            DynamicHelper compiler = new DynamicHelper(DynamicSessionBean.class.getClassLoader());
            compiler.setPathDirOutput("E:\\temp\\DynamicClassCompile\\output");
            compiler.addSourceDir(new File("E:\\temp\\DynamicClassCompile\\scr"));
            Class EmployeeClass = compiler.loadClass(nameClass);
            employeeInst = EmployeeClass.newInstance();
        } catch (InstantiationException ex) {
            LOGGER.error("", ex);
        } catch (IllegalAccessException ex) {
            LOGGER.error("", ex);
        } catch (ClassNotFoundException ex) {
            LOGGER.error("", ex);
        } catch (CompilerWSClassException ex) {
            LOGGER.error("", ex);
        }
        return employeeInst;
    }

    @Override
    public void realizarSumaRemota(Object instance) {

        ObjectOutputStream os = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            os = new ObjectOutputStream(out);
            os.writeObject(instance);
            //String uniqueID = UUID.randomUUID().toString();
            //String rutaTmpDir="E:\\temp\\DynamicClassCompile\\output\\serializeTmp\\";
            //String nameClass=instance.getClass().getSimpleName()+uniqueID+".dat";
            //FileOutputStream fos = new FileOutputStream(rutaTmpDir+nameClass);
            // BufferedOutputStream bos = new BufferedOutputStream(fos);
            //ObjectOutputStream oos = new ObjectOutputStream(bos);
            // oos.writeObject(instance);
            // oos.close();
            // beanRemote.realizarOperacion(rutaTmpDir+nameClass, "com.prueba.pojo.CalculadoraDTO", 1);
            //    beanRemote.realizarOperacion(out.toByteArray(), "com.prueba.pojo.CalculadoraDTO", 1);
            LOGGER.info("Se invoca la operacion en el cliente correctamente");
        } catch (IOException ex) {
           LOGGER.error( null, ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                LOGGER.error(null, ex);
            }
        }
    }

}
