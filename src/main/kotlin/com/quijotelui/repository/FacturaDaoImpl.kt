package com.quijotelui.repository

import com.quijotelui.model.Factura
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class FacturaDaoImpl : IFacturaDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    @Override
    override fun findAll(): MutableList<Factura> {
        return entityMAnager.createQuery("from Factura").resultList as MutableList<Factura>
    }

    @Override
    override fun findByFecha(fecha : Date): MutableList<Factura> {
        return entityMAnager.createQuery("from Factura " +
                "where fecha = :fecha").setParameter("fecha", fecha).resultList as MutableList<Factura>
    }
}