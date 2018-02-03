package com.quijotelui.service

import com.quijotelui.model.*
import com.quijotelui.repository.IGuiaDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GuiaServiceImpl : IGuiaService {

    @Autowired
    lateinit var guiaDao : IGuiaDao


    override fun findByComprobante(codigo: String, numero: String): MutableList<Guia> {
        return guiaDao.findByComprobante(codigo, numero)
    }

    override fun findContribuyenteByComprobante(codigo: String, numero: String): MutableList<Any> {
        return guiaDao.findContribuyenteByComprobante(codigo, numero)
    }

    override fun findDestinatarioByComprobante(codigo: String, numero: String): MutableList<GuiaDestinatario> {
        return guiaDao.findDestinatarioByComprobante(codigo, numero)
    }

    override fun findDestinatarioDetalleByComprobante(codigo: String, numero: String, documento: String): MutableList<GuiaDestinatarioDetalle> {
        return guiaDao.findDestinatarioDetalleByComprobante(codigo, numero, documento)
    }

    override fun findParametroByNombre(nombre: String): MutableList<Parametro> {
        return guiaDao.findParametroByNombre(nombre)
    }

    override fun findEstadoByComprobante(codigo: String, numero: String): MutableList<Any> {
        return guiaDao.findEstadoByComprobante(codigo, numero)
    }
}