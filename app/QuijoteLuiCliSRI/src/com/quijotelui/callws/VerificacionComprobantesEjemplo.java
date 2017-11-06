package com.quijotelui.callws;

import com.quijotelui.ws.definicion.Estado;
import com.quijotelui.ws.definicion.AutorizacionEstado;
import com.quijotelui.ws.util.ArchivoUtils;
import com.quijotelui.ws.util.AutorizacionComprobantesUtil;
import com.quijotelui.ws.util.AutorizacionComprobantesWs;
import com.quijotelui.ws.xml.LectorXMLPath;
import ec.gob.sri.comprobantes.ws.aut.RespuestaComprobante;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPathConstants;

public final class VerificacionComprobantesEjemplo

{
 
  private void btnEnvioIndividualActionPerformed()
  {
 

      RespuestaComprobante respuestaComprobante = null;
      AutorizacionEstado autorizacionDTO = null;
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
                  System.out.println(Integer.valueOf(i + 1)+ nombreArchivo+ ArchivoUtils.obtieneTipoDeComprobante(claveAccesoComprobante)+ autorizacionDTO.getEstadoAutorizacion().getDescripcion());
                }
                else
                {
                  System.out.println(Integer.valueOf(i + 1)+ nombreArchivo+ ArchivoUtils.obtieneTipoDeComprobante(claveAccesoComprobante)+ Estado.NPR.getDescripcion()+"El archivo no tiene autorizaciones relacionadas");
                }
              }
              catch (Exception ex)
              {
                if (Estado.NAU.equals(autorizacionDTO.getEstadoAutorizacion())) {
                  ((File)archivosSeleccionados.get(i)).delete();
                }
                System.out.println(Integer.valueOf(i + 1)+ nombreArchivo+ArchivoUtils.obtieneTipoDeComprobante(claveAccesoComprobante)+ autorizacionDTO.getEstadoAutorizacion().getDescripcion()+ex.getMessage());
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
            Logger.getLogger(VerificacionComprobantesEjemplo.class.getName()).log(Level.SEVERE, null, ex);
          }
        }
      }
    }
    
  
  
}
