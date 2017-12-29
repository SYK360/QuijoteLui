package com.quijotelui.service

import com.quijotelui.electronico.util.Fechas
import com.quijotelui.model.ReporteGuia
import com.quijotelui.repository.IReporteGuiaDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReporteGuiaServiceImpl : IReporteGuiaService {

    @Autowired
    lateinit var reporteGuiaDao: IReporteGuiaDao

    override fun findByFechas(fechaInicio: String, fechaFin: String): MutableList<ReporteGuia> {
        val fechas = Fechas()

        val fechaInDateTypeInicio = fechas.toDate(fechaInicio)
        val fechaInDateTypeFin = fechas.toDate(fechaFin)

        return reporteGuiaDao.findByFechas(fechaInDateTypeInicio, fechaInDateTypeFin)
    }

    override fun findByFechasEstado(fechaInicio: String, fechaFin: String, estado: String): MutableList<ReporteGuia> {
        val fechas = Fechas()

        val fechaInDateTypeInicio = fechas.toDate(fechaInicio)
        val fechaInDateTypeFin = fechas.toDate(fechaFin)

        if (estado.equals("Autorizados")) {
            return reporteGuiaDao.findByFechasAutorizado(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
        else if (estado.equals("NoAutorizados")) {
            return reporteGuiaDao.findByFechasNoAutorizado(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
        else {
            return reporteGuiaDao.findByFechas(fechaInDateTypeInicio, fechaInDateTypeFin)
        }
    }

}