/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.printer.retencion;

/**
 *
 * @author jorjoluiso
 */

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="impuesto", propOrder={"codigo", "codigoRetencion", "baseImponible", "porcentajeRetener", "valorRetenido", "codDocSustento", "numDocSustento", "fechaEmisionDocSustento"})
public class Impuesto
{

  @XmlElement(required=true)
  protected String codigo;

  @XmlElement(required=true)
  protected String codigoRetencion;

  @XmlElement(required=true)
  protected BigDecimal baseImponible;

  @XmlElement(required=true)
  protected BigDecimal porcentajeRetener;

  @XmlElement(required=true)
  protected BigDecimal valorRetenido;

  @XmlElement(required=true)
  protected String codDocSustento;
  protected String numDocSustento;
  protected String fechaEmisionDocSustento;

  public String getCodigo()
  {
    return this.codigo;
  }

  public void setCodigo(String value)
  {
    this.codigo = value;
  }

  public String getCodigoRetencion()
  {
    return this.codigoRetencion;
  }

  public void setCodigoRetencion(String value)
  {
    this.codigoRetencion = value;
  }

  public BigDecimal getBaseImponible()
  {
    return this.baseImponible;
  }

  public void setBaseImponible(BigDecimal value)
  {
    this.baseImponible = value;
  }

  public BigDecimal getPorcentajeRetener()
  {
    return this.porcentajeRetener;
  }

  public void setPorcentajeRetener(BigDecimal value)
  {
    this.porcentajeRetener = value;
  }

  public BigDecimal getValorRetenido()
  {
    return this.valorRetenido;
  }

  public void setValorRetenido(BigDecimal value)
  {
    this.valorRetenido = value;
  }

  public String getCodDocSustento()
  {
    return this.codDocSustento;
  }

  public void setCodDocSustento(String value)
  {
    this.codDocSustento = value;
  }

  public String getNumDocSustento()
  {
    return this.numDocSustento;
  }

  public void setNumDocSustento(String value)
  {
    this.numDocSustento = value;
  }

  public String getFechaEmisionDocSustento()
  {
    return this.fechaEmisionDocSustento;
  }

  public void setFechaEmisionDocSustento(String value)
  {
    this.fechaEmisionDocSustento = value;
  }
}
