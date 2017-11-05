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

import java.io.File;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

public class ValidadorEstructuraDocumento
{
  private File archivoXSD;
  private File archivoXML;
  
  public String validacion()
  {
    validarArchivo(this.archivoXSD, "archivoXSD");
    validarArchivo(this.archivoXML, "archivoXML");
    
    String mensaje = null;
    
    SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
    Schema schema;
    try
    {
      schema = schemaFactory.newSchema(this.archivoXSD);
    }
    catch (SAXException e)
    {
      throw new IllegalStateException("Existe un error en la sintaxis del esquema", e);
    }
    Validator validator = schema.newValidator();
    try
    {
      validator.validate(new StreamSource(this.archivoXML));
    }
    catch (Exception e)
    {
      return e.getMessage();
    }
    return mensaje;
  }
  
  protected void validarArchivo(File archivo, String nombre)
    throws IllegalStateException
  {
    if ((null == archivo) || (archivo.length() <= 0L)) {
      throw new IllegalStateException(nombre + " es nulo o esta vacio");
    }
  }
  
  public File getArchivoXSD()
  {
    return this.archivoXSD;
  }
  
  public void setArchivoXSD(File archivoXSD)
  {
    this.archivoXSD = archivoXSD;
  }
  
  public File getArchivoXML()
  {
    return this.archivoXML;
  }
  
  public void setArchivoXML(File archivoXML)
  {
    this.archivoXML = archivoXML;
  }
}
