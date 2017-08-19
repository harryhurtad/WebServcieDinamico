/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;

import com.unico.ws.constantes.DynamicWS;
import com.unico.ws.constantes.DynamicWSMessageException;
import com.unico.ws.exception.DynamicWSException;
import com.unico.ws.interfaces.DynamicParametrizacionSessionBeanRemote;
import com.unico.ws.operation.OperationRequestWS;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author T13237
 */
public class SendToRequestServletHelper {
    
    private final String USER_AGENT = "Mozilla/5.0";
    private  static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SendToRequestServletHelper.class);
     

    public void sendRequestServlet(OperationRequestWS request) throws DynamicWSException {

        //   ContextOperationWS.getInstance().getMapObjectContext().put(uniqueID, request);
        try {
            if (DynamicWSContextSingleton.getInstance().getMapDynamicWSParmas().isEmpty()) {

                DynamicParametrizacionSessionBeanRemote bean = (DynamicParametrizacionSessionBeanRemote) new InitialContext().lookup("ejb/DynamicParametrizacionSessionBean");
                DynamicWSClientHelper helper = new DynamicWSClientHelper(bean);
                DynamicWSContextSingleton.getInstance().getMapDynamicWSParmas().putAll(helper.getParamsDynamicWSRemote());

            }

            String host = DynamicWSContextSingleton.getInstance().getMapDynamicWSParmas().get(DynamicWS.HOST_WS);
            String port = DynamicWSContextSingleton.getInstance().getMapDynamicWSParmas().get(DynamicWS.PUERTO_WS);
            //       Invocacion el servlet por post
            String url = "http://"+host+":"+port+"/"+request.getContextWebAppCLient()+"/UnicoWSServlet";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "idRequest=" + request.getRequestId() + "&idOperacion=" + request.getIdOperacion();

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            LOGGER.info("DynamicConsumerMessageBean-> Envia la peticion  No: "+request.getRequestId());
            LOGGER.info("\nSending 'POST' request to URL : " + url);
            LOGGER.info("Post parameters : " + urlParameters);
            LOGGER.info("Response Code : " + responseCode);

            if(responseCode==404||responseCode==500){
                throw new DynamicWSException(DynamicWSMessageException.ERROR_SEND_REQUEST_APP_CLIENT+" Codigo respuesta:"+responseCode);
            }
            /* BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();*/

        } catch (NamingException ex) {
            LOGGER.error(DynamicWSMessageException.ERROR_SEND_REQUEST_APP_CLIENT, ex);
        } catch (MalformedURLException ex) {
            LOGGER.error(DynamicWSMessageException.ERROR_SEND_REQUEST_APP_CLIENT, ex);
        } catch (IOException ex) {
             LOGGER.error(DynamicWSMessageException.ERROR_SEND_REQUEST_APP_CLIENT, ex);
        }
    }
}
