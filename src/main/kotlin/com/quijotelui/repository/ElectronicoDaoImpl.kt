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
}