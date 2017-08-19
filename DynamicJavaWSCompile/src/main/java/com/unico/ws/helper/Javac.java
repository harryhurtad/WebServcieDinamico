/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.helper;

import com.unico.ws.exception.CompilerWSClassException;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringBufferInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 *
 * @author proveedor_hhurtado
 */
public class Javac {

    String classpath;

    String outputdir;

    String sourcepath;

    String bootclasspath;

    String extdirs;

    String encoding;

    String target;

    public Javac(String classpath, String outputdir) {
        this.classpath = classpath;
        this.outputdir = outputdir;
    }

    /**
     * Compile the given source files.
     *
     * @param srcFiles
     * @return null if success; or compilation errors
     * @throws com.dataconnector.compiler.CompilerDataConnectorException
     */
    public void compile(String srcFiles[]) throws CompilerWSClassException {
        StringWriter err = new StringWriter();
        int resultCode;
        try (PrintWriter errPrinter = new PrintWriter(err)) {
            String args[] = buildJavacArgs(srcFiles);
            resultCode = com.sun.tools.javac.Main.compile(args, errPrinter);
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            //JavaCompiler compiler= ToolProvider.getSystemJavaCompiler();
            //compiler.run(null, null, null, args);
           
            //       errPrinter.flush();
            //  errPrinter.println();
        }
       
        if(resultCode!=0){
          throw  new CompilerWSClassException(err.toString());
        }
        
       
    }

    public void compile(File srcFiles[]) throws CompilerWSClassException {
        String paths[] = new String[srcFiles.length];
        for (int i = 0; i < paths.length; i++) {
            paths[i] = srcFiles[i].getAbsolutePath();
        }
        compile(paths);
    }

    private String[] buildJavacArgs(String srcFiles[]) {
        ArrayList args = new ArrayList();

        if (classpath != null) {
            args.add("-classpath");
            args.add(classpath);
        }
        if (outputdir != null) {
            args.add("-d");
            args.add(outputdir);
        }
        if (sourcepath != null) {
            args.add("-sourcepath");
            args.add(sourcepath);
        }
        if (bootclasspath != null) {
            args.add("-bootclasspath");
            args.add(bootclasspath);
        }
        if (extdirs != null) {
            args.add("-extdirs");
            args.add(extdirs);
        }
        if (encoding != null) {
            args.add("-encoding");
            args.add(encoding);
        }
        if (target != null) {
            args.add("-target");
            args.add(target);
        }

        for (int i = 0; i < srcFiles.length; i++) {
            args.add(srcFiles[i]);
        }

        return (String[]) args.toArray(new String[args.size()]);
    }

    public String getBootclasspath() {
        return bootclasspath;
    }

    public void setBootclasspath(String bootclasspath) {
        this.bootclasspath = bootclasspath;
    }

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getExtdirs() {
        return extdirs;
    }

    public void setExtdirs(String extdirs) {
        this.extdirs = extdirs;
    }

    public String getOutputdir() {
        return outputdir;
    }

    public void setOutputdir(String outputdir) {
        this.outputdir = outputdir;
    }

    public String getSourcepath() {
        return sourcepath;
    }

    public void setSourcepath(String sourcepath) {
        this.sourcepath = sourcepath;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
