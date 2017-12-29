package com.quijotelui.service

import com.quijotelui.model.ReporteNotaCredito
import java.util.Date

interface IReporteNotaCreditoService {

    fun findByFechas(fechaInicio : String, fechaFin : String) : MutableList<ReporteNotaCredito>
    fun findByFechasEstado(fechaInicio : String, fechaFin : String, estado: String) : MutableList<ReporteNotaCredito>

}