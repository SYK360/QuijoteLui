/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.printer.utilidades;

/**
 *
 * @author jorjoluiso
 */
public enum TipoImpuestoEnum
{
  RENTA(1, "Impuesto a la Renta"), IVA(2, "I.V.A."), ICE(3, "I.C.E.");

  private int code;
  private String descripcion;

  private TipoImpuestoEnum(int code, String descripcion) { this.code = code;
    this.descripcion = descripcion; }

  public String getDescripcion()
  {
    return this.descripcion;
  }

  public int getCode() {
    return this.code;
  }
}