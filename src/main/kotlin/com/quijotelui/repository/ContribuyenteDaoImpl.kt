package com.quijotelui.repository

import com.quijotelui.model.Contribuyente
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class ContribuyenteDaoImpl : IContribuyenteDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    @Override
    override fun findAll(): MutableList<Contribuyente> {
        return entityMAnager.createQuery("from Contribuyente").resultList as MutableList<Contribuyente>
    }

    @Override
    override fun findByRUC(ruc: String): Contribuyente {
        println("DAO Ruc: $ruc")
        return entityMAnager.createQuery("from Contribuyente " +
                "where ruc = :ruc").setParameter("ruc", ruc).singleResult as Contribuyente
    }


}