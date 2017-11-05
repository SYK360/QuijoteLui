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
public enum DirectorioEnum
{
  GENERADOS(1),  FIRMADOS(2),  AUTORIZADOS(3),  NO_AUTORIZADOS(4),  ENVIADOS(5);
  
  private int code;
  
  private DirectorioEnum(int code)
  {
    this.code = code;
  }
  
  public int getCode()
  {
    return this.code;
  }
  
  public void setCode(int code)
  {
    this.code = code;
  }
  

}