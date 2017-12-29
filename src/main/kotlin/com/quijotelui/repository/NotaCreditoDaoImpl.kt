package com.quijotelui.repository

import com.quijotelui.model.*
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class NotaCreditoDaoImpl : INotaCreditoDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    override fun findByComprobante(codigo: String, numero: String): MutableList<NotaCredito> {
        return entityMAnager.createQuery("from NotaCredito " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero).resultList as MutableList<NotaCredito>
    }

    override fun findContribuyenteByComprobante(codigo: String, numero: String): MutableList<Any> {
        val result = entityMAnager.createQuery("from Contribuyente c, " +
                "NotaCredito nc " +
                "WHERE c.id = nc.idContribuyente " +
                "and nc.codigo = :codigo " +
                "and nc.numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero)
                .resultList

        return result as MutableList<Any>

    }

    override fun findNotaCreditoDetalleByComprobante(codigo: String, numero: String): MutableList<NotaCreditoDetalle> {
        return entityMAnager.createQuery("from NotaCreditoDetalle " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero).resultList as MutableList<NotaCreditoDetalle>

    }

    override fun findImpuestoByComprobante(codigo: String, numero: String): MutableList<Impuesto> {
        return entityMAnager.createQuery("from Impuesto " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero).resultList as MutableList<Impuesto>
    }

    override fun findParametroByNombre(nombre: String): MutableList<Parametro> {
        return entityMAnager.createQuery("from Parametro " +
                "where nombre = :nombre " +
                "and estado = 'Activo'")
                .setParameter("nombre", nombre).resultList as MutableList<Parametro>

    }

    override fun findInformacionByDocumento(documento: String): MutableList<Informacion> {
        return entityMAnager.createQuery("from Informacion " +
                "where documento = :documento")
                .setParameter("documento", documento)
                .resultList as MutableList<Informacion>
    }
}