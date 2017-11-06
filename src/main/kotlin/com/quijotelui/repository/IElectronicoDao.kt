package com.quijotelui.repository

import com.quijotelui.model.Electronico

interface IElectronicoDao {

    fun findAll() : MutableList<Electronico>

}