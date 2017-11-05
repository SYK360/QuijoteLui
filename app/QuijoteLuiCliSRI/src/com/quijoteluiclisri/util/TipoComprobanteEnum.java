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


public enum TipoComprobanteEnum
{
  LOTE("00", "lote.xsd", "LOTE MASIVO"),  FACTURA("01", "factura.xsd", "FACTURA"),  NOTA_DE_CREDITO("04", "notaCredito.xsd", "NOTA DE CREDITO"),  NOTA_DE_DEBITO("05", "notaDebito.xsd", "NOTA DE DEBITO"),  GUIA_DE_REMISION("06", "guiaRemision.xsd", "GUIA DE REMISION"),  COMPROBANTE_DE_RETENCION("07", "comprobanteRetencion.xsd", "COMPROBANTE DE RETENCION"),  LIQUIDACION_DE_COMPRAS("03", "", "LIQ.DE COMPRAS");
  
  private String code;
  private String xsd;
  private String descripcion;
  
  private TipoComprobanteEnum(String code, String xsd, String descripcion)
  {
    this.code = code;
    this.xsd = xsd;
    this.descripcion = descripcion;
  }
  
  public String getCode()
  {
    return this.code;
  }
  
  public String getXsd()
  {
    return this.xsd;
  }
  
  public String getDescripcion()
  {
    return this.descripcion;
  }
  
  public static String retornaCodigo(String valor)
  {
    String codigo = null;
    if (valor.equals(FACTURA.getDescripcion())) {
      codigo = FACTURA.getCode();
    } else if (valor.equals(NOTA_DE_DEBITO.getDescripcion())) {
      codigo = NOTA_DE_DEBITO.getCode();
    } else if (valor.equals(NOTA_DE_CREDITO.getDescripcion())) {
      codigo = NOTA_DE_CREDITO.getCode();
    } else if (valor.equals(COMPROBANTE_DE_RETENCION.getDescripcion())) {
      codigo = COMPROBANTE_DE_RETENCION.getCode();
    } else if (valor.equals(GUIA_DE_REMISION.getDescripcion())) {
      codigo = GUIA_DE_REMISION.getCode();
    } else if (valor.equals(LIQUIDACION_DE_COMPRAS.getDescripcion())) {
      codigo = LIQUIDACION_DE_COMPRAS.getCode();
    }
    return codigo;
  }
}
