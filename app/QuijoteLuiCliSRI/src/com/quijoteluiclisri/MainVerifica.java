/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri;

import com.quijoteluiclisri.api.EstadoAutorizacion;
import com.quijoteluiclisri.dto.AutorizacionDTO;
import com.quijoteluiclisri.exception.RespuestaAutorizacionException;
import com.quijoteluiclisri.util.ArchivoUtils;
import com.quijoteluiclisri.util.AutorizacionComprobantesUtil;
import com.quijoteluiclisri.util.AutorizacionComprobantesWs;
import com.quijoteluiclisri.util.FormGenerales;
import com.quijoteluiclisri.util.xml.LectorXMLPath;
import ec.gob.sri.comprobantes.ws.aut.RespuestaComprobante;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathConstants;

/**
 *
 * @author jorgequiguango
 */
public class MainVerifica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        RespuestaComprobante respuestaComprobante = null;
        AutorizacionDTO autorizacionDTO = null;

        try {
            String nombreArchivo = "0710201701100245687700110010020000003381234567812.xml";
            byte[] archivoEnviado = ArchivoUtils.archivoToByte(new File("/data/work/tmp/facturacionelectronica/Firmados/"
                    + "0710201701100245687700110010020000003381234567812.xml"));
            LectorXMLPath lectorXMLPath = new LectorXMLPath(archivoEnviado, XPathConstants.STRING);
            String claveAccesoComprobante = lectorXMLPath.getClaveAcceso();
            String codDoc = lectorXMLPath.getCodDoc();
            String tipoComprobante = codDoc.substring(1);
            if ((tipoComprobante != null) && (claveAccesoComprobante != null)) {
                try {
                    respuestaComprobante = AutorizacionComprobantesWs.autorizarComprobante(claveAccesoComprobante, "1");
                    if (!respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()) {
                        AutorizacionComprobantesUtil autorizacionComprobantesUtil = new AutorizacionComprobantesUtil(respuestaComprobante, nombreArchivo);
                        autorizacionDTO = autorizacionComprobantesUtil.obtenerEstadoAutorizaccion();
                        autorizacionComprobantesUtil.validarRespuestaAutorizacion(autorizacionDTO);
                        System.out.println(nombreArchivo + FormGenerales.obtieneTipoDeComprobante(claveAccesoComprobante) + autorizacionDTO.getEstadoAutorizacion().getDescripcion());
                    } else {
                        System.out.println(nombreArchivo + FormGenerales.obtieneTipoDeComprobante(claveAccesoComprobante) + EstadoAutorizacion.NPR.getDescripcion() + "El archivo no tiene autorizaciones relacionadas");
                    }
                } catch (RespuestaAutorizacionException ex) {
                    if (EstadoAutorizacion.NAU.equals(autorizacionDTO.getEstadoAutorizacion())) {
                        System.out.println("Borra de la lista");
                    }
                    System.out.println( nombreArchivo + FormGenerales.obtieneTipoDeComprobante(claveAccesoComprobante) + autorizacionDTO.getEstadoAutorizacion().getDescripcion() + ex.getMessage());
                }
            } else {
                String m = "\n En:  la informaci��n <codDoc> y <claveAcceso> son obligatorias para el envio del archivo";
                System.out.println("Error al tratar de enviar el comprobante hacia el SRI: " + "Se ha producido un error ");
            }
        } catch (Exception ex) {
            Logger.getLogger(VerificacionComprobantesView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
