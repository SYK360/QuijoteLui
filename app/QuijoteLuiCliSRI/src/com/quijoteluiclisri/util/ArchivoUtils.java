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


import com.quijoteluiclisri.exception.ConvertidorXMLException;
import com.quijoteluiclisri.exception.DirectorioException;
import com.quijoteluiclisri.exception.MergeRespuestaException;
import com.quijoteluiclisri.exception.RespuestaEnvioException;

import com.quijoteluiclisri.util.xml.Java2XML;
import com.quijoteluiclisri.util.xml.LectorXPath;
import com.quijoteluiclisri.util.xml.ValidadorEstructuraDocumento;
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud;
import ec.gob.sri.comprobantes.ws.aut.Autorizacion;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.logging.Level;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ArchivoUtils
{
  private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ArchivoUtils.class);
  
  public static String archivoToString(String rutaArchivo)
  {
    StringBuffer buffer = new StringBuffer();
    try
    {
      FileInputStream fis = new FileInputStream(rutaArchivo);
      InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
      Reader in = new BufferedReader(isr);
      int ch;
      while ((ch = in.read()) > -1) {
        buffer.append((char)ch);
      }
      in.close();
      return buffer.toString();
    }
    catch (IOException ex)
    {
      LOG.error(ex);
    }
    return null;
  }
  
  public static File stringToArchivo(String rutaArchivo, String contenidoArchivo)
  {
    FileOutputStream fos = null;
    try
    {
      fos = new FileOutputStream(rutaArchivo);
      OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
      for (int i = 0; i < contenidoArchivo.length(); i++) {
        out.write(contenidoArchivo.charAt(i));
      }
      out.close();
      
      return new File(rutaArchivo);
    }
    catch (Exception ex)
    {
      LOG.error(ex);
      return null;
    }
    finally
    {
      try
      {
        if (fos != null) {
          fos.close();
        }
      }
      catch (Exception ex)
      {
        LOG.error(ex);
      }
    }
  }
  
  public static boolean byteToFile(byte[] arrayBytes, String rutaArchivo)
  {
    boolean respuesta = false;
    try
    {
      File file = new File(rutaArchivo);
      file.createNewFile();
      FileInputStream fileInputStream = new FileInputStream(rutaArchivo);
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(arrayBytes);
      OutputStream outputStream = new FileOutputStream(rutaArchivo);
      int data;
      while ((data = byteArrayInputStream.read()) != -1) {
        outputStream.write(data);
      }
      fileInputStream.close();
      outputStream.close();
      respuesta = true;
    }
    catch (IOException ex)
    {
      LOG.error(ex);
    }
    return respuesta;
  }
  
  public static String obtenerValorXML(File xmlDocument, String expression)
  {
    String valor = null;
    try
    {
      LectorXPath reader = new LectorXPath(xmlDocument.getPath());
      valor = (String)reader.leerArchivo(expression, XPathConstants.STRING);
    }
    catch (Exception ex)
    {
      LOG.error(ex);
    }
    return valor;
  }
  
//  public static String seleccionaXsd(String tipo)
//  {
//    String nombreXsd = null;
//    if (tipo.equals(TipoComprobanteEnum.FACTURA.getCode())) {
//      nombreXsd = TipoComprobanteEnum.FACTURA.getXsd();
//    } else if (tipo.equals(TipoComprobanteEnum.COMPROBANTE_DE_RETENCION.getCode())) {
//      nombreXsd = TipoComprobanteEnum.COMPROBANTE_DE_RETENCION.getXsd();
//    } else if (tipo.equals(TipoComprobanteEnum.GUIA_DE_REMISION.getCode())) {
//      nombreXsd = TipoComprobanteEnum.GUIA_DE_REMISION.getXsd();
//    } else if (tipo.equals(TipoComprobanteEnum.NOTA_DE_CREDITO.getCode())) {
//      nombreXsd = TipoComprobanteEnum.NOTA_DE_CREDITO.getXsd();
//    } else if (tipo.equals(TipoComprobanteEnum.NOTA_DE_DEBITO.getCode())) {
//      nombreXsd = TipoComprobanteEnum.NOTA_DE_DEBITO.getXsd();
//    } else if (tipo.equals(TipoComprobanteEnum.LOTE.getCode())) {
//      nombreXsd = TipoComprobanteEnum.LOTE.getXsd();
//    }
//    return nombreXsd;
//  }
  
  public static void firmarEnviarAutorizar( String pathCompletoArchivoAFirmar, String nombreArchivo, String ruc, String codDoc, String claveDeAcceso, String password)
    throws InterruptedException
  {
    RespuestaSolicitud respuestaRecepcion = new RespuestaSolicitud();
    String respuestaFirma = null;
    String respAutorizacion = null;
    try
    {
      String dirFirmados = "/firmados";
      String dirEnviados = "/enviados";
      
//      respuestaFirma = firmarArchivo(emisor, pathCompletoArchivoAFirmar, dirFirmados, null, password);
      if (respuestaFirma == null)
      {
        new File(pathCompletoArchivoAFirmar).delete();
        
        File archivoFirmado = new File(dirFirmados + File.separator + nombreArchivo);
        
        respuestaRecepcion = EnvioComprobantesWs.obtenerRespuestaEnvio1(archivoFirmado, ruc, codDoc, claveDeAcceso, FormGenerales.devuelveUrlWs(/*emisor.getTipoAmbiente()*/"1", "RecepcionComprobantesOffline"));
        if (respuestaRecepcion.getEstado().equals("RECIBIDA"))
        {
          JOptionPane.showMessageDialog(new JFrame(), "El comprobante fue enviado, est�� pendiente de autorizaci��n", "Respuesta", 1);
          File enviados = new File(dirEnviados);
          if (!enviados.exists()) {
            new File(dirEnviados).mkdir();
          }
          if (!copiarArchivo(archivoFirmado, enviados.getPath() + File.separator + nombreArchivo))
          {
            JOptionPane.showMessageDialog(new JFrame(), "Error al mover archivo a carpeta enviados", "Respuesta", 1);
          }
          else
          {
//            VisualizacionRideUtil.decodeArchivoSinAutorizacion(dirEnviados + File.separator + nombreArchivo, claveDeAcceso, null, codDoc);
            archivoFirmado.delete();
          }
        }
        else if (respuestaRecepcion.getEstado().equals("DEVUELTA"))
        {
          String dirRechazados = dirFirmados + File.separator + "rechazados";
          String resultado = FormGenerales.insertarCaracteres(EnvioComprobantesWs.obtenerMensajeRespuesta(respuestaRecepcion), "\n", 160);
          
          File rechazados = new File(dirRechazados);
          if (!rechazados.exists()) {
            new File(dirRechazados).mkdir();
          }
          if (!copiarArchivo(archivoFirmado, rechazados.getPath() + File.separator + nombreArchivo)) {
            JOptionPane.showMessageDialog(new JFrame(), "Error al mover archivo a carpeta rechazados", "Respuesta", 1);
          } else {
            archivoFirmado.delete();
          }
          JOptionPane.showMessageDialog(new JFrame(), "Error al tratar de enviar el comprobante hacia el SRI:\n" + resultado, "Se ha producido un error ", 0);
        }
      }
      else
      {
        JOptionPane.showMessageDialog(new JFrame(), "Error al tratar de firmar digitalmente el archivo:\n" + respuestaFirma, "Se ha producido un error ", 0);
      }
    }
    catch (Exception ex)
    {
      java.util.logging.Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static RespuestaSolicitud enviarComprobante( byte[] archivoFirmado, String dirFirmados, String ruc, String codDoc, String claveDeAcceso)
    throws InterruptedException
  {
    RespuestaSolicitud respuestaSolicitudEnvio = new RespuestaSolicitud();
    return respuestaSolicitudEnvio;
  }
  
  public static RespuestaSolicitud enviar( File archivoFirmado, String ruc, String codDoc, String claveDeAcceso)
  {
    return EnvioComprobantesWs.obtenerRespuestaEnvio(archivoFirmado, ruc, codDoc, claveDeAcceso, FormGenerales.devuelveUrlWs(/*emisor.getTipoAmbiente()*/"1", "RecepcionComprobantesOffline"));
  }
  
  public static void validarRespuestaEnvio(RespuestaSolicitud respuestaSolicitudEnvio, byte[] archivoFirmado, String nombreArchivo)
    throws DirectorioException, MergeRespuestaException, RespuestaEnvioException
  {
    if (respuestaSolicitudEnvio.getEstado().equals("RECIBIDA"))
    {
      crearArchivo(archivoFirmado, nombreArchivo, DirectorioEnum.ENVIADOS);
    }
    else if (respuestaSolicitudEnvio.getEstado().equals("DEVUELTA"))
    {
      byte[] archivoRespuesta = anadirMotivosRechazo(respuestaSolicitudEnvio, archivoFirmado);
      crearArchivoDirectorioRechazados(archivoRespuesta, nombreArchivo);
      throw new RespuestaEnvioException(String.format("Error al enviar el comprobante estado : %s \nRevisar la carpeta de rechazados", new Object[] { respuestaSolicitudEnvio.getEstado() }));
    }
  }
  
  public static String validaArchivoXSD(String tipoComprobante, String pathArchivoXML)
  {
    String respuestaValidacion = null;
    try
    {
      ValidadorEstructuraDocumento validador = new ValidadorEstructuraDocumento();
//      String nombreXsd = seleccionaXsd(tipoComprobante);
      
//      String pathArchivoXSD = "resources/xsd/" + nombreXsd;
      if (pathArchivoXML != null)
      {
        validador.setArchivoXML(new File(pathArchivoXML));
//        validador.setArchivoXSD(new File(pathArchivoXSD));
        
        respuestaValidacion = validador.validacion();
      }
    }
    catch (Exception ex)
    {
      LOG.error(ex);
    }
    return respuestaValidacion;
  }
  

  

  
  public static String obtieneClaveAccesoAutorizacion(Autorizacion item)
  {
    String claveAcceso = null;
    
    File archivoTemporal = new File("temp.xml");
    
    String contenidoXML = decodeArchivoBase64(archivoTemporal.getPath());
    if (contenidoXML != null)
    {
      stringToArchivo(archivoTemporal.getPath(), contenidoXML);
      claveAcceso = obtenerValorXML(archivoTemporal, "/*/infoTributaria/claveAcceso");
    }
    return claveAcceso;
  }
  
  public static String decodeArchivoBase64(String pathArchivo)
  {
    String xmlDecodificado = null;
    try
    {
      File file = new File(pathArchivo);
      if (file.exists())
      {
        String encd = obtenerValorXML(file, "/*/comprobante");
        
        xmlDecodificado = encd;
      }
      else
      {
        System.out.print("File not found!");
      }
    }
    catch (Exception ex)
    {
      LOG.error(ex);
    }
    return xmlDecodificado;
  }
  
  public static boolean anadirMotivosRechazo(File archivo, RespuestaSolicitud respuestaRecepcion)
  {
    boolean exito = false;
    File respuesta = new File("respuesta.xml");
    Java2XML.marshalRespuestaSolicitud(respuestaRecepcion, respuesta.getPath());
    if (adjuntarArchivo(respuesta, archivo) == true)
    {
      exito = true;
      respuesta.delete();
    }
    return exito;
  }
  
  public static byte[] anadirMotivosRechazo(RespuestaSolicitud respuestaRecepcion, byte[] comprobante)
    throws MergeRespuestaException
  {
    try
    {
      byte[] respuetaRecepcionXML = Java2XML.convertirAXml(respuestaRecepcion);
      Document document = mergeArchivos(comprobante, respuetaRecepcionXML);
      return adjuntarArchivo(document);
    }
    catch (ConvertidorXMLException ex)
    {
      java.util.logging.Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
      
      throw new MergeRespuestaException("Se produjo un error al adjuntar los resultados de la respuesta al comprobante enviado");
    }
  }
  
  public static boolean adjuntarArchivo(File respuesta, File comprobante)
  {
    try
    {
      Document document = merge("*", new File[] { comprobante, respuesta });
      
      DOMSource source = new DOMSource(document);
      
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(comprobante), "UTF-8");
      StreamResult result = new StreamResult(outputStreamWriter);
      
      TransformerFactory transFactory = TransformerFactory.newInstance();
      Transformer transformer = transFactory.newTransformer();
      
      transformer.transform(source, result);
      outputStreamWriter.close();
      return true;
    }
    catch (Exception ex)
    {
      LOG.error(ex);
    }
    return false;
  }
  
  public static Document mergeArchivos(byte[] comprobante, byte[] respuesta)
    throws MergeRespuestaException
  {
    return merge("*", new byte[][] { comprobante, respuesta });
  }
  
  public static byte[] adjuntarArchivo(Document document)
    throws MergeRespuestaException
  {
    try
    {
      DOMSource source = new DOMSource(document);
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      StreamResult result = new StreamResult(byteArrayOutputStream);
      TransformerFactory transFactory = TransformerFactory.newInstance();
      Transformer transformer = transFactory.newTransformer();
      transformer.transform(source, result);
      return byteArrayOutputStream.toByteArray();
    }
    catch (TransformerException ex)
    {
      java.util.logging.Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
      
      throw new MergeRespuestaException("Se produjo un error al adjuntar los resultados de la respuesta al comprobante enviado");
    }
  }
  
  private static Document merge(String exp, File... files)
    throws Exception
  {
    XPathFactory xPathFactory = XPathFactory.newInstance();
    XPath xpath = xPathFactory.newXPath();
    XPathExpression expression = xpath.compile(exp);
    
    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
    docBuilderFactory.setIgnoringElementContentWhitespace(true);
    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
    Document base = docBuilder.parse(files[0]);
    
    Node results = (Node)expression.evaluate(base, XPathConstants.NODE);
    if (results == null) {
      throw new IOException(files[0] + ": expression does not evaluate to node");
    }
    for (int i = 1; i < files.length; i++)
    {
      Document merge = docBuilder.parse(files[i]);
      Node nextResults = (Node)expression.evaluate(merge, XPathConstants.NODE);
      results.appendChild(base.importNode(nextResults, true));
    }
    return base;
  }
  
  private static Document merge(String exp, byte[]... archivosXML)
    throws MergeRespuestaException
  {
    try
    {
      XPathFactory xPathFactory = XPathFactory.newInstance();
      XPath xpath = xPathFactory.newXPath();
      XPathExpression expression = xpath.compile(exp);
      DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
      docBuilderFactory.setIgnoringElementContentWhitespace(true);
      DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(archivosXML[0]);
      Document base = docBuilder.parse(byteArrayInputStream);
      Node results = (Node)expression.evaluate(base, XPathConstants.NODE);
      for (int i = 1; i < archivosXML.length; i++)
      {
        ByteArrayInputStream byteArrayInputStreamMerge = new ByteArrayInputStream(archivosXML[i]);
        Document merge = docBuilder.parse(byteArrayInputStreamMerge);
        Node nextResults = (Node)expression.evaluate(merge, XPathConstants.NODE);
        results.appendChild(base.importNode(nextResults, true));
      }
      return base;
    }
    catch (XPathExpressionException ex)
    {
      java.util.logging.Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
      
      throw new MergeRespuestaException("Se produjo un error al adjuntar los resultados de la respuesta al comprobante enviado");
    }
    catch (SAXException ex)
    {
      java.util.logging.Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
      
      throw new MergeRespuestaException("Se produjo un error al adjuntar los resultados de la respuesta al comprobante enviado");
    }
    catch (IOException ex)
    {
      java.util.logging.Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
      
      throw new MergeRespuestaException("Se produjo un error al adjuntar los resultados de la respuesta al comprobante enviado");
    }
    catch (ParserConfigurationException ex)
    {
      java.util.logging.Logger.getLogger(ArchivoUtils.class.getName()).log(Level.SEVERE, null, ex);
      
      throw new MergeRespuestaException("Se produjo un error al adjuntar los resultados de la respuesta al comprobante enviado");
    }
  }
  
  public static boolean copiarArchivo(File archivoOrigen, String pathDestino)
  {
    FileReader in = null;
    try
    {
      File outputFile = new File(pathDestino);
      in = new FileReader(archivoOrigen);
      FileWriter out = new FileWriter(outputFile);
      int c;
      while ((c = in.read()) != -1) {
        out.write(c);
      }
      in.close();
      out.close();
      return true;
    }
    catch (Exception ex)
    {
      LOG.error(ex);
    }
    finally
    {
      try
      {
        in.close();
      }
      catch (IOException ex)
      {
        LOG.error(ex);
      }
    }
      return false;
  }
  
  public static boolean copiarArchivo(File archivoOrigen, DirectorioEnum directorioEnum)
    throws DirectorioException
  {
      File directorioDestino = new File("/tmp");
      return copiarArchivo(archivoOrigen, directorioDestino);
  }
  
  public static boolean copiarArchivo(File archivoOrigen, File directorioDestino)
    throws DirectorioException
  {
    InputStream inStream = null;
    OutputStream outStream = null;
    try
    {
      String rutaArchivoDestino = directorioDestino + File.separator + archivoOrigen.getName();
      File archivoDestino = new File(rutaArchivoDestino);
      inStream = new FileInputStream(archivoOrigen);
      outStream = new FileOutputStream(archivoDestino);
      byte[] buffer = new byte['E'];
      int length;
      while ((length = inStream.read(buffer)) > 0) {
        outStream.write(buffer, 0, length);
      }
      inStream.close();
      outStream.close();
      return true;
    }
    catch (IOException ex)
    {
      LOG.error(ex);
      throw new DirectorioException(String.format("Se produjo un error al consultar el direcotorio : %s", new Object[] { directorioDestino.getName() }));
    }
  }
  
  public static File crearArchivo(byte[] archivo, String nombreArchivo, DirectorioEnum directorioEnum)
    throws DirectorioException
  {
    try
    {

      File directorioDestino = new File("/tmp");
      String rutaArchivoDestino = directorioDestino + File.separator + nombreArchivo;
      File archivoDestino = new File(rutaArchivoDestino);
      FileOutputStream fileOutputStream = new FileOutputStream(archivoDestino);
      fileOutputStream.write(archivo);
      fileOutputStream.close();
      return archivoDestino;
    }
    catch (IOException ex)
    {
      LOG.error(ex);
      throw new DirectorioException(String.format("Error al mover el archivo al directorio: %s", new Object[] { directorioEnum.toString() }));
    }
  }
  
  public static void crearArchivoDirectorioRechazados(byte[] archivo, String nombreArchivo)
    throws DirectorioException
  {
    try
    {

      File directorioDestino = new File("/tmp");
      String rutaDirectorioRechazados = directorioDestino + File.separator + "rechazados";
      File directorioRechazados = new File(rutaDirectorioRechazados);
      if (!directorioRechazados.exists()) {
        directorioRechazados.mkdir();
      }
      String rutaArchivoDestino = rutaDirectorioRechazados + File.separator + nombreArchivo;
      File archivoDestino = new File(rutaArchivoDestino);
      FileOutputStream fileOutputStream = new FileOutputStream(archivoDestino);
      fileOutputStream.write(archivo);
      fileOutputStream.close();
    }
    catch (IOException ex)
    {
      LOG.error(ex);
      throw new DirectorioException(String.format("Error al mover el archivo al directorio: %s", new Object[] { "rechazados" }));
    }
  }
  
  public static byte[] archivoToByte(File file)
    throws IOException
  {
    String archivo = FileUtils.readFileToString(file, "UTF-8");
    return archivo.getBytes(Charset.forName("UTF-8"));
  }
}
