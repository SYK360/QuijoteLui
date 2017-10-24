package com.quijotelui.repository

import com.quijotelui.model.Factura
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class IFacturaDaoImpl : IFacturaDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    @Override
    override fun findAll(): MutableList<Factura> {
        return entityMAnager.createQuery("from Factura").resultList as MutableList<Factura>
    }
}