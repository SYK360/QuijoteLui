package com.quijotelui.electronico.util

import com.quijotelui.model.Parametro

class Parametros{

    companion object {
        fun getAmbiente(parametro: MutableList<Parametro>): String {
            if (parametro.isEmpty()) {
                return "No existe valor para el parámetro Ambiente"
            } else if (parametro.size > 1) {
                return "Existen más de un valor para el parámetro Ambiente"
            } else {
                println("Ambiente " + parametro[0].valor)
                if (parametro[0].valor == "Pruebas") {
                    return "1"
                } else if (parametro[0].valor == "Producción") {
                    return "2"
                }
            }
            return "El parámetro Ambiente no fue encontrado"
        }

        fun getEmision(parametro: MutableList<Parametro>) : String {
            if (parametro.isEmpty()) {
                return "No existe valor para el parámetro Emisión"
            } else if (parametro.size > 1) {
                return "Existen más de un valor para el parámetro Emisión"
            } else {
                println("Emisión " + parametro[0].valor)
                if (parametro[0].valor == "Normal") {
                    return "1"
                }
            }
            return "El parámetro Ambiente no fue encontrado"
        }
    }

}