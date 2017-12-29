package com.quijotelui.service

import com.quijotelui.electronico.util.Fechas
import com.quijotelui.model.ReporteNotaCredito
import com.quijotelui.repository.IReporteNotaCreditoDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReporteNotaCreditoServiceImpl : IReporteNotaCreditoService {

    @Autowired
    lateinit var reporteNotaCreditoDao: IReporteNotaCreditoDao

    override fun findByFechas(fechaInicio: String, fechaFin: String): MutableList<ReporteNotaCredito> {
        val fechas = Fechas()

        val fechaInDateTypeInicio = fechas.toDate(fechaInicio)
        val fechaInDateTypeFin = fechas.toDate(fechaFin)

        return reporteNotaCreditoDao.findByFechas(fechaInDateTypeInicio, fechaInDateTypeFin)
    }

    override fun findByFechasEstado(fechaInicio: String, fechaFin: String, estado: String): MutableList<ReporteNotaCredito> {
        val fechas = Fechas()

        val fechaInDateTypeInicio = fechas.toDate(fechaInicio)
        val fechaInDateTypeFin = fechas.toDate(fechaFin)

        if (estado.equals("Autorizados")) {
            return reporteNotaCreditoDao.findByFechasAutorizado(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
        else if (estado.equals("NoAutorizados")) {
            return reporteNotaCreditoDao.findByFechasNoAutorizado(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
        else {
            return reporteNotaCreditoDao.findByFechas(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
    }

}