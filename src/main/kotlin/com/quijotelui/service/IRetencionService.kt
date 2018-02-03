package com.quijotelui.service

import com.quijotelui.model.Informacion
import com.quijotelui.model.Parametro
import com.quijotelui.model.Retencion
import com.quijotelui.model.RetencionDetalle

interface IRetencionService {

    fun findAll() : MutableList<Retencion>
    fun findByComprobante(codigo : String, numero : String) : MutableList<Retencion>
    fun findEstadoByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findContribuyenteByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findRetencionDetalleByComprobante(codigo : String, numero : String) : MutableList<RetencionDetalle>
    fun findParametroByNombre(nombre : String) : MutableList<Parametro>
    fun findInformacionByDocumento(documento : String) : MutableList<Informacion>

}