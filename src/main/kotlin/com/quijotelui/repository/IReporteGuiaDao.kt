package com.quijotelui.repository

import com.quijotelui.model.ReporteGuia
import java.util.Date

interface IReporteGuiaDao {

    fun findByFechas(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteGuia>
    fun findByFechasAutorizado(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteGuia>
    fun findByFechasNoAutorizado(fechaInicio : Date, fechaFin : Date) : MutableList<ReporteGuia>

}