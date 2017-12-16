package com.quijotelui.service

import com.quijotelui.model.ReporteFactura
import com.quijotelui.repository.IReporteFacturaDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class ReporteFacturaServiceImpl : IReporteFacturaService {

    @Autowired
    lateinit var reporteFacturaDao : IReporteFacturaDao

    override fun findByFechas(fechaInicio: String, fechaFin: String): MutableList<ReporteFactura> {
        println("Fecha Inicio en String: $fechaInicio")
        println("Fecha Fin en String: $fechaFin")

        val simpleDateFormatInicio = SimpleDateFormat("yyyy-MM-dd")
        val fechaInDateTypeInicio : Date
        fechaInDateTypeInicio = simpleDateFormatInicio.parse(fechaInicio)

        val simpleDateFormatFin = SimpleDateFormat("yyyy-MM-dd")
        val fechaInDateTypeFin : Date
        fechaInDateTypeFin = simpleDateFormatFin.parse(fechaFin)

        println("Fecha Inicio en String: $fechaInDateTypeInicio")
        println("Fecha Fin en String: $fechaInDateTypeFin")

        return reporteFacturaDao.findByFechas(fechaInDateTypeInicio, fechaInDateTypeFin)
    }
}