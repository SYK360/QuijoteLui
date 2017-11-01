package com.quijotelui.service

import com.quijotelui.model.Impuesto

interface IImpuestoService {

    fun findByComprobante(codigo : String, numero : String) : MutableList<Impuesto>

}