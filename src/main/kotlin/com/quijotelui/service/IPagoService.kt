package com.quijotelui.service

import com.quijotelui.model.Pago

interface IPagoService {

    fun findByComprobante(codigo : String, numero : String) : MutableList<Pago>

}