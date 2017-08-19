/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prueba;

import com.ath.intranet.vo.ConceptoOperacionData;
import com.ath.intranet.vo.ConceptoOtrData;
import com.ath.intranet.vo.OrdenTransferenciaData;
import com.ath.intranet.vo.OrdenTransferenciaTablaDetalleData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author T13237
 */
public class TestDatasourceFactory2 {
    public static java.util.Collection getOrdenTransferenciaTablaDetalleData() {
        List<OrdenTransferenciaTablaDetalleData> listDatos = new ArrayList();
        
       
        OrdenTransferenciaTablaDetalleData detalleDato1 = new OrdenTransferenciaTablaDetalleData();
        detalleDato1.setConceptoOtrData(new ConceptoOtrData());
        detalleDato1.getConceptoOtrData().setIdconceptooperacion(new ConceptoOperacionData());
        detalleDato1.getConceptoOtrData().getIdconceptooperacion().setIdconceptooperacion(153);
        detalleDato1.getConceptoOtrData().setDescripcion("Valor en Transacciones");
        detalleDato1.setNumeroTX_ADQ(null);
        detalleDato1.setValor_ADQ(0d);
        detalleDato1.setNumeroTX_AUT(0);
        detalleDato1.setValor_AUT(0d);
        detalleDato1.setNetoCompensar(0d);
       
        OrdenTransferenciaTablaDetalleData detalleDato2 = new OrdenTransferenciaTablaDetalleData();
        detalleDato2.setConceptoOtrData(new ConceptoOtrData());
        detalleDato2.getConceptoOtrData().setIdconceptooperacion(new ConceptoOperacionData());
        detalleDato2.getConceptoOtrData().getIdconceptooperacion().setIdconceptooperacion(153);
        detalleDato2.getConceptoOtrData().setDescripcion("Transacciones Exitosas");
        detalleDato2.setNumeroTX_ADQ(0);
        detalleDato2.setValor_ADQ(null);
        detalleDato2.setNumeroTX_AUT(0);
        detalleDato2.setValor_AUT(null);
        detalleDato2.setNetoCompensar(null);      
       
        listDatos.add(detalleDato1);
        listDatos.add(detalleDato2);
       
        return listDatos;
    }
}
