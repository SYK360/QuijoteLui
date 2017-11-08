package com.quijotelui.ws.util;

import com.quijotelui.ws.definicion.Estado;
import com.quijotelui.ws.definicion.AutorizacionEstado;
import com.quijotelui.ws.xml.XStreamAutorizacion;
import com.thoughtworks.xstream.XStream;
import ec.gob.sri.comprobantes.ws.aut.Autorizacion;
import ec.gob.sri.comprobantes.ws.aut.Mensaje;
import ec.gob.sri.comprobantes.ws.aut.RespuestaComprobante;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

    public File validarRespuestaAutorizacion(AutorizacionEstado autorizacionEstado, String directorioAutorizado, String directorioNoAutorizado) {
        
        File archivoRespuesta = null;
        
        byte[] archivoRespuestaAutorizacionXML = obtenerRepuestaAutorizacionXML(autorizacionEstado.getAutorizacion());
        if (Estado.AUTORIZADO.equals(autorizacionEstado.getEstadoAutorizacion())) {
            try {
                //Autorizado
                archivoRespuesta = ArchivoUtils.crearArchivo(archivoRespuestaAutorizacionXML, this.nombreArchivo, directorioAutorizado);
            } catch (Exception ex) {
                Logger.getLogger(AutorizacionComprobantesUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (Estado.NO_AUTORIZADO.equals(autorizacionEstado.getEstadoAutorizacion())) {
                try {
                    //No Autorizado
                    archivoRespuesta = ArchivoUtils.crearArchivo(archivoRespuestaAutorizacionXML, this.nombreArchivo, directorioNoAutorizado);
                    System.out.println("Error al validar el comprobante estado " + autorizacionEstado.getEstadoAutorizacion().getDescripcion() + autorizacionEstado.getMensaje());
                } catch (Exception ex) {
                    Logger.getLogger(AutorizacionComprobantesUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (Estado.PROCESADO.equals(autorizacionEstado.getEstadoAutorizacion())) {
                System.out.println("Error al validar el comprobante estado : " + autorizacionEstado.getEstadoAutorizacion().getDescripcion());
            }
        }
        return archivoRespuesta;
    }

    public AutorizacionEstado obtenerEstadoAutorizaccion() {
        for (Autorizacion autorizacion : this.respuestaComprobante.getAutorizaciones().getAutorizacion()) {
            Estado estadoAutorizacion = Estado.getEstadoAutorizacion(autorizacion.getEstado());
            if (Estado.AUTORIZADO.equals(estadoAutorizacion)) {
                return new AutorizacionEstado(autorizacion, Estado.AUTORIZADO);
            }
            if (Estado.PROCESADO.equals(estadoAutorizacion)) {
                return new AutorizacionEstado(autorizacion, Estado.AUTORIZADO);
            }
        }
        Autorizacion autorizacion = (Autorizacion) this.respuestaComprobante.getAutorizaciones().getAutorizacion().get(0);

        return new AutorizacionEstado(autorizacion, Estado.NO_AUTORIZADO, obtieneMensajesAutorizacion(autorizacion));
    }

    private void setXMLCDATA(Autorizacion autorizacion) {
        autorizacion.setComprobante("<![CDATA[" + autorizacion.getComprobante() + "]]>");
    }

    private byte[] obtenerRepuestaAutorizacionXML(Autorizacion autorizacion) {
        try {
            XStream xstream = XStreamAutorizacion.getRespuestaXStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
            setXMLCDATA(autorizacion);
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xstream.toXML(autorizacion, writer);
            return outputStream.toByteArray();
        } catch (IOException ex) {
            System.out.println("Se produjo un error al convetir el archivo al formato XML" + ex.getMessage());
        }
        return null;
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
