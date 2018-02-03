package com.quijotelui.service

import com.quijotelui.model.*

interface IGuiaService {

    fun findByComprobante(codigo : String, numero : String) : MutableList<Guia>
    fun findEstadoByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findContribuyenteByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findDestinatarioByComprobante(codigo : String, numero : String) : MutableList<GuiaDestinatario>
    fun findDestinatarioDetalleByComprobante(codigo : String, numero : String, documento: String) : MutableList<GuiaDestinatarioDetalle>
    fun findParametroByNombre(nombre : String) : MutableList<Parametro>

}