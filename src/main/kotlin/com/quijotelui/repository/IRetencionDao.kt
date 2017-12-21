package com.quijotelui.repository

import com.quijotelui.model.Retencion

interface IRetencionDao {

    fun findAll() : MutableList<Retencion>
    fun findByComprobante(codigo : String, numero : String) : MutableList<Any>
}