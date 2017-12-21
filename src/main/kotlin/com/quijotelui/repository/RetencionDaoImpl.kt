package com.quijotelui.repository

import com.quijotelui.model.Retencion
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class RetencionDaoImpl : IRetencionDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    override fun findAll(): MutableList<Retencion> {
        return entityMAnager.createQuery("from Retencion").resultList as MutableList<Retencion>
    }

    override fun findByComprobante(codigo: String, numero: String): MutableList<Any> {
        val result = entityMAnager.createQuery("from Contribuyente c, " +
                "Retencion r " +
//                "RetencionDetalle d " +
                "WHERE c.id = r.idContribuyente " +
//                "and r.codigo = d.codigo " +
//                "and r.numero = d.numero " +
                "and r.codigo = :codigo " +
                "and r.numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero)
                .resultList

        return result as MutableList<Any>
    }
}