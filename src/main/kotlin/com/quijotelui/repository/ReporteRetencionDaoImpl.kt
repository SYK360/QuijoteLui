package com.quijotelui.repository

import com.quijotelui.model.ReporteRetencion
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class ReporteRetencionDaoImpl : IReporteRetencionDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    override fun findByFechas(fechaInicio: Date, fechaFin: Date): MutableList<ReporteRetencion> {
        return entityMAnager.createQuery("from ReporteRetencion " +
                "where fecha between :fechaInicio and :fechaFin").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteRetencion>
    }

    override fun findByFechasAutorizado(fechaInicio: Date, fechaFin: Date): MutableList<ReporteRetencion> {
        return entityMAnager.createQuery("from ReporteRetencion " +
                "where fecha between :fechaInicio and :fechaFin " +
                "and estado = 'AUTORIZADO'").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteRetencion>
    }

    override fun findByFechasNoAutorizado(fechaInicio: Date, fechaFin: Date): MutableList<ReporteRetencion> {
        return entityMAnager.createQuery("from ReporteRetencion " +
                "where fecha between :fechaInicio and :fechaFin " +
                "and estado != 'AUTORIZADO'").
                setParameter("fechaInicio", fechaInicio).
                setParameter("fechaFin", fechaFin).
                resultList as MutableList<ReporteRetencion>
    }
}