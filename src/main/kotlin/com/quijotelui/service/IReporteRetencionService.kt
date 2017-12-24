package com.quijotelui.service

import com.quijotelui.model.ReporteRetencion

interface IReporteRetencionService {

    fun findByFechas(fechaInicio : String, fechaFin : String) : MutableList<ReporteRetencion>
    fun findByFechasEstado(fechaInicio : String, fechaFin : String, estado : String) : MutableList<ReporteRetencion>

}