package com.quijotelui.repository

import com.quijotelui.model.*

interface INotaCreditoDao {

    fun findByComprobante(codigo : String, numero : String) : MutableList<NotaCredito>
    fun findContribuyenteByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findNotaCreditoDetalleByComprobante(codigo : String, numero : String) : MutableList<NotaCreditoDetalle>
    fun findImpuestoByComprobante(codigo : String, numero : String) : MutableList<Impuesto>
    fun findParametroByNombre(nombre : String) : MutableList<Parametro>
    fun findInformacionByDocumento(documento : String) : MutableList<Informacion>

}