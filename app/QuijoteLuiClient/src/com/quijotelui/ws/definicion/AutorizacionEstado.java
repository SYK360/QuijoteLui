package com.quijotelui.ws.definicion;

import ec.gob.sri.comprobantes.ws.aut.Autorizacion;

public class AutorizacionEstado {

    private Autorizacion autorizacion;
    private Estado estadoAutorizacion;
    String mensaje;

    public AutorizacionEstado(Autorizacion autorizacion, Estado estadoAutorizacion) {
        this.autorizacion = autorizacion;
        this.estadoAutorizacion = estadoAutorizacion;
    }

    public AutorizacionEstado(Autorizacion autorizacion, Estado estadoAutorizacion, String mensaje) {
        this.autorizacion = autorizacion;
        this.estadoAutorizacion = estadoAutorizacion;
        this.mensaje = mensaje;
    }

    public Autorizacion getAutorizacion() {
        return this.autorizacion;
    }

    public void setAutorizacion(Autorizacion autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Estado getEstadoAutorizacion() {
        return this.estadoAutorizacion;
    }

    public void setEstadoAutorizacion(Estado estadoAutorizacion) {
        this.estadoAutorizacion = estadoAutorizacion;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
