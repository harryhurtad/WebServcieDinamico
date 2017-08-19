/**
----------NO EDITAR-----------
**/

package com.unico.ws.ejb;

  import com.unico.ws.interfaces.DynamicExcecutionSessionBeanRemote;
  import com.unico.ws.constantes.DynamicWSMessageException;    
  import com.unico.ws.interfaces.DynamicParametrizacionSessionBeanRemote;
  import com.unico.ws.interfaces.DynamicProductorJMSSesBeanLocal;
  import com.unico.ws.interfaces.EjecucionOperClienteSessionBeanRemote;
  import com.unico.ws.object.RequestDynamicWSJMS;  
  import com.unico.ws.object.RequestParamPrimitiveDynaJMS;
  import com.unico.ws.object.ReqParamsComplexObjDynaJMS; 
  import com.unico.ws.exception.DynamicWSException;
  import javax.annotation.PostConstruct;
  import javax.ejb.EJB;
  import javax.ejb.Stateless;  
  import java.util.UUID;
  import javax.naming.InitialContext;
  import javax.naming.NamingException;

 
  
 @Stateless(name = "DynamicExcecutionSessionBean", mappedName = "ejb/DynamicExcecutionSessionBean")    
public class DynamicExcecutionSessionBean implements DynamicExcecutionSessionBeanRemote {

   @EJB
   private DynamicProductorJMSSesBeanLocal dynamicProductorJMSSesBean;
   private EjecucionOperClienteSessionBeanRemote metodosOperSessionBean;
   private DynamicParametrizacionSessionBeanRemote dynamicParametrizacionSessionBean; 
   private String contextoWebClient;
   private  static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(DynamicExcecutionSessionBean.class);
    
    @PostConstruct
    public void loadInfoParam(){
          try {
           metodosOperSessionBean= (EjecucionOperClienteSessionBeanRemote)new InitialContext().lookup("ejb/EjecucionOperClienteSessionBean");
           dynamicParametrizacionSessionBean=(DynamicParametrizacionSessionBeanRemote)new InitialContext().lookup("ejb/DynamicParametrizacionSessionBean");
           contextoWebClient= dynamicParametrizacionSessionBean.buscarNombreContextoCliente("CentroComputoWS");
       } catch (NamingException ex) {
           LOGGER.error(DynamicWSMessageException.ERROR_LOAD_PARAM_EJB+"DynamicExcecutionSessionBean", ex);
       }
    } 

   
        @Override
        public String  busquedaLogAsincronico   (       
        byte[] usuarioDTO, String[] listusuarioDTO
        ,byte[] claseConsultaMonitorLog,String[] listclaseConsultaMonitorLog ){
                String respuseta=null;
                String uniqueID="";
                try {
                    LOGGER.info("DynamicExcecutionSessionBean->Inicia la ejecucion del metodo operacion  busquedaLogAsincronico");             
                    Integer idOperacion=  metodosOperSessionBean.consultaIdOperacion("BusquedaLogAsincronico");
                    //Id del Request
                    uniqueID = UUID.randomUUID().toString();
                    RequestDynamicWSJMS requestWS=new RequestDynamicWSJMS(uniqueID, idOperacion, contextoWebClient);
                    //Adicion al Wrapper los parametros
                    ReqParamsComplexObjDynaJMS param1=new ReqParamsComplexObjDynaJMS(usuarioDTO, listusuarioDTO);
                    ReqParamsComplexObjDynaJMS param2=new ReqParamsComplexObjDynaJMS(claseConsultaMonitorLog, listclaseConsultaMonitorLog);
                    //Adiciona los parametros al map 
                    requestWS.getMapParamsOperationWS().put(1, param1);

                    requestWS.getMapParamsOperationWS().put(2, param2);

                    dynamicProductorJMSSesBean.enviaMensajeCola(requestWS);
                   
                     LOGGER.info("DynamicExcecutionSessionBean->Finaliza la ejecucion del metodo operacion  busquedaLogAsincronico");
            } catch (DynamicWSException ex) {
                 respuseta=ex.getMessage()+" peticion: "+uniqueID;
            }
                return respuseta!=null?respuseta:"Se ha generado la peticion-> "+uniqueID;
        }


  

}