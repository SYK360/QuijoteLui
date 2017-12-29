package com.quijotelui.clientews;

/**
 *
 * @author jorgequiguango
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Enviar enviar = new Enviar("/app/Quijotelui/comprobante/firmado/"
//                + "1210201701100245687700110010030000012481234567810.xml",
//                "/app/Quijotelui/comprobante/enviado",
//                "/app/Quijotelui/comprobante/rechazado",
//                "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl");
//        enviar.executeEnviar();
        
        

//        Comprobar comprovar = new Comprobar("/data/Quijotelui/comprobante/enviado/"
//                + "1510201701100245687700110010030000012621234567810.xml",
//                "/app/Quijotelui/comprobante/autorizado",
//                "/app/Quijotelui/comprobante/noAutorizado",
//                "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl");
//
//        comprovar.executeComprobar();
        
        Comprobar comprovar = new Comprobar("/app/Quijotelui", 
                "/app/Quijotelui/", 
                "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl");
        
        comprovar.executeComprobar("051220170110024568770011001002000001079123456781");
    }

}
