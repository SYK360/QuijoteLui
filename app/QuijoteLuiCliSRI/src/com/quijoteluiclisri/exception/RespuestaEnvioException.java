/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri.exception;

/**
 *
 * @author jorgequiguango
 */
public class RespuestaEnvioException
  extends Exception
{
  private static final long serialVersionUID = 6933150456422042905L;
  
  public RespuestaEnvioException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public RespuestaEnvioException(String message)
  {
    super(message);
  }
  
  public RespuestaEnvioException(Throwable cause)
  {
    super(cause);
  }
}
