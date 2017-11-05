/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri.dto;

/**
 *
 * @author jorgequiguango
 */



import com.quijoteluiclisri.api.EstadoAutorizacion;
import ec.gob.sri.comprobantes.ws.aut.Autorizacion;

public class AutorizacionDTO
{
  private Autorizacion autorizacion;
  private EstadoAutorizacion estadoAutorizacion;
  String mensaje;
  
  public AutorizacionDTO(Autorizacion autorizacion, EstadoAutorizacion estadoAutorizacion)
  {
    this.autorizacion = autorizacion;
    this.estadoAutorizacion = estadoAutorizacion;
  }
  
  public AutorizacionDTO(Autorizacion autorizacion, EstadoAutorizacion estadoAutorizacion, String mensaje)
  {
    this.autorizacion = autorizacion;
    this.estadoAutorizacion = estadoAutorizacion;
    this.mensaje = mensaje;
  }
  
  public Autorizacion getAutorizacion()
  {
    return this.autorizacion;
  }
  
  public void setAutorizacion(Autorizacion autorizacion)
  {
    this.autorizacion = autorizacion;
  }
  
  public EstadoAutorizacion getEstadoAutorizacion()
  {
    return this.estadoAutorizacion;
  }
  
  public void setEstadoAutorizacion(EstadoAutorizacion estadoAutorizacion)
  {
    this.estadoAutorizacion = estadoAutorizacion;
  }
  
  public String getMensaje()
  {
    return this.mensaje;
  }
  
  public void setMensaje(String mensaje)
  {
    this.mensaje = mensaje;
  }
}
