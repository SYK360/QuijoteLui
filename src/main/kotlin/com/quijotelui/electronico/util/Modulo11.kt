package com.quijotelui.electronico.util

class Modulo11 {

    private fun invertirCadena(cadena: String): String {
        var cadenaInvertida = ""
        for (x in cadena.length - 1 downTo 0) {
            cadenaInvertida = cadenaInvertida + cadena[x]
        }
        return cadenaInvertida
    }

    private fun obtenerSumaPorDigitos(cadena: String): Int {

        var pivote = 2
        val longitudCadena = cadena.length
        var cantidadTotal = 0
        var b = 1
        for (i in 0 until longitudCadena) {
            if (pivote == 8) {
                pivote = 2
            }
            var temporal = Integer.parseInt("" + cadena.substring(i, b))
            b++
            temporal = temporal * pivote
            pivote++
            cantidadTotal = cantidadTotal + temporal
        }
        cantidadTotal = 11 - cantidadTotal % 11
        if (cantidadTotal == 10) {
            cantidadTotal = 1
        }
        if (cantidadTotal == 11) {
            cantidadTotal = 0
        }
        return cantidadTotal

    }

    fun modulo11(cadena: String): Int {
        println("modulo11: " + cadena)
        return obtenerSumaPorDigitos(invertirCadena(cadena))
    }

}

/*fun main(args: Array<String>) {
    val a = Modulo11()
    println("Cadena: 2310201402100160804900110010011123456781")
    println("Resultado :" + a.modulo11("2310201402100160804900110010011123456781"))

}*/
