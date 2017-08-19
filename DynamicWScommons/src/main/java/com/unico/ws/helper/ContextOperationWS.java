/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;

//import com.unico.ws.helper.OperationWSHelper;
import com.unico.ws.operation.OperationManageBeanWS;
import com.unico.ws.operation.OperationRequestParamWS;
import com.unico.ws.operation.OperationRequestWS;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author T13237
 */
public class ContextOperationWS {
    private static ContextOperationWS instance;
    private final Map<String,OperationRequestWS> mapObjectContext;
    //Temporal
    private final Map<Integer,OperationManageBeanWS> mapOperacionesWS;
    
    private ContextOperationWS(){
        mapObjectContext=new HashMap<>();
        mapOperacionesWS=new HashMap<>();
        //Carga la informacion del mapa
      //  OperationWSHelper.loadInfoOperations(mapOperacionesWS);
    }
    
    public static ContextOperationWS getInstance(){
            if(instance==null){
             instance=new   ContextOperationWS();             
            }
        return instance;
    }

    public Map<String, OperationRequestWS> getMapObjectContext() {
        return mapObjectContext;
    }

    public Map<Integer, OperationManageBeanWS> getMapOperacionesWS() {
        return mapOperacionesWS;
    }
    
    
   
}
