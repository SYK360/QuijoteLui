package com.quijotelui.ws.dto;

import com.quijotelui.ws.define.Estado;
import ec.gob.sri.comprobantes.ws.aut.Autorizacion;

public class AutorizacionDTO
{
  private Autorizacion autorizacion;
  private Estado estadoAutorizacion;
  String mensaje;
  
  public AutorizacionDTO(Autorizacion autorizacion, Estado estadoAutorizacion)
  {
    this.autorizacion = autorizacion;
    this.estadoAutorizacion = estadoAutorizacion;
  }
  
  public AutorizacionDTO(Autorizacion autorizacion, Estado estadoAutorizacion, String mensaje)
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
  
  public Estado getEstadoAutorizacion()
  {
    return this.estadoAutorizacion;
  }
  
  public void setEstadoAutorizacion(Estado estadoAutorizacion)
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
