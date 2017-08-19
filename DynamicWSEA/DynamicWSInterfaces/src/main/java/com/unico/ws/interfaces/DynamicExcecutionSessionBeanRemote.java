/**
----------NO EDITAR-----------
**/

package com.unico.ws.interfaces;

    import javax.ejb.Remote;
 
 
 @Remote
 public interface DynamicExcecutionSessionBeanRemote  {

        
        public String  busquedaLogAsincronico  (
        byte[] usuarioDTO, String[] listusuarioDTO
        ,byte[] claseConsultaMonitorLog,String[] listclaseConsultaMonitorLog  );

}