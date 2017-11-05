/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri.util.xml;

/**
 *
 * @author jorgequiguango
 */

import com.quijoteluiclisri.exception.ConvertidorXMLException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LectorXMLPath
{
  private byte[] archivoXML;
  private QName returnType;
  
  public LectorXMLPath(byte[] archivoXML, QName returnType)
  {
    this.archivoXML = archivoXML;
    this.returnType = returnType;
  }
  
  private Document parseDocumento(byte[] archivoXML)
    throws ConvertidorXMLException
  {
    try
    {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      InputStream inputStream = new ByteArrayInputStream(archivoXML);
      return db.parse(new InputSource(inputStream));
    }
    catch (SAXException ex)
    {
      Logger.getLogger(LectorXPath.class.getName()).log(Level.SEVERE, null, ex);
      throw new ConvertidorXMLException("Se produjo un error al convetir el archivo al formato XML");
    }
    catch (IOException ex)
    {
      Logger.getLogger(LectorXPath.class.getName()).log(Level.SEVERE, null, ex);
      throw new ConvertidorXMLException("Se produjo un error al convetir el archivo al formato XML");
    }
    catch (ParserConfigurationException ex)
    {
      Logger.getLogger(LectorXPath.class.getName()).log(Level.SEVERE, null, ex);
      throw new ConvertidorXMLException("Se produjo un error al convetir el archivo al formato XML");
    }
  }
  
  public Object obtenerValorXML(String expression)
    throws ConvertidorXMLException
  {
    try
    {
      Document xmlDocument = parseDocumento(this.archivoXML);
      XPath xPath = XPathFactory.newInstance().newXPath();
      XPathExpression xPathExpression = xPath.compile(expression);
      return xPathExpression.evaluate(xmlDocument, this.returnType);
    }
    catch (XPathExpressionException ex)
    {
      Logger.getLogger(LectorXMLPath.class.getName()).log(Level.SEVERE, null, ex);
      throw new ConvertidorXMLException("Se produjo un error al convetir el archivo al formato XML");
    }
  }
  
  public String getClaveAcceso()
    throws ConvertidorXMLException
  {
    return (String)obtenerValorXML("/*/infoTributaria/claveAcceso");
  }
  
  public String getCodDoc()
    throws ConvertidorXMLException
  {
    return (String)obtenerValorXML("/*/infoTributaria/codDoc");
  }
}
