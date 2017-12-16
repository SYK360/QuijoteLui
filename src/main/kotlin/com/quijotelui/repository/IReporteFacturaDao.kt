package com.quijotelui.repository

import com.quijotelui.model.ReporteFactura
import java.util.*

interface IReporteFacturaDao {

    fun findAll() : MutableList<ReporteFactura>
    fun findByFechas(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteFactura>

}