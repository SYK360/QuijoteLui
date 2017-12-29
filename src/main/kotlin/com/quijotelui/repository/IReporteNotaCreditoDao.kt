package com.quijotelui.repository

import com.quijotelui.model.ReporteNotaCredito
import java.util.Date

interface IReporteNotaCreditoDao {

    fun findByFechas(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteNotaCredito>
    fun findByFechasAutorizado(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteNotaCredito>
    fun findByFechasNoAutorizado(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteNotaCredito>

}