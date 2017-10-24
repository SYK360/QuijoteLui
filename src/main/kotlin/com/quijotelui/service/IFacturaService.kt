package com.quijotelui.service

import com.quijotelui.model.Factura

interface IFacturaService {
    fun findAll() : MutableList<Factura>
}