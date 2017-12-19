package com.quijotelui.repository

import com.quijotelui.model.Informacion

interface IInformacionDao {

    fun findByDocumento(documento : String) : MutableList<Informacion>
    fun correoByDocumento(documento : String) : MutableList<Informacion>

}