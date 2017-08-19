/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;

import com.unico.ws.constantes.DynamicWS;
import com.unico.ws.entity.ConstantesParametrizacionWs;
import com.unico.ws.exception.CompilerWSClassException;
import com.unico.ws.interfaces.DynamicParametrizacionSessionBeanRemote;
import com.unico.ws.stream.DynamicObjectInputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author T13237
 */
public class DynamicWSClientHelper {

    private DynamicParametrizacionSessionBeanRemote ejb;

    public DynamicWSClientHelper() {

    }

    public DynamicWSClientHelper(DynamicParametrizacionSessionBeanRemote ejb) {
        this.ejb = ejb;

    }

    /**
     * Retorna la lista de parametros
     *
     * @return
     */
    public Map<String, String> getParamsDynamicWSRemote() {
        List<ConstantesParametrizacionWs> listParamsMap = ejb.buscarParametrizacionDynamicWS();
        Map<String, String> mapParamsDynamicWS = new HashMap<String, String>();
        //REcorre la lista de parametros
        for (ConstantesParametrizacionWs parametro : listParamsMap) {
            mapParamsDynamicWS.put(parametro.getNombreParametro(), parametro.getValorParametro());

        }
        return mapParamsDynamicWS;
    }

    public Object realizarDesSerializacion(ClassLoader classLoader, String[] listClassToLoad, byte[] instanceByte) throws ClassNotFoundException, IOException, CompilerWSClassException, NamingException {
        DynamicHelper compiler = new DynamicHelper(classLoader);
        if (DynamicWSContextSingleton.getInstance().getMapDynamicWSParmas().isEmpty()) {

            DynamicParametrizacionSessionBeanRemote bean = (DynamicParametrizacionSessionBeanRemote) new InitialContext().lookup("ejb/DynamicParametrizacionSessionBean");
            DynamicWSClientHelper helper = new DynamicWSClientHelper(bean);
            DynamicWSContextSingleton.getInstance().getMapDynamicWSParmas().putAll(helper.getParamsDynamicWSRemote());

        }
        //Parametros de directorio fuentes y compilacion definidos por DynamicWS
        String directorioFuentes = DynamicWSContextSingleton.getInstance().getMapDynamicWSParmas().get(DynamicWS.PARAMETRO_RUTA_DIRECTORIO_FUENTES);
        String directorioSalida = DynamicWSContextSingleton.getInstance().getMapDynamicWSParmas().get(DynamicWS.PARAMETRO_RUTA_DIRECTORIO_SALIDA);

        compiler.setPathDirOutput(directorioSalida);
        compiler.addSourceDir(new File(directorioFuentes));
        Class classNew = null;
        Object instance = null;
        for (String nombreClase : listClassToLoad) {
            classNew = compiler.loadClass(nombreClase);
            compiler.setCompileClasspath(DynamicHelper.extractClasspath(classNew.getClassLoader()));
        }
        if (classNew != null) {
            BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(instanceByte));
            ObjectInputStream ois = new DynamicObjectInputStream(bis, classNew.getClassLoader());
            instance = ois.readObject();
            ois.close();
        }
        System.out.println("Realizacion de la deseralizacion exitosamente!!");
        return instance;
    }

}
