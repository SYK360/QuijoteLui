/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri.util;

/**
 *
 * @author jorgequiguango
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.MalformedURLException;

public class FormGenerales {

    private static char SEPARADOR_DECIMAL = '.';

    public static String devuelveUrlWs(String ambiente, String nombreServicio) {
        StringBuilder url = new StringBuilder();
        String direccionIPServicio = null;

        if (ambiente.equals("1") == true) {
            direccionIPServicio = "https://celcer.sri.gob.ec";
        } else if (ambiente.equals("2") == true) {
            direccionIPServicio = "https://celcer.sri.gob.ec";
        }
        url.append(direccionIPServicio);

        url.append("/comprobantes-electronicos-ws/");
        url.append(nombreServicio);
        url.append("?wsdl");
        System.out.print(url.toString());

        return url.toString();
    }


    public static String obtieneTipoDeComprobante(String claveDeAcceso) {
        String abreviatura = null;
        if ((claveDeAcceso != null) && (claveDeAcceso.length() == 49)) {
            String tipo = claveDeAcceso.substring(8, 10);
            if (tipo.equals(TipoComprobanteEnum.FACTURA.getCode())) {
                abreviatura = TipoComprobanteEnum.FACTURA.getDescripcion();
            } else if (tipo.equals(TipoComprobanteEnum.NOTA_DE_DEBITO.getCode())) {
                abreviatura = TipoComprobanteEnum.NOTA_DE_DEBITO.getDescripcion();
            } else if (tipo.equals(TipoComprobanteEnum.NOTA_DE_CREDITO.getCode())) {
                abreviatura = TipoComprobanteEnum.NOTA_DE_CREDITO.getDescripcion();
            } else if (tipo.equals(TipoComprobanteEnum.GUIA_DE_REMISION.getCode())) {
                abreviatura = TipoComprobanteEnum.GUIA_DE_REMISION.getDescripcion();
            } else if (tipo.equals(TipoComprobanteEnum.COMPROBANTE_DE_RETENCION.getCode())) {
                abreviatura = TipoComprobanteEnum.COMPROBANTE_DE_RETENCION.getDescripcion();
            } else if (tipo.equals(TipoComprobanteEnum.LOTE.getCode())) {
                abreviatura = TipoComprobanteEnum.LOTE.getDescripcion();
            }
        }
        return abreviatura;
    }

    public static String insertarCaracteres(String cadenaLarga, String aInsertar, int longitud) {
        StringBuilder sb = new StringBuilder(cadenaLarga);

        int i = 0;
        while ((i = sb.indexOf(" ", i + longitud)) != -1) {
            sb.replace(i, i + 1, aInsertar);
        }
        return sb.toString();
    }
}