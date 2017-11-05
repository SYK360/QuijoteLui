/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri.modelo;

/**
 *
 * @author jorgequiguango
 */

public class ComprobanteXml
{
  private String tipo;
  private String version;
  private String fileXML;
  
  public String getFileXML()
  {
    return this.fileXML;
  }
  
  public void setFileXML(String fileXML)
  {
    this.fileXML = ("<![CDATA[" + fileXML + "]]>");
  }
  
  public String getTipo()
  {
    return this.tipo;
  }
  
  public void setTipo(String tipo)
  {
    this.tipo = tipo;
  }
  
  public String getVersion()
  {
    return this.version;
  }
  
  public void setVersion(String version)
  {
    this.version = version;
  }
}
