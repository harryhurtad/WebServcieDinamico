/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.constantes;

/**
 *
 * @author T13237
 */
public class DynamicWSMessageException {

    public final static String ERROR_PUPLISH_WS = "ERROR 01: Error al crear el endpoint del WS->";
    public final static String ERROR_COMPILE_CLASS_PUBLISH_WS = "ERROR 02: Error al compilar las clases del WS->";
     public final static String ERROR_GENERATE_CLASS_PUBLISH_WS = "ERROR 03: Error al generar la clases WS en tiempo de ejecucion->";
    public final static String ERROR_INICIAL_PARAM_PUBLISH_WS = "ERROR 04: Error al inicializar los parametros en el WS";
    public final static String ERROR_PUPLISHS_ALL_WS = "ERROR 05: Error al publicar el WS";
    public final static String ERROR_EJECUTAR_OPERACION_WS = "ERROR 06: Error al relaizar la ejecucion de la operacion en la Queque";
    public final static String ERROR_METODO_ASOCIADO_MB_NO_EXISTE = "ERROR 07: No existe ningun metodo asociado al Manage Bean. idClaseMB : ?.";
    public final static String ERROR_OPERACION_SIN_METODOS = "ERROR 08: No existe ningun metodo asociado a la operacion ?.";
    public final static String ERROR_OPERACION_SIN_MB = "ERROR 09: No existe ningun Manage Bean asociado a la operacion ?.";
    public final static String ERROR_MB_NO_EXISTE = "ERROR 10: El Manage Bean no existe ?.";
    public final static String ERROR_CREATE_COMPLEX_OBJECT= "ERROR 11: Error al crear el objeto complejo en JAXB: ";
    public final static String ERROR_ENABLE_WS= "ERROR 12: Error al habilitar el Web Services";
    public final static String ERROR_GENERATE_JAXB= "ERROR 13: Error al realizar la Creacion del Obj JAXB";
    public final static String ERROR_INIT_LOG4J= "ERROR 14: Error Iniciando el log4j";
    public final static String ERROR_SEND_JMS= "ERROR 15: Error enviando el mensaje a la cola";
    public final static String ERROR_PROCCESS_JMS= "ERROR 16: Error al procesar el mensaje en la cola";
    public final static String ERROR_SEND_REQUEST_APP_CLIENT= "ERROR 17: Error enviando request a la aplicacion cliente";
    public final static String ERROR_LOAD_PARAM_EJB= "ERROR 18: Error al cargar los parametros en el ejb:";
    public final static String ERROR_NOMBRE_INTERFAZ_EJB = "ERROR 19: El nombre de la interfaz remota se encuentra mal parametrizada o el nombre no cumple el estandar";
    public final static String ERROR_INVOKE_METHOD_APP_CLIENT = "ERROR 20: Error al invocar el metodo en la aplicacion cliente";
    public final static String ERROR_CREATE_NEW_INSTANCE_APP_CLIENT = "ERROR 21: Error al crear nueva instancia de la aplicacion cliente";
}
