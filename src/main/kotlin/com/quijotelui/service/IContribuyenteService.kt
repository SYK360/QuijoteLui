package com.quijotelui.service

import com.quijotelui.model.Contribuyente

interface IContribuyenteService {

    fun findAll() : MutableList<Contribuyente>
    fun findByRUC(ruc : String) : Contribuyente

}