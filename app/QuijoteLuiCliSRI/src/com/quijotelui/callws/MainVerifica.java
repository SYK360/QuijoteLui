/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.callws;

import com.quijotelui.ws.define.Estado;
import com.quijotelui.ws.dto.AutorizacionDTO;
import com.quijotelui.ws.util.ArchivoUtils;
import com.quijotelui.ws.util.AutorizacionComprobantesUtil;
import com.quijotelui.ws.util.AutorizacionComprobantesWs;
import com.quijotelui.ws.xml.LectorXMLPath;
import ec.gob.sri.comprobantes.ws.aut.RespuestaComprobante;
import java.io.File;
import java.io.IOException;
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
            String nombreArchivo = "0710201701100245687700110010030000012431234567812.xml";
            byte[] archivoEnviado = ArchivoUtils.archivoToByte(new File("/data/work/tmp/facturacionelectronica/Firmados/"
                    + "0710201701100245687700110010030000012431234567812.xml"));
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
                        System.out.println(nombreArchivo + ArchivoUtils.obtieneTipoDeComprobante(claveAccesoComprobante) + autorizacionDTO.getEstadoAutorizacion().getDescripcion());
                    } else {
                        System.out.println(nombreArchivo + ArchivoUtils.obtieneTipoDeComprobante(claveAccesoComprobante) + Estado.NPR.getDescripcion() + "El archivo no tiene autorizaciones relacionadas");
                    }
                } catch (Exception ex) {
                    if (Estado.NAU.equals(autorizacionDTO.getEstadoAutorizacion())) {
                        System.out.println("Borra de la lista");
                    }
                    System.out.println( nombreArchivo + ArchivoUtils.obtieneTipoDeComprobante(claveAccesoComprobante) + autorizacionDTO.getEstadoAutorizacion().getDescripcion() + ex.getMessage());
                }
            } else {
                String m = "\n En:  la información <codDoc> y <claveAcceso> son obligatorias para el envio del archivo";
                System.out.println("Error al tratar de enviar el comprobante hacia el SRI: " + "Se ha producido un error ");
            }
        } catch (IOException ex) {
            Logger.getLogger(VerificacionComprobantesEjemplo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
