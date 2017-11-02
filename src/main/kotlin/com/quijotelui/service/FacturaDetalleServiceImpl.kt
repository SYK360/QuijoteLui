package com.quijotelui.service

import com.quijotelui.model.FacturaDetalle
import com.quijotelui.repository.IFacturaDetalleDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FacturaDetalleServiceImpl : IFacturaDetalleService {

    @Autowired
    lateinit var facturaDetalleDao : IFacturaDetalleDao

    @Override
    override fun findByComprobante(codigo: String, numero: String): MutableList<FacturaDetalle> {
        return facturaDetalleDao.findByComprobante(codigo, numero)
    }

    @Override
    override fun findAll(): MutableList<FacturaDetalle> {
        return facturaDetalleDao.findAll()
    }
}