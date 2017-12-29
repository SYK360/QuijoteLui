package com.quijotelui.repository

import com.quijotelui.model.ReporteFactura
import com.quijotelui.model.ReporteNotaCredito
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class ReporteNotaCreditoDaoImpl : IReporteNotaCreditoDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    override fun findByFechas(fechaInicio: Date, fechaFin: Date): MutableList<ReporteNotaCredito> {
        return entityMAnager.createQuery("from ReporteNotaCredito " +
                "where fecha between :fechaInicio and :fechaFin").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteNotaCredito>
    }

    override fun findByFechasAutorizado(fechaInicio: Date, fechaFin: Date): MutableList<ReporteNotaCredito> {
        return entityMAnager.createQuery("from ReporteNotaCredito " +
                "where fecha between :fechaInicio and :fechaFin " +
                "and estado = 'AUTORIZADO'").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteNotaCredito>
    }

    override fun findByFechasNoAutorizado(fechaInicio: Date, fechaFin: Date): MutableList<ReporteNotaCredito> {
        return entityMAnager.createQuery("from ReporteNotaCredito " +
                "where fecha between :fechaInicio and :fechaFin " +
                "and estado != 'AUTORIZADO'").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteNotaCredito>
    }

}