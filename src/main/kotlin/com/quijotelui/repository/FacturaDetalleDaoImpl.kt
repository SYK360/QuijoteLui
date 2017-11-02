package com.quijotelui.repository

import com.quijotelui.model.FacturaDetalle
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class FacturaDetalleDaoImpl : IFacturaDetalleDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    @Override
    override fun findByComprobante(codigo: String, numero: String): MutableList<FacturaDetalle> {
        return entityMAnager.createQuery("from FacturaDetalle " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero",numero).resultList as MutableList<FacturaDetalle>
    }

    @Override
    override fun findAll(): MutableList<FacturaDetalle> {
        return entityMAnager.createQuery("from FacturaDetalle").resultList as MutableList<FacturaDetalle>
    }

}