package com.quijotelui.service

import com.quijotelui.model.Contribuyente
import com.quijotelui.model.Factura
import com.quijotelui.repository.IFacturaDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class FacturaServiceImpl : IFacturaService {

    @Autowired
    lateinit var facturaDao : IFacturaDao

    @Override
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

    override fun findByComprobante(codigo: String, numero: String): MutableList<Factura> {
        return facturaDao.findByComprobante(codigo, numero)
    }

    override fun findContribuyenteByComprobante(codigo: String, numero: String): MutableList<Any> {
        return facturaDao.findContribuyenteByComprobante(codigo, numero)
    }
}