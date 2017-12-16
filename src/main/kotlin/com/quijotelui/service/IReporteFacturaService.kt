package com.quijotelui.service

import com.quijotelui.model.ReporteFactura

interface IReporteFacturaService {

    fun findByFechas(fechaInicio : String, fechaFin : String) : MutableList<ReporteFactura>
    fun findByFechasEstado(fechaInicio : String, fechaFin : String, estado : String) : MutableList<ReporteFactura>

}