package com.quijotelui.service

import com.quijotelui.model.*

interface IFacturaService {

    fun findAll() : MutableList<Factura>
    fun findByFecha(fecha: String) : MutableList<Factura>
    fun findByFechas(fechaInicio : String, fechaFin : String) : MutableList<Factura>
    fun findByComprobante(codigo : String, numero : String) : MutableList<Factura>
    fun findEstadoByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findContribuyenteByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findParametroByNombre(nombre : String) : MutableList<Parametro>
    fun findImpuestoByComprobante(codigo : String, numero : String) : MutableList<Impuesto>
    fun findPagoByComprobante(codigo : String, numero : String) : MutableList<Pago>
    fun findFacturaDetalleByComprobante(codigo : String, numero : String) : MutableList<FacturaDetalle>
    fun findInformacionByDocumento(documento : String) : MutableList<Informacion>

}