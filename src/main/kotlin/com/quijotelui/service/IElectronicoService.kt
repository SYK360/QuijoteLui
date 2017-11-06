package com.quijotelui.service

import com.quijotelui.model.Electronico

interface IElectronicoService {

    fun findAll() : MutableList<Electronico>

}