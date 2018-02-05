package com.quijotelui.repository

import com.quijotelui.model.Truncado

interface ITruncadoDao {

    fun findAll() : MutableList<Truncado>
    fun findById(id : Long) : Truncado
    fun count() : MutableList<Any>
    fun count(id : Long) : MutableList<Any>
    fun deleteById(id : Long)

}