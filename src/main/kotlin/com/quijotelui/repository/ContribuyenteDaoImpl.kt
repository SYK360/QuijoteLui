package com.quijotelui.repository

import com.quijotelui.model.Contribuyente
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class ContribuyenteDaoImpl : ContribuyenteDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    @Override
    override fun getContribuyentes(): MutableList<Contribuyente> {
        return entityMAnager.createQuery("from Contribuyente").resultList as MutableList<Contribuyente>
    }
}