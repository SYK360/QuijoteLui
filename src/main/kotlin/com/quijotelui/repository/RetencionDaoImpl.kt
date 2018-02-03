package com.quijotelui.repository

import com.quijotelui.model.Informacion
import com.quijotelui.model.Parametro
import com.quijotelui.model.Retencion
import com.quijotelui.model.RetencionDetalle
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

    override fun findByComprobante(codigo: String, numero: String): MutableList<Retencion> {
        return entityMAnager.createQuery("from Retencion " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero).resultList as MutableList<Retencion>
    }

    override fun findContribuyenteByComprobante(codigo: String, numero: String): MutableList<Any> {
        val result = entityMAnager.createQuery("from Contribuyente c, " +
                "Retencion r " +
                "WHERE c.id = r.idContribuyente " +
                "and r.codigo = :codigo " +
                "and r.numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero)
                .resultList

        return result as MutableList<Any>
    }

    override fun findRetencionDetalleByComprobante(codigo : String, numero : String) : MutableList<RetencionDetalle> {
        return entityMAnager.createQuery("from RetencionDetalle " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero).resultList as MutableList<RetencionDetalle>
    }

    override fun findParametroByNombre(nombre : String) : MutableList<Parametro> {
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

    override fun findEstadoByComprobante(codigo: String, numero: String): MutableList<Any> {
        return entityMAnager.createQuery("select estado from ReporteRetencion " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero).resultList as MutableList<Any>
    }
}