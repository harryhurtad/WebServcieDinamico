/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.exception;

/**
 *
 * @author T13237
 */
public class CompilerWSClassException extends Exception {
     /* Creates a new instance of <code>CompilerDataConnectorException</code>
     * without detail message.
     */
    public CompilerWSClassException() {
    }

    /**
     * Constructs an instance of <code>CompilerDataConnectorException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CompilerWSClassException(String msg) {
        super(msg);
    }
    
}
