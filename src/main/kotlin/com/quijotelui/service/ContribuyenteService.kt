package com.quijotelui.service

import com.quijotelui.model.Contribuyente

interface ContribuyenteService {
    fun getContribuyentes() : MutableList<Contribuyente>

}