package com.quijotelui.service

import com.quijotelui.electronico.util.Fechas
import com.quijotelui.model.ReporteRetencion
import com.quijotelui.repository.IReporteRetencionDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReporteRetencionServiceImpl : IReporteRetencionService {

    @Autowired
    lateinit var reporteRetencionDao: IReporteRetencionDao

    override fun findByFechas(fechaInicio: String, fechaFin: String): MutableList<ReporteRetencion> {
        val fechas = Fechas()

        val fechaInDateTypeInicio = fechas.toDate(fechaInicio)
        val fechaInDateTypeFin = fechas.toDate(fechaFin)

        return reporteRetencionDao.findByFechas(fechaInDateTypeInicio, fechaInDateTypeFin)
    }

    override fun findByFechasEstado(fechaInicio: String, fechaFin: String, estado: String): MutableList<ReporteRetencion> {
        val fechas = Fechas()

        val fechaInDateTypeInicio = fechas.toDate(fechaInicio)
        val fechaInDateTypeFin = fechas.toDate(fechaFin)

        if (estado.equals("Autorizados")) {
            return reporteRetencionDao.findByFechasAutorizado(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
        else if (estado.equals("NoAutorizados")) {
            return reporteRetencionDao.findByFechasNoAutorizado(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
        else {
            return reporteRetencionDao.findByFechas(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
    }
}