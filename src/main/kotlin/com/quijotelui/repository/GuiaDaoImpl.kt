package com.quijotelui.repository

import com.quijotelui.model.*
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class GuiaDaoImpl : IGuiaDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    override fun findByComprobante(codigo: String, numero: String): MutableList<Guia> {
        return entityMAnager.createQuery("from Guia " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero).resultList as MutableList<Guia>
    }

    override fun findContribuyenteByComprobante(codigo: String, numero: String): MutableList<Any> {
        val result = entityMAnager.createQuery("from Contribuyente c, " +
                "Guia g " +
                "WHERE c.id = g.idContribuyente " +
                "and g.codigo = :codigo " +
                "and g.numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero)
                .resultList

        return result as MutableList<Any>
    }

    override fun findDestinatarioByComprobante(codigo: String, numero: String): MutableList<GuiaDestinatario> {
        return entityMAnager.createQuery("from GuiaDestinatario " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero).resultList as MutableList<GuiaDestinatario>
    }

    override fun findDestinatarioDetalleByComprobante(codigo: String, numero: String, documento: String): MutableList<GuiaDestinatarioDetalle> {
        return entityMAnager.createQuery("from GuiaDestinatarioDetalle " +
                "where codigo = :codigo " +
                "and numero = :numero " +
                "and documento = :documento")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero)
                .setParameter("documento", documento)
                .resultList as MutableList<GuiaDestinatarioDetalle>
    }

    override fun findParametroByNombre(nombre: String): MutableList<Parametro> {
        return entityMAnager.createQuery("from Parametro " +
                "where nombre = :nombre " +
                "and estado = 'Activo'")
                .setParameter("nombre", nombre).resultList as MutableList<Parametro>
    }

    override fun findEstadoByComprobante(codigo: String, numero: String): MutableList<Any> {
        return entityMAnager.createQuery("select estado from ReporteGuia " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero).resultList as MutableList<Any>
    }
}