/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijotelui.printer.adicional;

/**
 *
 * @author jorjoluiso
 */

public class InformacionAdicional
{
  private String valor;
  private String nombre;

  public InformacionAdicional(String valor, String nombre)
  {
    this.valor = valor;
    this.nombre = nombre;
  }

  public InformacionAdicional()
  {
  }

  public String getValor()
  {
    return this.valor;
  }

  public void setValor(String valor)
  {
    this.valor = valor;
  }

  public String getNombre()
  {
    return this.nombre;
  }

  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }
}