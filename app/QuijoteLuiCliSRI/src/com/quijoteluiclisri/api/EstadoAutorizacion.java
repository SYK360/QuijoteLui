/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri.api;

/**
 *
 * @author jorgequiguango
 */

public enum EstadoAutorizacion
{
  AUT("AUTORIZADO"),  NAU("NO AUTORIZADO"),  PRO("EN PROCESO"),  NPR("NO PROCESADO");
  
  private String descripcion;
  
  private EstadoAutorizacion(String descripcion)
  {
    this.descripcion = descripcion;
  }
  
  public String getDescripcion()
  {
    return this.descripcion;
  }
  
  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }
  
  public static EstadoAutorizacion getEstadoAutorizacion(String descripcion)
  {
    EstadoAutorizacion[] listaEstadoAutorizaciones = values();
    for (EstadoAutorizacion estadoAutorizacion : listaEstadoAutorizaciones) {
      if (descripcion.equals(estadoAutorizacion.getDescripcion())) {
        return estadoAutorizacion;
      }
    }
    return null;
  }
}
