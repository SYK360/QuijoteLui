package com.quijotelui.repository

import com.quijotelui.model.Parametro
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Transactional
@Repository
class ParametroDaoImpl : IParametroDao {

    @PersistenceContext
    lateinit var entityMAnager : EntityManager

    override fun findAll(): MutableList<Parametro> {
        return entityMAnager.createQuery("from Parametro").resultList as MutableList<Parametro>
    }

    override fun findByNombre(nombre: String): MutableList<Parametro> {
        return entityMAnager.createQuery("from Parametro " +
                "where nombre = :nombre " +
                "and estado = 'Activo'")
                .setParameter("nombre", nombre).resultList as MutableList<Parametro>
    }
}