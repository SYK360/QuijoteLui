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
}