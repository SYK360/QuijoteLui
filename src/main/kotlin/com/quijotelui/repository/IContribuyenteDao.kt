package com.quijotelui.repository

import com.quijotelui.model.Contribuyente

interface IContribuyenteDao {

    fun findAll() : MutableList<Contribuyente>
    fun findByRUC(ruc : String) : Contribuyente
}