/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.callws;

import java.io.File;

/**
 *
 * @author jorgequiguango
 */
public class MainEnvio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          Enviar enviar = new Enviar("/data/work/tmp/facturacionelectronica/Firmados/"
                  + "1010201701100197312000110010030000012461234567817.xml", 
                  "/tmp", 
                  "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl");
          enviar.executeEnviar();

    }

}
