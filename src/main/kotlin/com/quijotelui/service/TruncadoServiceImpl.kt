package com.quijotelui.service

import com.quijotelui.model.Truncado
import com.quijotelui.repository.ITruncadoDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TruncadoServiceImpl : ITruncadoService {

    @Autowired
    lateinit var truncadoDao: ITruncadoDao

    override fun findAll(): MutableList<Truncado> {
        return truncadoDao.findAll()
    }

    override fun count(): MutableList<Any> {
        return truncadoDao.count()
    }

    override fun count(id: Long): MutableList<Any> {
        return truncadoDao.count(id)
    }

    override fun findById(id: Long): Truncado {
        return truncadoDao.findById(id)
    }

    override fun deleteById(id: Long) {
        return truncadoDao.deleteById(id)
    }
}