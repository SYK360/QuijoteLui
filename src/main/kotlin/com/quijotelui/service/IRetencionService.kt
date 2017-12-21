package com.quijotelui.service

import com.quijotelui.model.Retencion

interface IRetencionService {

    fun findAll() : MutableList<Retencion>
    fun findByComprobante(codigo: String, numero: String): MutableList<Any>

}