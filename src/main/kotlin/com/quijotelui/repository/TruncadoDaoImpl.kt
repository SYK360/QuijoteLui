package com.quijotelui.repository

import com.quijotelui.model.Truncado
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class TruncadoDaoImpl : ITruncadoDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    override fun findAll(): MutableList<Truncado> {
        return entityMAnager.createQuery("from Truncado")
                .resultList as MutableList<Truncado>
    }

    override fun count(): MutableList<Any> {
        return entityMAnager.createQuery("select count(*) from Truncado")
                .resultList as MutableList<Any>
    }

    override fun count(id: Long): MutableList<Any> {
        return entityMAnager.createQuery("select count(*) from Truncado " +
                "where id = :id")
                .setParameter("id", id)
                .resultList as MutableList<Any>
    }

    override fun deleteById(id: Long) {
        entityMAnager.remove(findById(id))
    }

    override fun findById(id: Long): Truncado {
            return entityMAnager.find(Truncado::class.java, id)
    }
}