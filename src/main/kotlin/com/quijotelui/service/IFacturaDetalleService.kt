package com.quijotelui.service

import com.quijotelui.model.FacturaDetalle

interface IFacturaDetalleService {

    fun findAll() : MutableList<FacturaDetalle>
    fun findByComprobante(codigo : String, numero : String) : MutableList<FacturaDetalle>

}