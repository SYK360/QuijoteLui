package com.quijotelui.repository

import com.quijotelui.model.Parametro

interface IParametroDao {

    fun findAll() : MutableList<Parametro>
    fun findByNombre(nombre : String) : MutableList<Parametro>

}