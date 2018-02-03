package com.quijotelui.service

import com.quijotelui.model.Informacion
import com.quijotelui.model.NotaCredito
import com.quijotelui.model.NotaCreditoDetalle
import com.quijotelui.model.Parametro
import com.quijotelui.model.Impuesto

interface INotaCreditoService {

    fun findByComprobante(codigo : String, numero : String) : MutableList<NotaCredito>
    fun findEstadoByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findContribuyenteByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findNotaCreditoDetalleByComprobante(codigo : String, numero : String) : MutableList<NotaCreditoDetalle>
    fun findImpuestoByComprobante(codigo : String, numero : String) : MutableList<Impuesto>
    fun findParametroByNombre(nombre : String) : MutableList<Parametro>
    fun findInformacionByDocumento(documento : String) : MutableList<Informacion>

}