package com.quijotelui.controller.util

object Extraer {

    //Extrae el número de una lista de un solo número usada para el count()
    fun numero(lista1SoloNumero: MutableList<Any>) : Long {
        return lista1SoloNumero.indices
                .firstOrNull()
                ?.let { lista1SoloNumero[it] as Long }
                ?: 0
    }
}