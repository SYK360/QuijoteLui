package com.quijotelui.repository

import com.quijotelui.model.Factura
import java.util.*

interface IFacturaDao {
    fun findAll() : MutableList<Factura>
    fun findByFecha(fecha : Date) : MutableList<Factura>
}