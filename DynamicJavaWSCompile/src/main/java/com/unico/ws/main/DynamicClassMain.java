/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.main;


import com.unico.ws.helper.DynamicHelper;
import com.unico.ws.helper.WSDynamicUtil;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T13237
 */
public class DynamicClassMain {
    
    public static void main(String[] args) {
        
        try {
            DynamicHelper compiler=new DynamicHelper( DynamicClassMain.class.getClassLoader());
            compiler.addSourceDir(new File("E:\\temp\\DynamicClassCompile\\scr"));
            compiler.setPathDirOutput("E:\\temp\\DynamicClassCompile\\output");
            Class EmployeeClass= compiler.loadClass("com.prueba.pojo.Employee");
            Object employeeInst = EmployeeClass.newInstance();
           //
            WSDynamicUtil.getInstance().invokeMethod(employeeInst ,"com.prueba.pojo.Employee", new Class[]{String.class}, "setNombre",new String[]{"Harry"} );
             String retorno=(String)  WSDynamicUtil.getInstance().invokeMethod(employeeInst ,"com.prueba.pojo.Employee", new Class[]{}, "getNombre",null );
             System.out.println("Valor reflexion: "+retorno);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DynamicClassMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DynamicClassMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
