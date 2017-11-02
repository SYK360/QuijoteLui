package com.quijotelui.service

import com.quijotelui.model.Factura
import com.quijotelui.model.Impuesto
import com.quijotelui.model.Pago
import com.quijotelui.model.Parametro

interface IFacturaService {

    fun findAll() : MutableList<Factura>
    fun findByFecha(fecha: String) : MutableList<Factura>
    fun findByComprobante(codigo : String, numero : String) : MutableList<Factura>
    fun findContribuyenteByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findParametroByNombre(nombre : String) : MutableList<Parametro>
    fun findImpuestoByComprobante(codigo : String, numero : String) : MutableList<Impuesto>
    fun findPagoByComprobante(codigo : String, numero : String) : MutableList<Pago>

}