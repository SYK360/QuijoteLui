package com.quijotelui.service

import com.quijotelui.model.Informacion

interface IInformacionService {

    fun findByDocumento(documento : String) : MutableList<Informacion>

}