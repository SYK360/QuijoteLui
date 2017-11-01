package com.quijotelui.repository

import com.quijotelui.model.Impuesto
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class ImpuestoDaoImpl : IImpuestoDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    @Override
    override fun findByComprobante(codigo: String, numero: String): MutableList<Impuesto> {
        return entityMAnager.createQuery("from Impuesto " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero",numero).resultList as MutableList<Impuesto>
    }

}