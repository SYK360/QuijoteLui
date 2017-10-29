package com.quijotelui.service

import com.quijotelui.model.Contribuyente
import com.quijotelui.model.Factura
import java.util.*

interface IFacturaService {

    fun findAll() : MutableList<Factura>
    fun findByFecha(fecha: String) : MutableList<Factura>
    fun findByComprobante(codigo : String, numero : String) : MutableList<Factura>
    fun findContribuyenteByComprobante(codigo : String, numero : String) : MutableList<Any>

}