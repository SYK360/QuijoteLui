package com.quijotelui.service

import com.quijotelui.model.Informacion
import com.quijotelui.model.Parametro
import com.quijotelui.model.Retencion
import com.quijotelui.model.RetencionDetalle
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

    override fun findByComprobante(codigo: String, numero: String): MutableList<Retencion> {
        return retencionDao.findByComprobante(codigo, numero)
    }

    override fun findContribuyenteByComprobante(codigo: String, numero: String): MutableList<Any> {
        return retencionDao.findContribuyenteByComprobante(codigo, numero)
    }

    override fun findRetencionDetalleByComprobante(codigo: String, numero: String): MutableList<RetencionDetalle> {
        return retencionDao.findRetencionDetalleByComprobante(codigo, numero)
    }

    override fun findParametroByNombre(nombre: String): MutableList<Parametro> {
        return retencionDao.findParametroByNombre(nombre)
    }

    override fun findInformacionByDocumento(documento: String): MutableList<Informacion> {
        return retencionDao.findInformacionByDocumento(documento)
    }

    override fun findEstadoByComprobante(codigo: String, numero: String): MutableList<Any> {
        return retencionDao.findEstadoByComprobante(codigo, numero)
    }
}