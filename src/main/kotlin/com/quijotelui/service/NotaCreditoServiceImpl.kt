package com.quijotelui.service

import com.quijotelui.model.*
import com.quijotelui.repository.INotaCreditoDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotaCreditoServiceImpl : INotaCreditoService {

    @Autowired
    lateinit var notaCreditoDao : INotaCreditoDao

    override fun findByComprobante(codigo: String, numero: String): MutableList<NotaCredito> {
        return notaCreditoDao.findByComprobante(codigo, numero)
    }

    override fun findContribuyenteByComprobante(codigo: String, numero: String): MutableList<Any> {
        return notaCreditoDao.findContribuyenteByComprobante(codigo, numero)
    }

    override fun findNotaCreditoDetalleByComprobante(codigo: String, numero: String): MutableList<NotaCreditoDetalle> {
        return notaCreditoDao.findNotaCreditoDetalleByComprobante(codigo, numero)
    }

    override fun findImpuestoByComprobante(codigo: String, numero: String): MutableList<Impuesto> {
        return notaCreditoDao.findImpuestoByComprobante(codigo, numero)
    }

    override fun findParametroByNombre(nombre: String): MutableList<Parametro> {
        return notaCreditoDao.findParametroByNombre(nombre)
    }

    override fun findInformacionByDocumento(documento: String): MutableList<Informacion> {
        return notaCreditoDao.findInformacionByDocumento(documento)
    }

    override fun findEstadoByComprobante(codigo: String, numero: String): MutableList<Any> {
        return  notaCreditoDao.findEstadoByComprobante(codigo, numero)
    }
}