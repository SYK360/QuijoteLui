package com.quijotelui.repository

import com.quijotelui.model.Contribuyente

interface ContribuyenteDao {

    fun getContribuyentes() : MutableList<Contribuyente>
}