/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.main;

import com.sun.codemodel.internal.JCodeModel;
import com.sun.tools.internal.xjc.api.S2JJAXBModel;
import com.sun.tools.internal.xjc.api.SchemaCompiler;
import com.sun.tools.internal.xjc.api.XJC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.InputSource;

/**
 *
 * @author T13237
 */
public class GenerateJaxbMain {

    public static void main(String[] args) {
        try {
          //String schemaPath = "E:\\temp\\DynamicClassCompile\\schema\\calculadora.xsd";
          String schemaPath = "E:\\temp\\DynamicClassCompile\\schema\\ConsultaMonitorLog.xsd";
//               String schemaPath = "E:\\temp\\DynamicClassCompile\\schema\\testing.xsd";
            String outputDirectory = "E:\\temp\\DynamicClassCompile\\schema\\out";

// Setup schema compiler

            SchemaCompiler sc = XJC.createSchemaCompiler();
          //  sc.forcePackageName("com.prueba.pojo");
          sc.forcePackageName("com.unico.ws.webservice");
            
// Setup SAX InputSource
            File schemaFile = new File(schemaPath);
            InputSource is = new InputSource(new FileInputStream(schemaFile));
            is.setSystemId("1");

// Parse & build
            sc.parseSchema(is);
            
            S2JJAXBModel model = sc.bind();
            
            JCodeModel jCodeModel = model.generateCode(null, null);
            jCodeModel.build(new File(outputDirectory));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerateJaxbMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GenerateJaxbMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
