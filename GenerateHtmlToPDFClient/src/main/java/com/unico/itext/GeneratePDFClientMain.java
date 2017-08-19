/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.itext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author T13237
 */
public class GeneratePDFClientMain {

    public static void main(String[] args) {
        try {
            StringBuilder command = new StringBuilder();
            String java_home = "\"C:\\Program Files\\Java\\jdk1.6.0_45\"";
            String paramReq = args[0];
           // command.append("C:\\temp\\generatePDF.bat");
          //  command.append(" ");
            command.append(java_home);
           // command.append(" ");
          //  command.append(paramReq);
            ProcessBuilder   ps=new ProcessBuilder("C:\\temp\\generatePDF.bat",command.toString(),paramReq);
            ps.redirectErrorStream(true);
            Process p = ps.start();
         //   Process p = Runtime.getRuntime().exec(command.toString());

         //  

            BufferedReader reader
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuffer output = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
             p.waitFor();
            System.out.println(output);
        } catch (IOException ex) {
            Logger.getLogger(GeneratePDFClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GeneratePDFClientMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
