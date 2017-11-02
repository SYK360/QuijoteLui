package com.quijotelui.repository

import com.quijotelui.model.*
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

    @Override
    override fun findFacturaDetalleByComprobante(codigo: String, numero: String): MutableList<FacturaDetalle> {
        return entityMAnager.createQuery("from FacturaDetalle " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero",numero).resultList as MutableList<FacturaDetalle>
    }

    @Override
    override fun findContribuyenteByComprobante(codigo: String, numero: String) : MutableList<Any> {

        val result = entityMAnager.createQuery("from Contribuyente c, " +
                "Factura f  " +
                "WHERE c.id = f.idContribuyente " +
                "and f.codigo = :codigo " +
                "and f.numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero)
                .resultList


        /*
        var contribuyente = Contribuyente()
        var factura = Factura()

        if (result.size > 0) {

            for (i in result.indices) {
                val row = result.get(i) as Array<Any>
                contribuyente = row[0] as Contribuyente
                factura = row[1] as Factura

            }

        }
        println("Nombre Comercial " + contribuyente.nombreComercial)
        println("Razón Social " + factura.razonSocial)
        */

        return result as MutableList<Any>
    }

    @Override
    override fun findParametroByNombre(nombre: String): MutableList<Parametro> {
        return entityMAnager.createQuery("from Parametro " +
                "where nombre = :nombre " +
                "and estado = 'Activo'")
                .setParameter("nombre", nombre).resultList as MutableList<Parametro>
    }

    @Override
    override fun findImpuestoByComprobante(codigo: String, numero: String): MutableList<Impuesto> {
        return entityMAnager.createQuery("from Impuesto " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero",numero).resultList as MutableList<Impuesto>
    }

    @Override
    override fun findPagoByComprobante(codigo: String, numero: String): MutableList<Pago> {
        return entityMAnager.createQuery("from Pago " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero",numero).resultList as MutableList<Pago>
    }
}