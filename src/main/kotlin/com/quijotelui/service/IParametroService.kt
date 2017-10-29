package com.quijotelui.service

import com.quijotelui.model.Parametro

interface IParametroService {

    fun findAll() : MutableList<Parametro>
    fun findByNombre(nombre : String) : MutableList<Parametro>

}