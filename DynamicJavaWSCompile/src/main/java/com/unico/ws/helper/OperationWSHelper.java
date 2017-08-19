/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;


import com.unico.ws.exception.CompilerWSClassException;
import com.unico.ws.helper.DynamicHelper;
import com.unico.ws.operation.OperationManageBeanWS;
import com.unico.ws.operation.OperationMapToField;
import com.unico.ws.operation.OperationMethodManageBeanWS;
import com.unico.ws.operation.OperationParamMthManageBean;
import com.unico.ws.operation.OperationRequestParamWS;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T13237
 */
public class OperationWSHelper {

    public static void loadInfoOperations(Map<Integer, OperationManageBeanWS> mapOperation) {
        
       //Cargar informacion
        OperationMapToField fieldToMap1=new OperationMapToField("com.prueba.pojo.CalculadoraDTO",Integer.class, "getDato1",  "setDato1",Integer.class);
        OperationMapToField fieldToMap2=new OperationMapToField("com.prueba.pojo.CalculadoraDTO",Integer.class, "getDato2", "setDato2",Integer.class);
       //Operacion 1
        OperationManageBeanWS oper1 = new OperationManageBeanWS();
        oper1.setIdOperation(1);
        oper1.setAlias("calculadoraJSFManagedBean");
        oper1.setNombreBackingBean("com.unico.ws.mb.CalculadoraJSFManagedBean");
        oper1.setFields(new OperationMapToField[]{fieldToMap1,fieldToMap2} );
        //OPeraciones methodo      
        OperationMethodManageBeanWS methodSuma = new OperationMethodManageBeanWS("com.unico.ws.mb.CalculadoraJSFManagedBean","realizarSuma", new OperationParamMthManageBean[]{});
        oper1.setOperaciones(new OperationMethodManageBeanWS[]{methodSuma});
        mapOperation.put(1, oper1);
        
        //OPeracion 2
        OperationManageBeanWS oper2 = new OperationManageBeanWS();
        oper2.setIdOperation(2);
        OperationMethodManageBeanWS methodResta = new OperationMethodManageBeanWS("com.unico.ws.mb.CalculadoraJSFManagedBean","realizarResta", new OperationParamMthManageBean[]{});
        oper2.setOperaciones(new OperationMethodManageBeanWS[]{methodResta});
        oper2.setFields(new OperationMapToField[]{fieldToMap1,fieldToMap2} );
        oper2.setAlias("calculadoraJSFManagedBean");
        oper2.setNombreBackingBean("com.unico.ws.mb.CalculadoraJSFManagedBean");
        mapOperation.put(2, oper2);
        
//OPeracion 3
        OperationManageBeanWS oper3 = new OperationManageBeanWS();
        oper3.setIdOperation(3);
        OperationMethodManageBeanWS methodMultiplicacion = new OperationMethodManageBeanWS("com.unico.ws.mb.CalculadoraJSFManagedBean", "realizarMultiplicacion", new OperationParamMthManageBean[]{});
        oper3.setOperaciones(new OperationMethodManageBeanWS[]{methodMultiplicacion});
        oper3.setFields(new OperationMapToField[]{fieldToMap1,fieldToMap2} );
        oper3.setAlias("calculadoraJSFManagedBean");
        oper3.setNombreBackingBean("com.unico.ws.mb.CalculadoraJSFManagedBean");
        mapOperation.put(3, oper3);
        
        //OPeracion 4
        OperationManageBeanWS oper4 = new OperationManageBeanWS();
        oper4.setIdOperation(4);
        OperationMethodManageBeanWS methodDivicion = new OperationMethodManageBeanWS("com.unico.ws.mb.CalculadoraJSFManagedBean", "realizarDivicion", new OperationParamMthManageBean[]{});
        oper4.setOperaciones(new OperationMethodManageBeanWS[]{methodDivicion});
        oper4.setAlias("calculadoraJSFManagedBean");
        oper4.setFields(new OperationMapToField[]{fieldToMap1,fieldToMap2} );
        oper4.setNombreBackingBean("com.unico.ws.mb.CalculadoraJSFManagedBean");
        mapOperation.put(4, oper4);

    }
    
    public static void main(String[] args) {
        FileOutputStream fos=null;
        try {
           DynamicHelper compiler = new DynamicHelper();
            compiler.setPathDirOutput("E:\\temp\\DynamicClassCompile\\output");
            compiler.addSourceDir(new File("E:\\temp\\DynamicClassCompile\\scr"));           
            Class prueba =compiler.loadClass("com.prueba.pojo.CalculadoraDTO");
            
            fos = new FileOutputStream("E:\\temp\\DynamicClassCompile\\output\\CalculadoraDTO.dat");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(prueba.newInstance());
            oos.close();
            System.out.println("Serializacion exitosa!!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OperationWSHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OperationWSHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OperationWSHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(OperationWSHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(OperationWSHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompilerWSClassException ex) {
            Logger.getLogger(OperationWSHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(OperationWSHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
