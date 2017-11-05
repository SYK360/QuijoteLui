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

import com.quijoteluiclisri.exception.RespuestaAutorizacionException;

import com.quijoteluiclisri.util.xml.XStreamUtil;
import com.thoughtworks.xstream.XStream;

import ec.gob.sri.comprobantes.ws.aut.Autorizacion;
import ec.gob.sri.comprobantes.ws.aut.AutorizacionComprobantesOffline;
import ec.gob.sri.comprobantes.ws.aut.AutorizacionComprobantesOfflineService;
import ec.gob.sri.comprobantes.ws.aut.Mensaje;
import ec.gob.sri.comprobantes.ws.aut.RespuestaComprobante;
import ec.gob.sri.comprobantes.ws.aut.RespuestaLote;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.namespace.QName;

public class AutorizacionComprobantesWs
{
  private AutorizacionComprobantesOfflineService service;
  public static final String ESTADO_AUTORIZADO = "AUTORIZADO";
  public static final String ESTADO_EN_PROCESO = "EN PROCESO";
  public static final String ESTADO_NO_AUTORIZADO = "NO AUTORIZADO";
  
  public AutorizacionComprobantesWs(String wsdlLocation)
  {
    try
    {
      this.service = new AutorizacionComprobantesOfflineService(new URL(wsdlLocation), new QName("http://ec.gob.sri.ws.autorizacion", "AutorizacionComprobantesOfflineService"));
    }
    catch (Exception ex)
    {
      Logger.getLogger(AutorizacionComprobantesWs.class.getName()).log(Level.SEVERE, null, ex);
      JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Se ha producido un error ", 0);
    }
  }
  
  public RespuestaComprobante llamadaWSAutorizacionInd(String claveDeAcceso)
  {
    RespuestaComprobante response = null;
    try
    {
      AutorizacionComprobantesOffline port = this.service.getAutorizacionComprobantesOfflinePort();
      response = port.autorizacionComprobante(claveDeAcceso);
    }
    catch (Exception e)
    {
      Logger.getLogger(AutorizacionComprobantesWs.class.getName()).log(Level.SEVERE, null, e);
      return response;
    }
    return response;
  }
  
  public RespuestaLote llamadaWsAutorizacionLote(String claveDeAcceso)
  {
    RespuestaLote response = null;
    try
    {
      AutorizacionComprobantesOffline port = this.service.getAutorizacionComprobantesOfflinePort();
      response = port.autorizacionComprobanteLote(claveDeAcceso);
    }
    catch (Exception e)
    {
      Logger.getLogger(AutorizacionComprobantesWs.class.getName()).log(Level.SEVERE, null, e);
      return response;
    }
    return response;
  }
  
  public static String autorizarComprobanteIndividual(String claveDeAcceso, String nombreArchivo, String tipoAmbiente)
  {
    StringBuilder mensaje = new StringBuilder();
    try
    {
        String dirAutorizados = "/tmp/autorizado";
        String dirNoAutorizados = "/tmp/noautorizado";
      
      RespuestaComprobante respuesta = null;
      for (int i = 0; i < 5; i++)
      {
        respuesta = new AutorizacionComprobantesWs(FormGenerales.devuelveUrlWs(tipoAmbiente, "AutorizacionComprobantesOffline")).llamadaWSAutorizacionInd(claveDeAcceso);
        if (!respuesta.getAutorizaciones().getAutorizacion().isEmpty()) {
          break;
        }
        Thread.currentThread();Thread.sleep(300L);
      }
      if (respuesta != null)
      {
          int i = 0;
        for (Autorizacion item : respuesta.getAutorizaciones().getAutorizacion())
        {
          mensaje.append(item.getEstado());
          
          item.setComprobante("<![CDATA[" + item.getComprobante() + "]]>");
          
          XStream xstream = XStreamUtil.getRespuestaXStream();
          Writer writer = null;
          ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
          writer = new OutputStreamWriter(outputStream, "UTF-8");
          writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
          
          xstream.toXML(item, writer);
          String xmlAutorizacion = outputStream.toString("UTF-8");
          if ((i == 0) && (item.getEstado().equals("AUTORIZADO")))
          {
            ArchivoUtils.stringToArchivo(dirAutorizados + File.separator + nombreArchivo, xmlAutorizacion);
            break;
          }
          if (item.getEstado().equals("NO AUTORIZADO"))
          {
            ArchivoUtils.stringToArchivo(dirNoAutorizados + File.separator + nombreArchivo, xmlAutorizacion);
            mensaje.append("|" + obtieneMensajesAutorizacion(item));
            
            verificarOCSP(item);
            
            break;
          }
          if (item.getEstado().equals("EN PROCESO"))
          {
            mensaje.append("|" + obtieneMensajesAutorizacion(item));
            break;
          }
          i++;
        }
      }
    }
    catch (Exception ex)
    {
      String dirAutorizados;
      String dirNoAutorizados;
      int i;
      Logger.getLogger(AutorizacionComprobantesWs.class.getName()).log(Level.SEVERE, null, ex);
    }
    return mensaje.toString();
  }
  
  public static RespuestaComprobante autorizarComprobante(String claveDeAcceso, String tipoAmbiente)
    throws RespuestaAutorizacionException
  {
    return new AutorizacionComprobantesWs(FormGenerales.devuelveUrlWs(tipoAmbiente, "AutorizacionComprobantesOffline")).llamadaWSAutorizacionInd(claveDeAcceso);
  }
 
  
  public static String obtieneMensajesAutorizacion(Autorizacion autorizacion)
  {
    StringBuilder mensaje = new StringBuilder();
    for (Mensaje m : autorizacion.getMensajes().getMensaje()) {
      if (m.getInformacionAdicional() != null) {
        mensaje.append("\n" + m.getMensaje() + ": " + m.getInformacionAdicional());
      } else {
        mensaje.append("\n" + m.getMensaje());
      }
    }
    return mensaje.toString();
  }
  
  public static boolean verificarOCSP(Autorizacion autorizacion)
    throws SQLException, ClassNotFoundException
  {
    boolean respuesta = true;
    for (Mensaje m : autorizacion.getMensajes().getMensaje()) {
      if (m.getIdentificador().equals("61"))
      {
        int i = JOptionPane.showConfirmDialog(null, "No se puede validar el certificado digital.\n Desea emitir en contingencia?", "Advertencia", 0);
        if (i == 0)
        {
            i=0;
//          FormGenerales.actualizaEmisor(TipoEmisionEnum.PREAUTORIZADA.getCode(), emisor);
        }
        respuesta = false;
      }
    }
    return respuesta;
  }
}
