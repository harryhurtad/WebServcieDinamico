/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;

import com.unico.ws.constantes.DynamicWSMessageException;
import com.unico.ws.exception.DynamicWSException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author T13237
 */
public class WSDynamicUtil {

    private static WSDynamicUtil instance;
    Map<String, Class> mapClasesPrimitivas;
    Map<String, Class> mapJavaWrapperClass;
    Map<String, Class> mapJavaCollectionClass;

    private WSDynamicUtil() {

        mapClasesPrimitivas = new HashMap<>();
        mapJavaWrapperClass = new HashMap<>();
        mapJavaCollectionClass = new HashMap<>();
        loadInformacionClasesPrimitivas();
        loadJavaWrapperClass();
        loadJavaCollectionClass();
    }

    public static WSDynamicUtil getInstance() {

        if (instance == null) {
            instance = new WSDynamicUtil();
        }

        return instance;

    }

    public byte[] realizarSerializacion(Object instance) {
        System.out.println("Si entro!!");
        ObjectOutputStream os;
        byte[] byteInstance = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            os = new ObjectOutputStream(out);
            os.writeObject(instance);

            byteInstance = out.toByteArray();

        } catch (IOException ex) {
            Logger.getLogger(WSDynamicUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return byteInstance;
    }

    /**
     * Realiza la invoación de los metodos de una clase a travez de Relfexion
     *
     * @param instance
     * @param nombreClase
     * @param parameters
     * @param nameMethod
     * @param value
     * @return
     * @throws com.unico.ws.exception.DynamicWSException
     */
    public Object invokeMethod(Object instance, String nombreClase, Class[] parameters, String nameMethod, Object[] value) throws DynamicWSException {
        Object retorno = null;
        try {
            //Invoca y crea una instancia de la clase
            Class cls = Class.forName(nombreClase, false, instance.getClass().getClassLoader());
            //Object obj = cls.newInstance();

            //invoca el metodo de la clase
            Method method = cls.getDeclaredMethod(nameMethod, parameters);
            if (parameters.length == 0 && value == null) {
                retorno = method.invoke(instance, new Object[]{});
            } else if (value != null) {
                retorno = method.invoke(instance, value);
            }

        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            StringBuilder messageEx=new StringBuilder();
            messageEx.append(DynamicWSMessageException.ERROR_INVOKE_METHOD_APP_CLIENT);
            messageEx.append(": Clase-> ");
            messageEx.append(nombreClase);
            messageEx.append(" Metodo-> ");
            messageEx.append(nameMethod);
            messageEx.append(" Parametros-> ");
            messageEx.append(parameters);
            messageEx.append(" Valores-> ");
            messageEx.append(value);
            Logger.getLogger(WSDynamicUtil.class.getName()).log(Level.SEVERE, messageEx.toString(), ex);
            DynamicWSException e = new DynamicWSException(messageEx.toString());
            throw e;
        }
        return retorno;
    }

    public Object createNewInstance(String[] paramsClassConstruct, Object[] paramValue, String nombreClase, ClassLoader loader) throws DynamicWSException{
        Object instanceToBuild = null;
        Class[] paramClass = null;
        try {
            if (paramsClassConstruct != null) {
                paramClass = new Class[paramsClassConstruct.length];
                for (int i = 0; i < paramsClassConstruct.length; i++) {
                    paramClass[i] = Class.forName(paramsClassConstruct[i], false, loader);
                }
            }

            Class cls = Class.forName(nombreClase, false, loader);
            if (paramClass != null) {
                instanceToBuild = cls.getConstructor(paramClass).newInstance(paramValue);
            } else {
                instanceToBuild = cls.newInstance();
            }

        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            StringBuilder messageEx=new StringBuilder();
            messageEx.append(DynamicWSMessageException.ERROR_CREATE_NEW_INSTANCE_APP_CLIENT);
            messageEx.append(": Clase-> ");
            messageEx.append(nombreClase);
            messageEx.append(" Parametros-> ");
            messageEx.append(paramValue); 
            Logger.getLogger(WSDynamicUtil.class.getName()).log(Level.SEVERE, messageEx.toString(), ex);
            DynamicWSException e = new DynamicWSException(messageEx.toString());
            throw e;
        }
        return instanceToBuild;
    }

    private void loadInformacionClasesPrimitivas() {

        for (Class<?> cls : new Class<?>[]{
            void.class,
            boolean.class,
            char.class,
            byte.class,
            short.class,
            int.class,
            long.class,
            float.class,
            double.class
        }) {
            mapClasesPrimitivas.put(cls.getName(), cls);
        }

    }

    public Class<?> forName(final String name)
            throws ClassNotFoundException {
        final Class<?> prim = mapClasesPrimitivas.get(name);

        if (prim != null) {
            return prim;
        }

        return Class.forName(name);
    }

    public Object transformarJAXBtoJavaObject(Object obj) {
        Object objRet = null;
        if (obj instanceof XMLGregorianCalendar) {
            XMLGregorianCalendar fechaTemp = (XMLGregorianCalendar) obj;
            objRet = fechaTemp.toGregorianCalendar().getTime();
        } else {
            objRet = obj;
        }
        return objRet;
    }

    private void loadJavaWrapperClass() {
        for (Class<?> cls : new Class<?>[]{java.lang.Integer.class,
            java.lang.Float.class,
            java.lang.Boolean.class,
            java.lang.Long.class,
            java.lang.Double.class,
            java.util.Date.class}) {
            mapJavaWrapperClass.put(cls.getName(), cls);
        }

    }

    private void loadJavaCollectionClass() {
        for (Class<?> cls : new Class<?>[]{
            java.util.ArrayList.class, java.util.HashMap.class}) {
            mapJavaCollectionClass.put(cls.getName(), cls);
        }

    }

    public boolean evaluateJavaValuesClass(String nameClass) throws ClassNotFoundException {

        return mapJavaWrapperClass.containsKey(nameClass);
    }

    public boolean evaluateJavaCollectionValuesClass(String nameClass) throws ClassNotFoundException {

        return mapJavaCollectionClass.containsKey(nameClass);
    }
   
}
