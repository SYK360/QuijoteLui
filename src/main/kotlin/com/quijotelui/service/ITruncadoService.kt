package com.quijotelui.service

import com.quijotelui.model.Truncado

interface ITruncadoService {
    fun findAll() : MutableList<Truncado>
    fun count() : MutableList<Any>
}