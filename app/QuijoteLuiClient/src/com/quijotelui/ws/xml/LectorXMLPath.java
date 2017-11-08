package com.quijotelui.ws.xml;

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
  {
    try
    {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setNamespaceAware(true);
      DocumentBuilder db = dbf.newDocumentBuilder();
      InputStream inputStream = new ByteArrayInputStream(archivoXML);
      return db.parse(new InputSource(inputStream));
    }
    catch (SAXException | IOException | ParserConfigurationException ex)
    {
      Logger.getLogger(LectorXPath.class.getName()).log(Level.SEVERE, null, ex);
      System.out.println("Se produjo un error al convetir el archivo al formato XML");
    }
      return null;
  }
  
  public Object obtenerValorXML(String expression)
    
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
      System.out.println("Se produjo un error al convetir el archivo al formato XML");
    }
      return null;
  }
  
  public String getClaveAcceso()
    
  {
    return (String)obtenerValorXML("/*/infoTributaria/claveAcceso");
  }
  
  public String getCodDoc()
  {
    return (String)obtenerValorXML("/*/infoTributaria/codDoc");
  }
}
