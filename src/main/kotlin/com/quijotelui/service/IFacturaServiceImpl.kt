package com.quijotelui.service

import com.quijotelui.model.Factura
import com.quijotelui.repository.IFacturaDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class IFacturaServiceImpl : IFacturaService {

    @Autowired
    lateinit var facturaDao : IFacturaDao

    @Override
    override fun findAll(): MutableList<Factura> {
        return facturaDao.findAll()
    }
}