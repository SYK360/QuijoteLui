package com.quijotelui.service

import com.quijotelui.model.Pago
import com.quijotelui.repository.IPagoDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PagoServiceImpl : IPagoService {

    @Autowired
    lateinit var pagoDao : IPagoDao

    @Override
    override fun findByComprobante(codigo: String, numero: String): MutableList<Pago> {
        return pagoDao.findByComprobante(codigo, numero)
    }
}