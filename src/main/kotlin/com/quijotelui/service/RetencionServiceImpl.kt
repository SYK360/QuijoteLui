package com.quijotelui.service

import com.quijotelui.model.Retencion
import com.quijotelui.repository.IRetencionDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RetencionServiceImpl : IRetencionService {

    @Autowired
    lateinit var retencionDao : IRetencionDao

    override fun findAll(): MutableList<Retencion> {
        return retencionDao.findAll()
    }

    override fun findByComprobante(codigo: String, numero: String): MutableList<Any> {
        return retencionDao.findByComprobante(codigo, numero)
    }
}