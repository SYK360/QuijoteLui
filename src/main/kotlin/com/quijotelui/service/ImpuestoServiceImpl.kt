package com.quijotelui.service

import com.quijotelui.model.Impuesto
import com.quijotelui.repository.IImpuestoDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ImpuestoServiceImpl : IImpuestoService {

    @Autowired
    lateinit var impuestoDao : IImpuestoDao

    @Override
    override fun findByComprobante(codigo: String, numero: String): MutableList<Impuesto> {
        return impuestoDao.findByComprobante(codigo, numero)
    }
}