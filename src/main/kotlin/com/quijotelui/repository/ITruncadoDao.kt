package com.quijotelui.repository

import com.quijotelui.model.Truncado

interface ITruncadoDao {
    fun findAll() : MutableList<Truncado>
    fun count() : MutableList<Any>
}