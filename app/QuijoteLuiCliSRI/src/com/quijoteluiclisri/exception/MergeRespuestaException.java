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
public class MergeRespuestaException
  extends Exception
{
  private static final long serialVersionUID = 6933150456422042905L;
  
  public MergeRespuestaException(String message, Throwable cause)
  {
    super(message, cause);
  }
  
  public MergeRespuestaException(String message)
  {
    super(message);
  }
  
  public MergeRespuestaException(Throwable cause)
  {
    super(cause);
  }
}
