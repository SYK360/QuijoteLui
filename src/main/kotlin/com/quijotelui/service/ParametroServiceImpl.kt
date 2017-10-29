package com.quijotelui.service

import com.quijotelui.model.Parametro
import com.quijotelui.repository.IParametroDao
import org.springframework.beans.factory.annotation.Autowired

class ParametroServiceImpl : IParametroService {

    @Autowired
    lateinit var parametroDao : IParametroDao


    override fun findAll(): MutableList<Parametro> {
        return parametroDao.findAll()
    }

    override fun findByNombre(nombre: String): MutableList<Parametro> {
        return parametroDao.findByNombre(nombre)
    }
}