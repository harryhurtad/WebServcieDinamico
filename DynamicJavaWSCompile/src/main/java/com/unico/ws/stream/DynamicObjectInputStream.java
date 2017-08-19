/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 *
 * @author T13237
 */
public class DynamicObjectInputStream extends ObjectInputStream {
    private final ClassLoader loader;
    
        public DynamicObjectInputStream(InputStream in,ClassLoader classLoader) throws IOException{
            super(in);
            this.loader=classLoader;
            
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
       String name = desc.getName();
        
            return Class.forName(name, false, loader);
        
    }
        
}
