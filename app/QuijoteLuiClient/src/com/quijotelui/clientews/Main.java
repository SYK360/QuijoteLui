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
        Enviar enviar = new Enviar("/app/Quijotelui/comprobante/firmado/"
                + "1010201701100197312000110010030000012461234567817.xml",
                "/app/Quijotelui/comprobante/enviado",
                "/app/Quijotelui/comprobante/rechazado",
                "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl");
        enviar.executeEnviar();

        Comprobar comprovar = new Comprobar("/data/work/tmp/facturacionelectronica/Firmados/"
                + "1010201701100197312000110010030000012461234567817.xml",
                "/app/Quijotelui/comprobante/autorizado",
                "/app/Quijotelui/comprobante/noAutorizado",
                "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl");

        comprovar.executeComprobar();

    }

}
