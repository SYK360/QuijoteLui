package com.quijotelui.service

import com.quijotelui.model.Factura
import java.util.*

interface IFacturaService {
    fun findAll() : MutableList<Factura>
    fun findByFecha(fecha: Date) : MutableList<Factura>
}