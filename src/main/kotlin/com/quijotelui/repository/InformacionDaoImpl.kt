package com.quijotelui.repository

import com.quijotelui.model.Informacion
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class InformacionDaoImpl : IInformacionDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    @Override
    override fun findByDocumento(documento : String): MutableList<Informacion> {

        return entityMAnager.createQuery("from Informacion " +
                "where documento = :documento")
                .setParameter("documento", documento)
                .resultList as MutableList<Informacion>

    }
}