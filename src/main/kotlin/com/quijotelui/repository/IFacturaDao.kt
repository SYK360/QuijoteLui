package com.quijotelui.repository

import com.quijotelui.model.Factura
import com.quijotelui.model.Parametro
import java.util.*

interface IFacturaDao {

    fun findAll() : MutableList<Factura>
    fun findByFecha(fecha : Date) : MutableList<Factura>
    fun findByComprobante(codigo : String, numero : String) : MutableList<Factura>
    fun findContribuyenteByComprobante(codigo : String, numero : String) : MutableList<Any>
    fun findParametroByNombre(nombre : String) : MutableList<Parametro>

}