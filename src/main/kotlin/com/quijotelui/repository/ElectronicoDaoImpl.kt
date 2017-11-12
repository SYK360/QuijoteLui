package com.quijotelui.repository

import com.quijotelui.model.Electronico
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class ElectronicoDaoImpl : IElectronicoDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    @Override
    override fun findAll(): MutableList<Electronico> {
        return entityMAnager.createQuery("from Electronico").resultList as MutableList<Electronico>
    }

    @Override
    override fun saveElectronico(electronico: Electronico) {
        entityMAnager.persist(electronico)
    }

    @Override
    override fun updateElectronico(electronico: Electronico) {
        val e = findById(electronico.id!!)
        e.codigo = electronico.codigo
        e.numero = electronico.numero
        e.numeroAutorizacion = electronico.numeroAutorizacion
        e.fechaAutorizacion = electronico.fechaAutorizacion
        e.observacion = electronico.observacion
        e.estado = electronico.estado
        entityMAnager.flush()
    }

    @Override
    override fun findById(id : Long): Electronico {
        return entityMAnager.find(Electronico::class.java, id)
    }

    @Override
    override fun findByComprobante(codigo: String, numero: String): MutableList<Electronico> {
        return entityMAnager.createQuery("from Electronico " +
                "where codigo = :codigo " +
                "and numero = :numero")
                .setParameter("codigo", codigo)
                .setParameter("numero", numero).resultList as MutableList<Electronico>
    }
}