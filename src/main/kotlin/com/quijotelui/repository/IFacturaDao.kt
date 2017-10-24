package com.quijotelui.repository

import com.quijotelui.model.Factura

interface IFacturaDao {
    fun findAll() : MutableList<Factura>
}