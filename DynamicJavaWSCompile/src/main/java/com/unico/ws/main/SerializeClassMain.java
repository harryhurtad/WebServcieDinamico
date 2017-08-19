/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.main;

import com.unico.ws.exception.CompilerWSClassException;
import com.unico.ws.helper.DynamicHelper;
import com.unico.ws.stream.DynamicObjectInputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T13237
 */
public class SerializeClassMain {
    
    public static void main(String[] args) {
        ObjectInputStream ois = null;
        try {
            ClassLoader classL=SerializeClassMain.class.getClassLoader();
            
            DynamicHelper compiler = new DynamicHelper(SerializeClassMain.class.getClassLoader());
            compiler.setPathDirOutput("E:\\temp\\DynamicClassCompile\\output");
            compiler.addSourceDir(new File("E:\\temp\\DynamicClassCompile\\scr"));           
            Class calculatoClass=compiler.loadClass("com.prueba.pojo.CalculadoraDTO");
            
           // Class c= Class.forName("com.prueba.pojo.CalculadoraDTO");
         
            FileInputStream fis = new FileInputStream("E:\\temp\\DynamicClassCompile\\output\\CalculadoraDTO.dat");
           
         
            BufferedInputStream bis = new BufferedInputStream(fis);
           
            ois = new DynamicObjectInputStream(bis,calculatoClass.getClassLoader());   
            Object obj = ois.readObject();
            ois.close();
            System.out.println("Obj des:"+obj);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(SerializeClassMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CompilerWSClassException ex) {
            Logger.getLogger(SerializeClassMain.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(SerializeClassMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
