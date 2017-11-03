package com.quijotelui.repository

import com.quijotelui.model.*
import java.util.*

interface IFacturaDao {

    fun findAll() : MutableList<Factura>
    fun findByFecha(fecha : Date) : MutableList<Factura>
    fun findByComprobante(codigo : String, numero : String) : MutableList<Factura>
    fun findContribuyenteByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findParametroByNombre(nombre : String) : MutableList<Parametro>
    fun findImpuestoByComprobante(codigo : String, numero : String) : MutableList<Impuesto>
    fun findPagoByComprobante(codigo : String, numero : String) : MutableList<Pago>
    fun findFacturaDetalleByComprobante(codigo : String, numero : String) : MutableList<FacturaDetalle>
    fun findInformacionByDocumento(documento : String) : MutableList<Informacion>


}