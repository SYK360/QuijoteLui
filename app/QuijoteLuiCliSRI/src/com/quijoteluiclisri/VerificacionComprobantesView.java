/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri;

/**
 *
 * @author jorgequiguango
 */
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathConstants;

public final class VerificacionComprobantesView

{
  
  private String directorioEnviados;
  private int showOnlyOnce = 0;
  private int rowEvent = 0;
  static final SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy-hhmmss");

  
  private void btnEnvioIndividualActionPerformed()
  {
 

      RespuestaComprobante respuestaComprobante = null;
      AutorizacionDTO autorizacionDTO = null;
      List<File> archivosSeleccionados = new ArrayList();
      if (!archivosSeleccionados.isEmpty()) {
        for (int i = 0; i < archivosSeleccionados.size(); i++) {
          try
          {
            String nombreArchivo = ((File)archivosSeleccionados.get(i)).getName();
            byte[] archivoEnviado = ArchivoUtils.archivoToByte((File)archivosSeleccionados.get(i));
            LectorXMLPath lectorXMLPath = new LectorXMLPath(archivoEnviado, XPathConstants.STRING);
            String claveAccesoComprobante = lectorXMLPath.getClaveAcceso();
            String codDoc = lectorXMLPath.getCodDoc();
            String tipoComprobante = codDoc.substring(1);
            if ((tipoComprobante != null) && (claveAccesoComprobante != null))
            {
              try
              {
                respuestaComprobante = AutorizacionComprobantesWs.autorizarComprobante(claveAccesoComprobante, /*this.emisor.getTipoAmbiente()*/"1");
                if (!respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty())
                {
                  AutorizacionComprobantesUtil autorizacionComprobantesUtil = new AutorizacionComprobantesUtil(respuestaComprobante, nombreArchivo);
                  autorizacionDTO = autorizacionComprobantesUtil.obtenerEstadoAutorizaccion();
                  autorizacionComprobantesUtil.validarRespuestaAutorizacion(autorizacionDTO);
                  ((File)archivosSeleccionados.get(i)).delete();
                  System.out.println(Integer.valueOf(i + 1)+ nombreArchivo+ FormGenerales.obtieneTipoDeComprobante(claveAccesoComprobante)+ autorizacionDTO.getEstadoAutorizacion().getDescripcion());
                }
                else
                {
                  System.out.println(Integer.valueOf(i + 1)+ nombreArchivo+ FormGenerales.obtieneTipoDeComprobante(claveAccesoComprobante)+ EstadoAutorizacion.NPR.getDescripcion()+"El archivo no tiene autorizaciones relacionadas");
                }
              }
              catch (RespuestaAutorizacionException ex)
              {
                if (EstadoAutorizacion.NAU.equals(autorizacionDTO.getEstadoAutorizacion())) {
                  ((File)archivosSeleccionados.get(i)).delete();
                }
                System.out.println(Integer.valueOf(i + 1)+ nombreArchivo+FormGenerales.obtieneTipoDeComprobante(claveAccesoComprobante)+ autorizacionDTO.getEstadoAutorizacion().getDescripcion()+ex.getMessage());
              }
            }
            else
            {
              String m = "\n En: " + archivosSeleccionados.get(i) + " la informaci��n <codDoc> y <claveAcceso> son obligatorias para el envio del archivo";
              System.out.println("Error al tratar de enviar el comprobante hacia el SRI: " +"Se ha producido un error ");
            }
          }
          catch (Exception ex)
          {
            Logger.getLogger(VerificacionComprobantesView.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }
    
  
  
}
