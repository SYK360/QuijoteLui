package com.quijotelui.service

import com.quijotelui.electronico.util.Fechas
import com.quijotelui.model.*
import com.quijotelui.repository.IFacturaDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class FacturaServiceImpl : IFacturaService {

    @Autowired
    lateinit var facturaDao : IFacturaDao

    override fun findAll(): MutableList<Factura> {
        return facturaDao.findAll()
    }

    override fun findByFecha(fecha: String): MutableList<Factura> {

        val fechas = Fechas()

        val fechaInDateType = fechas.toDate(fecha)

        return facturaDao.findByFecha(fechaInDateType)
    }

    override fun findByFechas(fechaInicio: String, fechaFin: String): MutableList<Factura> {

        val fechas = Fechas()

        val fechaInDateTypeInicio = fechas.toDate(fechaInicio)
        val fechaInDateTypeFin = fechas.toDate(fechaFin)

        return facturaDao.findByFechas(fechaInDateTypeInicio, fechaInDateTypeFin)
    }

    override fun findByComprobante(codigo: String, numero: String): MutableList<Factura> {
        return facturaDao.findByComprobante(codigo, numero)
    }

    override fun findContribuyenteByComprobante(codigo: String, numero: String): MutableList<Any> {
        return facturaDao.findContribuyenteByComprobante(codigo, numero)
    }

    override fun findParametroByNombre(nombre: String): MutableList<Parametro> {
        return facturaDao.findParametroByNombre(nombre)
    }

    override fun findImpuestoByComprobante(codigo: String, numero: String): MutableList<Impuesto> {
        return facturaDao.findImpuestoByComprobante(codigo, numero)
    }

    override fun findPagoByComprobante(codigo: String, numero: String): MutableList<Pago> {
        return facturaDao.findPagoByComprobante(codigo, numero)
    }

    override fun findFacturaDetalleByComprobante(codigo: String, numero: String): MutableList<FacturaDetalle> {
        return facturaDao.findFacturaDetalleByComprobante(codigo, numero)
    }

    override fun findInformacionByDocumento(documento: String): MutableList<Informacion> {
        return facturaDao.findInformacionByDocumento(documento)
    }

    override fun findEstadoByComprobante(codigo: String, numero: String): MutableList<Any> {
        return facturaDao.findEstadoByComprobante(codigo, numero)
    }
}