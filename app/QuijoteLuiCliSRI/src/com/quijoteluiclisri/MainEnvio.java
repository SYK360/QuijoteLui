/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri;

import com.quijoteluiclisri.exception.ConvertidorXMLException;
import com.quijoteluiclisri.exception.DirectorioException;
import com.quijoteluiclisri.exception.MergeRespuestaException;
import com.quijoteluiclisri.exception.RespuestaEnvioException;
import com.quijoteluiclisri.util.ArchivoUtils;
import com.quijoteluiclisri.util.EnvioComprobantesWs;
import com.quijoteluiclisri.util.FormGenerales;
import com.quijoteluiclisri.util.xml.LectorXMLPath;
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathConstants;

/**
 *
 * @author jorgequiguango
 */
public class MainEnvio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String claveAccesoComprobante = "";
        RespuestaSolicitud respuestaSolicitudEnvio = new RespuestaSolicitud();

        try {

            File archivoXMLFirmadoFile = new File("/data/work/tmp/facturacionelectronica/Firmados/"
                    + "0710201701100245687700110010020000003371234567817.xml");
            String nombreArchivo = "0710201701100245687700110010020000003371234567817.xml";
            byte[] archivoXMLFirmadoByte = ArchivoUtils.archivoToByte(archivoXMLFirmadoFile);
            LectorXMLPath lectorXMLPath;
            lectorXMLPath = new LectorXMLPath(archivoXMLFirmadoByte, XPathConstants.STRING);
            claveAccesoComprobante = lectorXMLPath.getClaveAcceso();
            String codDoc = lectorXMLPath.getCodDoc();
            String tipoComprobante = codDoc.substring(1);
            String direccion = FormGenerales.devuelveUrlWs("1", "RecepcionComprobantesOffline");
            respuestaSolicitudEnvio = EnvioComprobantesWs.obtenerRespuestaEnvio(archivoXMLFirmadoFile, "1002456877001", tipoComprobante, claveAccesoComprobante, direccion);
            ArchivoUtils.validarRespuestaEnvio(respuestaSolicitudEnvio, archivoXMLFirmadoByte, nombreArchivo);
            System.out.println(respuestaSolicitudEnvio.getEstado() + " " + "El comprobante fue enviado, está pendiente de autorización");

        } catch (IOException | ConvertidorXMLException | DirectorioException | MergeRespuestaException | RespuestaEnvioException ex) {
            Logger.getLogger(MainEnvio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
