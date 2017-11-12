package com.quijotelui.service

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

        println("Fecha en String: $fecha")

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val fechaInDateType : Date
        fechaInDateType = simpleDateFormat.parse(fecha)

        println("Fecha en Date: $fechaInDateType")

        return facturaDao.findByFecha(fechaInDateType)
    }

    override fun findByFechas(fechaInicio: String, fechaFin: String): MutableList<Factura> {
        println("Fecha Inicio en String: $fechaInicio")
        println("Fecha Fin en String: $fechaFin")

        val simpleDateFormatInicio = SimpleDateFormat("yyyy-MM-dd")
        val fechaInDateTypeInicio : Date
        fechaInDateTypeInicio = simpleDateFormatInicio.parse(fechaInicio)

        val simpleDateFormatFin = SimpleDateFormat("yyyy-MM-dd")
        val fechaInDateTypeFin : Date
        fechaInDateTypeFin = simpleDateFormatFin.parse(fechaFin)

        println("Fecha Inicio en String: $fechaInDateTypeInicio")
        println("Fecha Fin en String: $fechaInDateTypeFin")

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

}