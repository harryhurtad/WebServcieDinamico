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
public class DynamicWSException extends Exception {

    /**
     * Creates a new instance of <code>DynamicWSException</code> without detail
     * message.
     */
    public DynamicWSException() {
    }

    /**
     * Constructs an instance of <code>DynamicWSException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DynamicWSException(String msg) {
        super(msg);
    }
}
