package com.quijotelui.repository

import com.quijotelui.model.ReporteFactura
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class ReporteFacturaDaoImpl : IReporteFacturaDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    @Override
    override fun findByFechas(fechaInicio: Date, fechaFin: Date): MutableList<ReporteFactura> {
        return entityMAnager.createQuery("from ReporteFactura " +
                "where fecha between :fechaInicio and :fechaFin").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteFactura>
    }

    @Override
    override fun findByFechasAutorizado(fechaInicio: Date, fechaFin: Date): MutableList<ReporteFactura> {
        return entityMAnager.createQuery("from ReporteFactura " +
                "where fecha between :fechaInicio and :fechaFin " +
                "and estado = 'AUTORIZADO'").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteFactura>
    }

    @Override
    override fun findByFechasNoAutorizado(fechaInicio: Date, fechaFin: Date): MutableList<ReporteFactura> {
        return entityMAnager.createQuery("from ReporteFactura " +
                "where fecha between :fechaInicio and :fechaFin " +
                "and estado != 'AUTORIZADO'").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteFactura>
    }
}