package com.quijotelui.repository

import com.quijotelui.model.ReporteFactura
import java.util.Date

interface IReporteFacturaDao {

    fun findByFechas(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteFactura>
    fun findByFechasAutorizado(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteFactura>
    fun findByFechasNoAutorizado(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteFactura>

}