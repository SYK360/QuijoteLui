package com.quijotelui.ws.definicion;

public enum Estado {
    AUTORIZADO("AUTORIZADO"), NO_AUTORIZADO("NO AUTORIZADO"), PROCESADO("EN PROCESO"), NO_PROCESADO("NO PROCESADO");

    private String descripcion;

    private Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static Estado getEstadoAutorizacion(String descripcion) {
        Estado[] listaEstadoAutorizaciones = values();
        for (Estado estadoAutorizacion : listaEstadoAutorizaciones) {
            if (descripcion.equals(estadoAutorizacion.getDescripcion())) {
                return estadoAutorizacion;
            }
        }
        return null;
    }
}
