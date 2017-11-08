/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.printer;

/**
 *
 * @author jorjoluiso
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="infoTributaria", propOrder={"ambiente", "tipoEmision", "razonSocial", "nombreComercial", "ruc", "claveAcceso", "codDoc", "estab", "ptoEmi", "secuencial", "dirMatriz"})
public class InfoTributaria
{

  @XmlElement(required=true)
  public String ambiente;

  @XmlElement(required=true)
  public String tipoEmision;

  @XmlElement(required=true)
  protected String razonSocial;
  protected String nombreComercial;

  @XmlElement(required=true)
  protected String ruc;

  @XmlElement(required=true)
  public String claveAcceso;

  @XmlElement(required=true)
  protected String codDoc;

  @XmlElement(required=true)
  protected String estab;

  @XmlElement(required=true)
  protected String ptoEmi;

  @XmlElement(required=true)
  protected String secuencial;

  @XmlElement(required=true)
  protected String dirMatriz;

    public InfoTributaria() {
    }

    public InfoTributaria(String ambiente, String tipoEmision, String razonSocial, String nombreComercial, String ruc, String claveAcceso, String codDoc, String estab, String ptoEmi, String secuencial, String dirMatriz) {
        this.ambiente = ambiente;
        this.tipoEmision = tipoEmision;
        this.razonSocial = razonSocial;
        this.nombreComercial = nombreComercial;
        this.ruc = ruc;
        this.claveAcceso = claveAcceso;
        this.codDoc = codDoc;
        this.estab = estab;
        this.ptoEmi = ptoEmi;
        this.secuencial = secuencial;
        this.dirMatriz = dirMatriz;
    }
  
  

  public String getAmbiente()
  {
    return this.ambiente;
  }

  public void setAmbiente(String value)
  {
    this.ambiente = value;
  }

  public String getTipoEmision()
  {
    return this.tipoEmision;
  }

  public void setTipoEmision(String value)
  {
    this.tipoEmision = value;
  }

  public String getRazonSocial()
  {
    return this.razonSocial;
  }

  public void setRazonSocial(String value)
  {
    this.razonSocial = value;
  }

  public String getNombreComercial()
  {
    return this.nombreComercial;
  }

  public void setNombreComercial(String value)
  {
    this.nombreComercial = value;
  }

  public String getRuc()
  {
    return this.ruc;
  }

  public void setRuc(String value)
  {
    this.ruc = value;
  }

  public String getClaveAcceso()
  {
    return this.claveAcceso;
  }

  public void setClaveAcceso(String value)
  {
    this.claveAcceso = value;
  }

  public String getCodDoc()
  {
    return this.codDoc;
  }

  public void setCodDoc(String value)
  {
    this.codDoc = value;
  }

  public String getEstab()
  {
    return this.estab;
  }

  public void setEstab(String value)
  {
    this.estab = value;
  }

  public String getPtoEmi()
  {
    return this.ptoEmi;
  }

  public void setPtoEmi(String value)
  {
    this.ptoEmi = value;
  }

  public String getSecuencial()
  {
    return this.secuencial;
  }

  public void setSecuencial(String value)
  {
    this.secuencial = value;
  }

  public String getDirMatriz()
  {
    return this.dirMatriz;
  }

  public void setDirMatriz(String value)
  {
    this.dirMatriz = value;
  }
}