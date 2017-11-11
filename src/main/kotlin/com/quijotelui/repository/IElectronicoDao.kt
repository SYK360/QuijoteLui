package com.quijotelui.repository

import com.quijotelui.model.Electronico

interface IElectronicoDao {

    fun findAll() : MutableList<Electronico>
    fun findById(id : Long) : Electronico
    fun saveElectronico(electronico : Electronico)
    fun updateElectronico(electronico : Electronico)

}