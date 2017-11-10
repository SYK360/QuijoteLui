/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.printer.adicional;

import java.util.List;

/**
 *
 * @author jorjoluiso
 */
public class DetallesAdicionalesReporte
{
  private String codigoPrincipal;
  private String codigoAuxiliar;
  private String cantidad;
  private String descripcion;
  private String precioUnitario;
  private String precioTotalSinImpuesto;
  private String descuento;
  private String numeroComprobante;
  private String nombreComprobante;
  private String detalle1;
  private String detalle2;
  private String detalle3;
  private String fechaEmisionCcompModificado;
  private List<InformacionAdicional> infoAdicional;
  private String razonModificacion;
  private String valorModificacion;
  private String baseImponible;
  private String nombreImpuesto;
  private String porcentajeRetener;
  private String valorRetenido;

  public String getCodigoPrincipal()
  {
    return this.codigoPrincipal;
  }

  public void setCodigoPrincipal(String codigoPrincipal)
  {
    this.codigoPrincipal = codigoPrincipal;
  }

  public String getCodigoAuxiliar()
  {
    return this.codigoAuxiliar;
  }

  public void setCodigoAuxiliar(String codigoAuxiliar)
  {
    this.codigoAuxiliar = codigoAuxiliar;
  }

  public String getCantidad()
  {
    return this.cantidad;
  }

  public void setCantidad(String cantidad)
  {
    this.cantidad = cantidad;
  }

  public String getDescripcion()
  {
    return this.descripcion;
  }

  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }

  public String getPrecioUnitario()
  {
    return this.precioUnitario;
  }

  public void setPrecioUnitario(String precioUnitario)
  {
    this.precioUnitario = precioUnitario;
  }

  public String getPrecioTotalSinImpuesto()
  {
    return this.precioTotalSinImpuesto;
  }

  public void setPrecioTotalSinImpuesto(String precioTotalSinImpuesto)
  {
    this.precioTotalSinImpuesto = precioTotalSinImpuesto;
  }

  public String getDetalle1()
  {
    return this.detalle1;
  }

  public void setDetalle1(String detalle1)
  {
    this.detalle1 = detalle1;
  }

  public String getDetalle2()
  {
    return this.detalle2;
  }

  public void setDetalle2(String detalle2)
  {
    this.detalle2 = detalle2;
  }

  public String getDetalle3()
  {
    return this.detalle3;
  }

  public void setDetalle3(String detalle3)
  {
    this.detalle3 = detalle3;
  }

  public List<InformacionAdicional> getInfoAdicional()
  {
    return this.infoAdicional;
  }

  public void setInfoAdicional(List<InformacionAdicional> infoAdicional)
  {
    this.infoAdicional = infoAdicional;
  }

  public String getRazonModificacion()
  {
    return this.razonModificacion;
  }

  public void setRazonModificacion(String razonModificacion)
  {
    this.razonModificacion = razonModificacion;
  }

  public String getValorModificacion()
  {
    return this.valorModificacion;
  }

  public void setValorModificacion(String valorModificacion)
  {
    this.valorModificacion = valorModificacion;
  }

  public String getBaseImponible()
  {
    return this.baseImponible;
  }

  public void setBaseImponible(String baseImponible)
  {
    this.baseImponible = baseImponible;
  }

  public String getNombreImpuesto()
  {
    return this.nombreImpuesto;
  }

  public void setNombreImpuesto(String nombreImpuesto)
  {
    this.nombreImpuesto = nombreImpuesto;
  }

  public String getPorcentajeRetener()
  {
    return this.porcentajeRetener;
  }

  public void setPorcentajeRetener(String porcentajeRetener)
  {
    this.porcentajeRetener = porcentajeRetener;
  }

  public String getValorRetenido()
  {
    return this.valorRetenido;
  }

  public void setValorRetenido(String valorRetenido)
  {
    this.valorRetenido = valorRetenido;
  }

  public String getDescuento()
  {
    return this.descuento;
  }

  public void setDescuento(String descuento)
  {
    this.descuento = descuento;
  }

  public String getNumeroComprobante()
  {
    return this.numeroComprobante;
  }

  public void setNumeroComprobante(String numeroComprobante)
  {
    this.numeroComprobante = numeroComprobante;
  }

  public String getNombreComprobante()
  {
    return this.nombreComprobante;
  }

  public void setNombreComprobante(String nombreComprobante)
  {
    this.nombreComprobante = nombreComprobante;
  }

  public String getFechaEmisionCcompModificado()
  {
    return this.fechaEmisionCcompModificado;
  }

  public void setFechaEmisionCcompModificado(String fechaEmisionCcompModificado)
  {
    this.fechaEmisionCcompModificado = fechaEmisionCcompModificado;
  }
}