package com.quijotelui.repository

import com.quijotelui.model.ReporteRetencion
import java.util.Date

interface IReporteRetencionDao {

    fun findByFechas(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteRetencion>
    fun findByFechasAutorizado(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteRetencion>
    fun findByFechasNoAutorizado(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteRetencion>

}