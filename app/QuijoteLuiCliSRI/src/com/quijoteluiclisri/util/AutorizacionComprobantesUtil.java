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
import com.quijoteluiclisri.api.EstadoAutorizacion;
import com.quijoteluiclisri.dto.AutorizacionDTO;
import com.quijoteluiclisri.exception.ConvertidorXMLException;
import com.quijoteluiclisri.exception.MergeRespuestaException;
import com.quijoteluiclisri.exception.RespuestaAutorizacionException;
import com.quijoteluiclisri.util.xml.XStreamUtil;
import com.thoughtworks.xstream.XStream;
import ec.gob.sri.comprobantes.ws.aut.Autorizacion;
import ec.gob.sri.comprobantes.ws.aut.Mensaje;
import ec.gob.sri.comprobantes.ws.aut.RespuestaComprobante;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutorizacionComprobantesUtil {

    private RespuestaComprobante respuestaComprobante;
    private String nombreArchivo;

    public AutorizacionComprobantesUtil(RespuestaComprobante respuestaComprobante, String nombreArchivo) {
        this.respuestaComprobante = respuestaComprobante;
        this.nombreArchivo = nombreArchivo;
    }

    public void validarRespuestaAutorizacion(AutorizacionDTO autorizacionDTO)
            throws MergeRespuestaException, RespuestaAutorizacionException, ConvertidorXMLException {
        byte[] archivoRespuestaAutorizacionXML = obtenerRepuestaAutorizacionXML(autorizacionDTO.getAutorizacion());
        if (EstadoAutorizacion.AUT.equals(autorizacionDTO.getEstadoAutorizacion())) {
            try {
                ArchivoUtils.crearArchivo(archivoRespuestaAutorizacionXML, this.nombreArchivo, DirectorioEnum.AUTORIZADOS);
            } catch (Exception ex) {
                Logger.getLogger(AutorizacionComprobantesUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (EstadoAutorizacion.NAU.equals(autorizacionDTO.getEstadoAutorizacion())) {
                try {
                    ArchivoUtils.crearArchivo(archivoRespuestaAutorizacionXML, this.nombreArchivo, DirectorioEnum.NO_AUTORIZADOS);
                    throw new RespuestaAutorizacionException(String.format("Error al validar el comprobante estado : %s \n%s", new Object[]{autorizacionDTO.getEstadoAutorizacion().getDescripcion(), autorizacionDTO.getMensaje()}));
                } catch (RespuestaAutorizacionException ex) {
                    Logger.getLogger(AutorizacionComprobantesUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (EstadoAutorizacion.PRO.equals(autorizacionDTO.getEstadoAutorizacion())) {
                throw new RespuestaAutorizacionException(String.format("Error al validar el comprobante estado : %s \n", new Object[]{autorizacionDTO.getEstadoAutorizacion().getDescripcion()}));
            }
        }
    }

    public AutorizacionDTO obtenerEstadoAutorizaccion() {
        for (Autorizacion autorizacion : this.respuestaComprobante.getAutorizaciones().getAutorizacion()) {
            EstadoAutorizacion estadoAutorizacion = EstadoAutorizacion.getEstadoAutorizacion(autorizacion.getEstado());
            if (EstadoAutorizacion.AUT.equals(estadoAutorizacion)) {
                return new AutorizacionDTO(autorizacion, EstadoAutorizacion.AUT);
            }
            if (EstadoAutorizacion.PRO.equals(estadoAutorizacion)) {
                return new AutorizacionDTO(autorizacion, EstadoAutorizacion.AUT);
            }
        }
        Autorizacion autorizacion = (Autorizacion) this.respuestaComprobante.getAutorizaciones().getAutorizacion().get(0);

        return new AutorizacionDTO(autorizacion, EstadoAutorizacion.NAU, obtieneMensajesAutorizacion(autorizacion));
    }

    private void setXMLCDATA(Autorizacion autorizacion) {
        autorizacion.setComprobante("<![CDATA[" + autorizacion.getComprobante() + "]]>");
    }

  private byte[] obtenerRepuestaAutorizacionXML(Autorizacion autorizacion)
    throws ConvertidorXMLException
  {
    try
    {
      XStream xstream = XStreamUtil.getRespuestaXStream();
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
      setXMLCDATA(autorizacion);
      writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      xstream.toXML(autorizacion, writer);
      return outputStream.toByteArray();
    }
    catch (IOException ex)
    {
      throw new ConvertidorXMLException("Se produjo un error al convetir el archivo al formato XML", ex);
    }
  }

    public static String obtieneMensajesAutorizacion(Autorizacion autorizacion) {
        StringBuilder mensaje = new StringBuilder();
        for (Mensaje m : autorizacion.getMensajes().getMensaje()) {
            if (m.getInformacionAdicional() != null) {
                mensaje.append(String.format("\n%s:%s", new Object[]{m.getMensaje(), m.getInformacionAdicional()}));
            } else {
                mensaje.append(String.format("\n%s", new Object[]{m.getMensaje()}));
            }
        }
        return mensaje.toString();
    }
}
