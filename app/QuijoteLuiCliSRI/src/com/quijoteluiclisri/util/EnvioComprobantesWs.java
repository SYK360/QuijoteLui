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


import ec.gob.sri.comprobantes.ws.Comprobante;
import ec.gob.sri.comprobantes.ws.Mensaje;
import ec.gob.sri.comprobantes.ws.RecepcionComprobantesOffline;
import ec.gob.sri.comprobantes.ws.RecepcionComprobantesOfflineService;
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceException;

public class EnvioComprobantesWs
{
  private static RecepcionComprobantesOfflineService service;
  private static final String VERSION = "1.0.0";
  public static final String ESTADO_RECIBIDA = "RECIBIDA";
  public static final String ESTADO_DEVUELTA = "DEVUELTA";
  
  public EnvioComprobantesWs(String wsdlLocation)
    throws MalformedURLException, WebServiceException
  {
    URL url = new URL(wsdlLocation);
    QName qname = new QName("http://ec.gob.sri.ws.recepcion", "RecepcionComprobantesOfflineService");
    service = new RecepcionComprobantesOfflineService(url, qname);
  }
  
  public static final Object webService(String wsdlLocation)
  {
    try
    {
      QName qname = new QName("http://ec.gob.sri.ws.recepcion", "RecepcionComprobantesOfflineService");
      URL url = new URL(wsdlLocation);
      service = new RecepcionComprobantesOfflineService(url, qname);
      return null;
    }
    catch (MalformedURLException ex)
    {
      Logger.getLogger(EnvioComprobantesWs.class.getName()).log(Level.SEVERE, null, ex);
      return ex;
    }
    catch (WebServiceException ws)
    {
      Logger.getLogger(EnvioComprobantesWs.class.getName()).log(Level.SEVERE, null, ws);
      return ws;
    }
  }
  
  public RespuestaSolicitud enviarComprobante(String ruc, File xmlFile, String tipoComprobante, String versionXsd)
  {
    RespuestaSolicitud response = null;
    try
    {
      RecepcionComprobantesOffline port = service.getRecepcionComprobantesOfflinePort();
      response = port.validarComprobante(ArchivoUtils.archivoToByte(xmlFile));
    }
    catch (Exception e)
    {
      Logger.getLogger(EnvioComprobantesWs.class.getName()).log(Level.SEVERE, null, e);
      response = new RespuestaSolicitud();
      response.setEstado(e.getMessage());
      return response;
    }
    return response;
  }
  
  public RespuestaSolicitud enviarComprobanteLotes(String ruc, byte[] xml, String tipoComprobante, String versionXsd)
  {
    RespuestaSolicitud response = null;
    try
    {
      RecepcionComprobantesOffline port = service.getRecepcionComprobantesOfflinePort();
      
      response = port.validarComprobante(xml);
    }
    catch (Exception e)
    {
      Logger.getLogger(EnvioComprobantesWs.class.getName()).log(Level.SEVERE, null, e);
      response = new RespuestaSolicitud();
      response.setEstado(e.getMessage());
      return response;
    }
    return response;
  }
  
  public RespuestaSolicitud enviarComprobanteLotes(String ruc, File xml, String tipoComprobante, String versionXsd)
  {
    RespuestaSolicitud response = null;
    try
    {
      RecepcionComprobantesOffline port = service.getRecepcionComprobantesOfflinePort();
      response = port.validarComprobante(ArchivoUtils.archivoToByte(xml));
    }
    catch (Exception e)
    {
      Logger.getLogger(EnvioComprobantesWs.class.getName()).log(Level.SEVERE, null, e);
      response = new RespuestaSolicitud();
      response.setEstado(e.getMessage());
      return response;
    }
    return response;
  }
  
  public static RespuestaSolicitud obtenerRespuestaEnvio(File archivo, String ruc, String tipoComprobante, String claveDeAcceso, String urlWsdl)
  {
    RespuestaSolicitud respuesta = new RespuestaSolicitud();
    EnvioComprobantesWs cliente = null;
    try
    {
      cliente = new EnvioComprobantesWs(urlWsdl);
    }
    catch (Exception ex)
    {
      Logger.getLogger(EnvioComprobantesWs.class.getName()).log(Level.SEVERE, null, ex);
      respuesta.setEstado(ex.getMessage());
      return respuesta;
    }
    respuesta = cliente.enviarComprobante(ruc, archivo, tipoComprobante, "1.0.0");
    
    return respuesta;
  }
  
  public static RespuestaSolicitud obtenerRespuestaEnvio1(File archivo, String ruc, String tipoComprobante, String claveDeAcceso, String urlWsdl)
  {
    RespuestaSolicitud respuesta = new RespuestaSolicitud();
    EnvioComprobantesWs cliente = null;
    try
    {
      cliente = new EnvioComprobantesWs(urlWsdl);
    }
    catch (Exception ex)
    {
      Logger.getLogger(EnvioComprobantesWs.class.getName()).log(Level.SEVERE, null, ex);
      respuesta.setEstado(ex.getMessage());
      return respuesta;
    }
    respuesta = cliente.enviarComprobante(ruc, archivo, tipoComprobante, "1.0.0");
    
    return respuesta;
  }
  
  public static void guardarRespuesta(String claveDeAcceso, String archivo, String estado, java.util.Date fecha)
  {
    try
    {
      java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
      
      Respuesta item = new Respuesta(null, claveDeAcceso, archivo, estado, sqlDate);

    }
    catch (Exception ex)
    {
      Logger.getLogger(EnvioComprobantesWs.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static String obtenerMensajeRespuesta(RespuestaSolicitud respuesta)
  {
    StringBuilder mensajeDesplegable = new StringBuilder();
    if (respuesta.getEstado().equals("DEVUELTA") == true)
    {
      RespuestaSolicitud.Comprobantes comprobantes = respuesta.getComprobantes();
      for (Comprobante comp : comprobantes.getComprobante())
      {
        mensajeDesplegable.append(comp.getClaveAcceso());
        mensajeDesplegable.append("\n");
        for (Mensaje m : comp.getMensajes().getMensaje())
        {
          mensajeDesplegable.append(m.getMensaje()).append(" :\n");
          mensajeDesplegable.append(m.getInformacionAdicional() != null ? m.getInformacionAdicional() : "");
          mensajeDesplegable.append("\n");
        }
        mensajeDesplegable.append("\n");
      }
    }
    return mensajeDesplegable.toString();
  }
}
