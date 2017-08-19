/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unico.ws.interfaces;

import com.unico.ws.entity.PublicadorWsEstructura;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author T13237
 */
@Local
public interface DynamicEstructuraWSSessionBeanLocal {
       List<PublicadorWsEstructura> findALLWebServicesWebToPublish();
       PublicadorWsEstructura updatePublicadorWsEstructura(PublicadorWsEstructura estructura);
        public PublicadorWsEstructura finPublicadorWsEstructuraById(Integer id);
        public void refreshContextEstructura();
}
