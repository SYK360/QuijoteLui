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

    @Override
    override fun findByComprobante(codigo: String, numero: String): MutableList<Factura> {
        return entityMAnager.createQuery("from Factura " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero",numero).resultList as MutableList<Factura>
    }

    override fun count(codigo: String, numero: String): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}