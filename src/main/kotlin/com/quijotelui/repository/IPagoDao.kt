package com.quijotelui.repository

import com.quijotelui.model.Pago

interface IPagoDao {

    fun findByComprobante(codigo : String, numero : String) : MutableList<Pago>

}