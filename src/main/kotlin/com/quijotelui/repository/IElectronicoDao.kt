package com.quijotelui.repository

import com.quijotelui.model.Electronico

interface IElectronicoDao {

    fun findAll() : MutableList<Electronico>
    fun findById(id : Long) : Electronico
    fun findByComprobante(codigo : String, numero : String) : MutableList<Electronico>
    fun saveElectronico(electronico : Electronico)
    fun updateElectronico(electronico : Electronico)

}