/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws;

import com.unico.ws.constantes.DynamicWS;
import com.unico.ws.constantes.DynamicWSMessageException;
import com.unico.ws.constantes.StatusExecutionWSEnum;
import com.unico.ws.entity.ConstantesParametrizacionWs;
import com.unico.ws.entity.ParametrosToMapInstClass;
import com.unico.ws.entity.ParametrosToMapMetodos;
import com.unico.ws.entity.PublicadorWsClases;
import com.unico.ws.entity.PublicadorWsDependenciaClases;
import com.unico.ws.entity.PublicadorWsEjecucionOperacionCliente;
import com.unico.ws.entity.PublicadorWsMetodosOperaciones;
import com.unico.ws.entity.PublicadorWsOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosMetodosOperaciones;
import com.unico.ws.entity.PublicadorWsParametrosToMapManagebean;
import com.unico.ws.exception.CompilerWSClassException;
import com.unico.ws.exception.DynamicWSException;
import com.unico.ws.faces.FacesUtil;
import com.unico.ws.faces.JSFWSHelper;
import com.unico.ws.helper.ContextOperationWS;
import com.unico.ws.helper.DynamicHelper;
import com.unico.ws.helper.WSDynamicUtil;
import com.unico.ws.interfaces.DynamicParametrizacionSessionBeanRemote;
import com.unico.ws.interfaces.DynamicWSExecutionStatusBeanRemote;
import com.unico.ws.operation.OperationRequestWS;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.unico.ws.interfaces.EjecucionOperClienteSessionBeanRemote;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author T13237
 */
public class UnicoWSServlet extends HttpServlet {

    //@EJB(mappedName = "ejb/MetodosOperSessionBean")
    //private EjecucionOperClienteSessionBeanRemote metodosOperSessionBean;
    private static Map<String, String> mapParamsDynamicWS;
    private DynamicHelper compiler;
    private ClassLoader classLoaderNewInstance;

    @Override
    public void init()
            throws ServletException {
        try {
            super.init(); //To change body of generated methods, choose Tools | Templates.
            DynamicParametrizacionSessionBeanRemote beanParams = (DynamicParametrizacionSessionBeanRemote) new InitialContext().lookup("ejb/DynamicParametrizacionSessionBean");
            List<ConstantesParametrizacionWs> listParamsMap = beanParams.buscarParametrizacionDynamicWS();
            mapParamsDynamicWS = new HashMap<>();
            //REcorre la lista de parametros
            for (ConstantesParametrizacionWs parametro : listParamsMap) {
                mapParamsDynamicWS.put(parametro.getNombreParametro(), parametro.getValorParametro());

            }
            //Extrae la ubicacion del directorio de compilacion 
            //Extrae la ubicacion del directorio de compilacion 
            String directorioSalida = mapParamsDynamicWS.get(DynamicWS.PARAMETRO_RUTA_DIRECTORIO_SALIDA);
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
            extraPathCompLib.append(directorioSalida);
            //Inicializa el compilador
            compiler = new DynamicHelper(this.getClass().getClassLoader(),extraPathCompLib.toString());
            String directorioFuentes = mapParamsDynamicWS.get(DynamicWS.PARAMETRO_RUTA_DIRECTORIO_FUENTES);
            
            compiler.setPathDirOutput(directorioSalida);
            compiler.addSourceDir(new File(directorioFuentes));
        } catch (NamingException ex) {
            Logger.getLogger(UnicoWSServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idRequest = request.getParameter("idRequest");
        String idOperacionReq = request.getParameter("idOperacion");
        ContextOperationWS context = ContextOperationWS.getInstance();
        DynamicWSExecutionStatusBeanRemote bean = null;
        Map<Integer, Object> resultInstanceMap = new HashMap<>();
        classLoaderNewInstance = null;
         Logger.getLogger(UnicoWSServlet.class.getName()).log(Level.INFO, "Inicia el procesamiento en el servlet UnicoWSServlet");
        if (idRequest != null && !" ".equals(idRequest) && context.getMapObjectContext().containsKey(idRequest)) {
            try {
                OperationRequestWS operReq = context.getMapObjectContext().get(idRequest);
                bean = (DynamicWSExecutionStatusBeanRemote) new InitialContext().lookup("ejb/DynamicWSExecutionStatusBean");
                bean.updatePublicadorWsEstadoInvocOper(idRequest, StatusExecutionWSEnum.EJECUCION, null);
                //Buscar la operacion
                Integer idOperacion = Integer.parseInt(idOperacionReq);
                EjecucionOperClienteSessionBeanRemote metodosOperSessionBean = (EjecucionOperClienteSessionBeanRemote) new InitialContext().lookup("ejb/EjecucionOperClienteSessionBean");
                PublicadorWsOperaciones operacion = metodosOperSessionBean.consultaOperacion(idOperacion);
                //Buscar el mapa de Metodos de la operacion
                List<PublicadorWsEjecucionOperacionCliente> ejecucionOperacionClientList = metodosOperSessionBean.consultarPublicWsEjecOperacionClienteByClient(idOperacion);
                //Se consulta el mapa de parametros de los manage Bean
                Map<PublicadorWsClases, List<PublicadorWsParametrosToMapManagebean>> mapaParametrosToMapManageBeanIdClaseMB = metodosOperSessionBean.consultarMapaParametrosToMapManageBeanIdClaseMB(idOperacion);                
                // <editor-fold defaultstate="collapsed" desc="Realiza la precarga de las clases que provien del wS">
                for (List<PublicadorWsParametrosToMapManagebean> listaMB : mapaParametrosToMapManageBeanIdClaseMB.values()) {
                    for (PublicadorWsParametrosToMapManagebean mb : listaMB) {
                        Class classNew = null;
                        List<String> listClass = new ArrayList<>();
                        //Obtiene las clases a cargar en el contexto
                        for (PublicadorWsDependenciaClases classWS : mb.getIdParametroOperacionWS().getIdClaseValor().getPublicadorWsClasesDependenciaList()) {
                            PublicadorWsClases publicadorWsClases = classWS.getPublicadorWsClasesPrincipal();
                            String nombreClase = publicadorWsClases.getPackageClass() + "." + publicadorWsClases.getNombreCLase();
                            listClass.add(nombreClase);
                        }
                        PublicadorWsClases clasePrincipal = mb.getIdParametroOperacionWS().getIdClaseValor();
                        listClass.add(clasePrincipal.getPackageClass() + "." + clasePrincipal.getNombreCLase());
                        for (String nombreClase : listClass) {
                            classNew = compiler.loadClass(nombreClase);
                            compiler.setCompileClasspath(DynamicHelper.extractClasspath(classNew.getClassLoader()));
                        }
                        if (classLoaderNewInstance == null && classNew != null) {
                            classLoaderNewInstance = classNew.getClassLoader();
                        }
                    }
                }
                // </editor-fold>
                if (mapaParametrosToMapManageBeanIdClaseMB != null && !mapaParametrosToMapManageBeanIdClaseMB.isEmpty()) {
                    // <editor-fold defaultstate="collapsed" desc="Realiza el Set y Get de los parametros del ManageBean">
                    for (List<PublicadorWsParametrosToMapManagebean> listaMB : mapaParametrosToMapManageBeanIdClaseMB.values()) {
                        Object beanInstance1;

                        PublicadorWsClases claseMB;
                        StringBuilder nombreCanonicoMB = new StringBuilder();
                        for (PublicadorWsParametrosToMapManagebean mb : listaMB) {
                            claseMB = mb.getIdClaseManageBean();
                            nombreCanonicoMB.setLength(0);
                            nombreCanonicoMB.append(claseMB.getPackageClass()).append(".").append(claseMB.getNombreCLase());

                            beanInstance1 = findManageBean(claseMB, request, response);
                            if (beanInstance1 != null) {

                                //Se obtiene el Class del parametro de la Operacion
                                Class claseParam = getClassForName(mb.getIdClaseAtributoManageBean(), classLoaderNewInstance);

                                // StringBuilder nombreCanonicoDatoGet = new StringBuilder();
                                // nombreCanonicoDatoGet.append(mb..getPackageClass()).append(".").append(mb.getIdClaseAtributoManageBean().getNombreCLase());
                                Integer ordenParametro = mb.getIdParametroOperacionWS().getOrdenParametro();
                                //Invoca el metodo Get del obj del WS
                                Object entrada;
                                //Evalua si el parametro de entrada es un tipo Java Class o un Object normal
                                if (WSDynamicUtil.getInstance().evaluateJavaValuesClass(operReq.getMapParamsRequest().get(ordenParametro).getNameClass()) || mb.getNombreGetFieldWS() == null) {
                                    entrada = operReq.getMapParamsRequest().get(ordenParametro).getInstance();
                                    if (entrada instanceof byte[]) {
                                       
                                        entrada = realizarDesSerializacion((byte[]) entrada);
                                    }
                                } else {
                                    entrada = WSDynamicUtil.getInstance().invokeMethod(operReq.getMapParamsRequest().get(ordenParametro).getInstance(), operReq.getMapParamsRequest().get(ordenParametro).getNameClass(), new Class[]{}, mb.getNombreGetFieldWS(), null);
                                }

                                entrada = WSDynamicUtil.getInstance().transformarJAXBtoJavaObject(entrada);
                                //Set Dato al campo del manage bean                            
                                WSDynamicUtil.getInstance().invokeMethod(beanInstance1, nombreCanonicoMB.toString(), new Class[]{claseParam}, mb.getNombreAtributoManageBean(), new Object[]{entrada});

                            }
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Realiza la ejecucion de la programacion de la operacion">
                    if (ejecucionOperacionClientList != null && !ejecucionOperacionClientList.isEmpty()) {

                        for (PublicadorWsEjecucionOperacionCliente ejecucion : ejecucionOperacionClientList) {
                            //Si la operacion a ejecutar es un metodo
                            if (ejecucion.getIdMetodoOperacion() != null) {
                                UnicoWSServlet.ExcecuteOperataionMethod methodToExcecute = new ExcecuteOperataionMethod(ejecucion.getIdMetodoOperacion(), operReq, request, response);
                                 ejecucionMetodosOperacion(methodToExcecute, resultInstanceMap);
                                // <editor-fold defaultstate="collapsed" desc="Evalua si la invocacion del metodo arrojó alguna excepcion">
                                if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()){
                                    StringBuilder messageClientException=new StringBuilder();
                                    for(FacesMessage facesMessage:FacesContext.getCurrentInstance().getMessageList()){
                                       if(facesMessage.getSeverity().equals(FacesMessage.SEVERITY_ERROR)){
                                           messageClientException.append(facesMessage.getDetail()!=null?facesMessage.getDetail():facesMessage.getSummary());
                                           messageClientException.append("\n");
                                       }
                                    }
                                    //Evalua si se debe lanzar la excepción
                                    if(messageClientException.length()!=0){
                                        throw new DynamicWSException(messageClientException.toString());
                                    }
                                    
                                }
                                //</editor-fold>
                               
                            }//Si la operacion a ejecutar es cargar un managebean
                            else if (ejecucion.getIdClassManageBeanFind() != null) {
                                PublicadorWsClases classMB = ejecucion.getIdClassManageBeanFind();
                                findManageBean(classMB, request, response);
                            }//Si la operacion es realizar la creacion de una instancia de clase;
                            else if (ejecucion.getIdClaseToBuildInst() != null) {
                                //  ejecucion.getPublicadorWsSetValueInstClaseList()
                                ExcecuteOperataionNewInstanceClass execution = new ExcecuteOperataionNewInstanceClass(ejecucion.getIdEjecucionOperacion(),
                                        ejecucion.getIdClaseToBuildInst(), ejecucion.getParametrosToMapInstClassList(), operReq);
                                ejecucionNewInstanceClass(execution, resultInstanceMap);
                            }
                        }
                        //</editor-fold> 
                        // } else {
                        //   throw new DynamicWSException(DynamicWSMessageException.ERROR_METODO_ASOCIADO_MB_NO_EXISTE.replace("?", claseMB.getIdClase().toString()));
                        // }
                    } else {
                        throw new DynamicWSException(DynamicWSMessageException.ERROR_OPERACION_SIN_METODOS.replace("?", operacion.getNombreOperacion()));
                    }

                } else {
                    throw new DynamicWSException(DynamicWSMessageException.ERROR_OPERACION_SIN_MB.replace("?", operacion.getNombreOperacion()));
                }

            } catch (ClassNotFoundException ex) {
                //"La clase del MB no existe o se encuentra mal configurada                
                //  String msg = DynamicWSMessageException.ERROR_MB_NO_EXISTE.replace("?", nombreCanonicoMB.toString());
                /*   if (bean != null) {
                bean.updatePublicadorWsEstadoInvocOper(idRequest, StatusExecutionWSEnum.ERROR, msg);
                }*/
                Logger.getLogger(UnicoWSServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                //"No se logro recuperar la instancia del Ejb"
                if (bean != null) {
                    bean.updatePublicadorWsEstadoInvocOper(idRequest, StatusExecutionWSEnum.ERROR, ex.getMessage());
                }
            } catch (DynamicWSException ex) {
                if (bean != null) {
                    bean.updatePublicadorWsEstadoInvocOper(idRequest, StatusExecutionWSEnum.ERROR, ex.getMessage());
                }
            } catch (CompilerWSClassException ex) {
                Logger.getLogger(UnicoWSServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        context.getMapObjectContext().remove(idRequest);
        System.out.println("Se remueve peticion: " + idRequest);
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

    private void ejecucionNewInstanceClass(ExcecuteOperataionNewInstanceClass execution, Map<Integer, Object> resultInstanceMap) throws ClassNotFoundException, DynamicWSException {

        //Realiza la creacion de la instancia 
        StringBuilder nombreClase = new StringBuilder();
        PublicadorWsClases clase = execution.getClase();
        nombreClase.append(clase.getPackageClass()).append(".").append(clase.getNombreCLase());
        Object instanceClass = WSDynamicUtil.getInstance().createNewInstance(new String[]{}, new Object[]{}, nombreClase.toString(), this.getClass().getClassLoader());
        //Realiza el Set de la info de los parametros de la instancia

        for (ParametrosToMapInstClass parametrosToMapInstClass : execution.listValueParamToMap) {

            Integer ordenParametro = parametrosToMapInstClass.getIdParametroOperacionWS().getOrdenParametro();
            //Invoca el metodo Get del obj del WS
            Object entrada = WSDynamicUtil.getInstance().invokeMethod(execution.getOperReq().getMapParamsRequest().get(ordenParametro).getInstance(), execution.getOperReq().getMapParamsRequest().get(ordenParametro).getNameClass(), new Class[]{},
                    parametrosToMapInstClass.getNombreParametroGetWS(), null);
            entrada = WSDynamicUtil.getInstance().transformarJAXBtoJavaObject(entrada);
            //Set Dato a la instancia  
            PublicadorWsClases param = parametrosToMapInstClass.getIdAtributoToSetValueClass().getIdClaseAtributo();
            Class paramEntrada = getClassForName(param, classLoaderNewInstance);
            String nombreAtributo = parametrosToMapInstClass.getIdAtributoToSetValueClass().getNombreAtributo();
            StringBuffer nombreMetodoSet;
            nombreMetodoSet = new StringBuffer().append("set").append(nombreAtributo.substring(0, 1).toUpperCase()).append(nombreAtributo.substring(1));
            WSDynamicUtil.getInstance().invokeMethod(instanceClass, nombreClase.toString(), new Class[]{paramEntrada}, nombreMetodoSet.toString(), new Object[]{entrada});
        }
        //Adiciona la instancia para ser pasada como parametro al metodo que lo solicite
        resultInstanceMap.put(execution.idEjecucion, instanceClass);
    }

    private void ejecucionMetodosOperacion(ExcecuteOperataionMethod exectue, Map<Integer, Object> resultInstanceMap) throws DynamicWSException, ClassNotFoundException {
        JSFWSHelper helper = new JSFWSHelper();
        OperationRequestWS operReq = exectue.getOperReq();
        //   if (metodosOperacionList != null ) {
        PublicadorWsMetodosOperaciones methodToInvoke = exectue.getMetodoOperacion();
        //Obtiene el manageBean del contexto
        String namemeBeanClass = methodToInvoke.getIdClasePrincipalMetdOperacion().getPackageClass() + "." + methodToInvoke.getIdClasePrincipalMetdOperacion().getNombreCLase();
        Object instanceClassPrincipal;
        if (methodToInvoke.getIdClasePrincipalMetdOperacion().getAliasClase() != null) {
            //PAra manageBean de Faces
            instanceClassPrincipal = FacesUtil.findBean(methodToInvoke.getIdClasePrincipalMetdOperacion().getAliasClase(), Class.forName(namemeBeanClass), exectue.getRequest(), exectue.getResponse(), getServletContext());

        } else {
            //Instancia de clases normales
            instanceClassPrincipal = WSDynamicUtil.getInstance().createNewInstance(new String[]{}, new String[]{}, namemeBeanClass, this.getClass().getClassLoader());
        }

        //Evalua el tipo de metodo a invocar
        if (helper.isMethodEventJSF(methodToInvoke)) {
            //Invoca un metodo con evento JSF del managebean
            helper.realizarInvocacionMetodoEventJSF(methodToInvoke, operReq.getMapParamsRequest(), exectue.getRequest(), exectue.getResponse(), getServletContext());
        } else {
            //Invoca el metodo del manage bean
            Class[] params = null;
            Object[] paramsValue = null;
            // <editor-fold defaultstate="collapsed" desc="Evalua si el metodo tiene parametros">

            if (methodToInvoke.getPublicadorWsParametrosMetodosOperacionesList() != null && !methodToInvoke.getPublicadorWsParametrosMetodosOperacionesList().isEmpty()) {
                params = new Class[methodToInvoke.getPublicadorWsParametrosMetodosOperacionesList().size()];
                paramsValue = new Object[methodToInvoke.getPublicadorWsParametrosMetodosOperacionesList().size()];

                for (PublicadorWsParametrosMetodosOperaciones publicadorWsParametrosMetodosOper : methodToInvoke.getPublicadorWsParametrosMetodosOperacionesList()) {
                    StringBuilder nombreClase = new StringBuilder();
                    PublicadorWsClases datosClase = publicadorWsParametrosMetodosOper.getIdClase();
                    nombreClase.append(datosClase.getPackageClass()).append(".").append(datosClase.getNombreCLase());
                    //Obtiene el .Class del parametro
                    params[publicadorWsParametrosMetodosOper.getOrdenParametro() - 1] = getClassForName(datosClase, classLoaderNewInstance);
                    //Obtiene el valor del parametro
                    ParametrosToMapMetodos parametro = publicadorWsParametrosMetodosOper.getParametroToMapMetodos();
                    if (parametro.getIdParametroOperacionWS() != null) {
                        //Invoca el metodo Get del obj del WS                    
                        Integer ordenParametro = parametro.getIdParametroOperacionWS().getOrdenParametro();
                        Object entrada = WSDynamicUtil.getInstance().invokeMethod(operReq.getMapParamsRequest().get(ordenParametro).getInstance(), operReq.getMapParamsRequest().get(ordenParametro).getNameClass(), new Class[]{}, parametro.getNombreParametroGetWS(), null);
                        entrada = WSDynamicUtil.getInstance().transformarJAXBtoJavaObject(entrada);
                        paramsValue[publicadorWsParametrosMetodosOper.getOrdenParametro() - 1] = entrada;
                    } else {
                        //En caso de que el valor de la instancia sea un resultado de la ejecucion de una operacion
                        Object valueInst = resultInstanceMap.get(parametro.getIdResultEjecInstClass().getIdEjecucionOperacion());
                        paramsValue[publicadorWsParametrosMetodosOper.getOrdenParametro() - 1] = valueInst;
                    }

                }
            }

            // </editor-fold>
            //Realiza la invocacion del metodo 
            params = params != null ? params : new Class[]{};
            paramsValue = paramsValue != null ? paramsValue : new Object[]{};
            WSDynamicUtil.getInstance().invokeMethod(instanceClassPrincipal, namemeBeanClass, params, methodToInvoke.getMetodoOperacion(), paramsValue);
        }

    }
    
    private Object realizarDesSerializacion(byte[] instanceByte) throws IOException, ClassNotFoundException {

        Object instance;
        BufferedInputStream bis = new BufferedInputStream(new ByteArrayInputStream(instanceByte));
        ObjectInputStream ois = new ObjectInputStream(bis);
        instance = ois.readObject();
        ois.close();

        return instance;
    }

    private Class getClassForName(PublicadorWsClases clase, ClassLoader loader) throws ClassNotFoundException {
        StringBuilder nombreClase = new StringBuilder();
        Class claseParam;
        if (clase.getPackageClass() != null && !clase.getPackageClass().equals("")) {
            //En caso de Objetos
            nombreClase.append(clase.getPackageClass()).append(".").append(clase.getNombreCLase());
            claseParam = Class.forName(nombreClase.toString(), false, loader);
        } else {
            //En caso de datos primitivos
            claseParam = WSDynamicUtil.getInstance().forName(clase.getNombreCLase());

        }
        return claseParam;
    }

    private Object findManageBean(PublicadorWsClases classMB, HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException {
        StringBuilder nombreCanonicoMB = new StringBuilder();
        nombreCanonicoMB.setLength(0);
        nombreCanonicoMB.append(classMB.getPackageClass()).append(".").append(classMB.getNombreCLase());
        return FacesUtil.findBean(classMB.getAliasClase(), Class.forName(nombreCanonicoMB.toString()), request, response, getServletContext());

    }

    // <editor-fold defaultstate="collapsed" desc="Definicion de clases internas">  
    private class ExcecuteOperataionNewInstanceClass {

        private final Integer idEjecucion;
        private final PublicadorWsClases clase;
        private final List<ParametrosToMapInstClass> listValueParamToMap;
        private final OperationRequestWS operReq;

        public ExcecuteOperataionNewInstanceClass(Integer idEjecucion, PublicadorWsClases clase, List<ParametrosToMapInstClass> listValueParamToMap, OperationRequestWS operReq) {
            this.idEjecucion = idEjecucion;
            this.clase = clase;
            this.listValueParamToMap = listValueParamToMap;
            this.operReq = operReq;
        }

        public Integer getIdEjecucion() {
            return idEjecucion;
        }

        public PublicadorWsClases getClase() {
            return clase;
        }

        public List<ParametrosToMapInstClass> getListValueParamToMap() {
            return listValueParamToMap;
        }

        public OperationRequestWS getOperReq() {
            return operReq;
        }

    }

    private class ExcecuteOperataionMethod {

        private final PublicadorWsMetodosOperaciones metodoOperacion;
        private final OperationRequestWS operReq;
        private final HttpServletRequest request;
        private final HttpServletResponse response;

        public ExcecuteOperataionMethod(PublicadorWsMetodosOperaciones metodoOperacion, OperationRequestWS operReq, HttpServletRequest request, HttpServletResponse response) {
            this.metodoOperacion = metodoOperacion;
            this.operReq = operReq;
            this.request = request;
            this.response = response;
        }

        public PublicadorWsMetodosOperaciones getMetodoOperacion() {
            return metodoOperacion;
        }

        public OperationRequestWS getOperReq() {
            return operReq;
        }

        public HttpServletRequest getRequest() {
            return request;
        }

        public HttpServletResponse getResponse() {
            return response;
        }

    }
    // </editor-fold>
}
