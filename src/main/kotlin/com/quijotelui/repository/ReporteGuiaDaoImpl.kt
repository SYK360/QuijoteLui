package com.quijotelui.repository

import com.quijotelui.model.ReporteFactura
import com.quijotelui.model.ReporteGuia
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class ReporteGuiaDaoImpl : IReporteGuiaDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    override fun findByFechas(fechaInicio: Date, fechaFin: Date): MutableList<ReporteGuia> {
        return entityMAnager.createQuery("from ReporteGuia " +
                "where fecha between :fechaInicio and :fechaFin").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteGuia>
    }

    override fun findByFechasAutorizado(fechaInicio: Date, fechaFin: Date): MutableList<ReporteGuia> {
        return entityMAnager.createQuery("from ReporteGuia " +
                "where fecha between :fechaInicio and :fechaFin " +
                "and estado = 'AUTORIZADO'").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteGuia>
    }

    override fun findByFechasNoAutorizado(fechaInicio: Date, fechaFin: Date): MutableList<ReporteGuia> {
        return entityMAnager.createQuery("from ReporteGuia " +
                "where fecha between :fechaInicio and :fechaFin " +
                "and estado != 'AUTORIZADO'").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteGuia>
    }
}