/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.interfaces;

import com.unico.ws.exception.DynamicWSException;
import com.unico.ws.object.RequestDynamicWSJMS;
import javax.ejb.Local;

/**
 *
 * @author T13237
 */
@Local
public interface DynamicProductorJMSSesBeanLocal {

    public void enviaMensajeCola(RequestDynamicWSJMS request)throws DynamicWSException;
    
}
