package com.quijotelui.service

import com.quijotelui.electronico.util.Fechas
import com.quijotelui.model.ReporteFactura
import com.quijotelui.repository.IReporteFacturaDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReporteFacturaServiceImpl : IReporteFacturaService {

    @Autowired
    lateinit var reporteFacturaDao : IReporteFacturaDao

    override fun findByFechas(fechaInicio: String, fechaFin: String): MutableList<ReporteFactura> {

        val fechas = Fechas()

        val fechaInDateTypeInicio = fechas.toDate(fechaInicio)
        val fechaInDateTypeFin = fechas.toDate(fechaFin)

        return reporteFacturaDao.findByFechas(fechaInDateTypeInicio, fechaInDateTypeFin)
    }

    /*
     *
     *El estado es:
     *   - Todos
     *   - NoAutorizados
     *   - Autorizados
     */
    override fun findByFechasEstado(fechaInicio: String, fechaFin: String, estado: String): MutableList<ReporteFactura> {

        val fechas = Fechas()

        val fechaInDateTypeInicio = fechas.toDate(fechaInicio)
        val fechaInDateTypeFin = fechas.toDate(fechaFin)

        if (estado.equals("Autorizados")) {
            return reporteFacturaDao.findByFechasAutorizado(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
        else if (estado.equals("NoAutorizados")) {
            return reporteFacturaDao.findByFechasNoAutorizado(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
        else {
            return reporteFacturaDao.findByFechas(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
    }
}