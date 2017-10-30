package com.quijotelui.repository

import com.quijotelui.model.Contribuyente

interface IContribuyenteDao {

    fun findAll() : MutableList<Contribuyente>
    fun findByRuc(ruc : String) : MutableList<Contribuyente>
}