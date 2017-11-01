package com.quijotelui.repository

import com.quijotelui.model.Impuesto

interface IImpuestoDao {

    fun findByComprobante(codigo : String, numero : String) : MutableList<Impuesto>

}