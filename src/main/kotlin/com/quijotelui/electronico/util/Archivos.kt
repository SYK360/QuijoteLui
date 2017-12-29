package com.quijotelui.electronico.util

import java.io.File

class Archivos {

    companion object {
        fun esArchivo(ruta : String): Boolean {

            val f = File(ruta)
            if (!f.isFile) {
                println("Archivo no encontrado: $ruta")
                return false
            }
            return true

        }

        fun esDirectorio(ruta : String): Boolean {

            val f = File(ruta)
            if (!f.exists() && !f.isDirectory) {
                println("Directorio no encontrado: $ruta")
                return false
            }
            return true

        }
    }
}

//fun main(args: Array<String>) {
//
//    println("Existe: " + Archivos.esArchivo("/data/Instaladores/tmp/jorge_luis_quiguango_teran.p12"))
//
//    println("Existe: " + Archivos.esDirectorio("/data/Instaladores/tmp"))
//}