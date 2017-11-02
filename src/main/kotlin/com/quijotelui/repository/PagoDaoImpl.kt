package com.quijotelui.repository

import com.quijotelui.model.Pago
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class PagoDaoImpl : IPagoDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    @Override
    override fun findByComprobante(codigo: String, numero: String): MutableList<Pago> {
        return entityMAnager.createQuery("from Pago " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero",numero).resultList as MutableList<Pago>
    }

}