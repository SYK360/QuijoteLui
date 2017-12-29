package com.quijotelui.service

import com.quijotelui.model.ReporteGuia
import java.util.Date

interface IReporteGuiaService {

    fun findByFechas(fechaInicio : String, fechaFin : String) : MutableList<ReporteGuia>
    fun findByFechasEstado(fechaInicio : String, fechaFin : String, estado: String) : MutableList<ReporteGuia>

}