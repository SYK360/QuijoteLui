package com.quijotelui.electronico.util

import com.quijotelui.electronico.correo.ConfiguracionCorreo
import com.quijotelui.model.Parametro

class Parametros{

    companion object {
        fun getAmbiente(parametro: MutableList<Parametro>): String {
            if (parametro.isEmpty()) {
                return "No existe valor para el parámetro: Ambiente"
            } else if (parametro.size > 1) {
                return "Existen más de un valor para el parámetro: Ambiente"
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
                return "No existe valor para el parámetro: Emisión"
            } else if (parametro.size > 1) {
                return "Existen más de un valor para el parámetro: Emisión"
            } else {
                println("Emisión " + parametro[0].valor)
                if (parametro[0].valor == "Normal") {
                    return "1"
                }
            }
            return "El parámetro Emisión no fue encontrado"
        }

        fun getRuta(parametro: MutableList<Parametro>) : String {
            if (parametro.isEmpty()) {
                return "No existe valor para el parámetro: Ruta"
            } else if (parametro.size > 1) {
                return "Existen más de un valor para el parámetro: Ruta"
            } else {
                println("Ruta ${parametro[0].nombre} ${parametro[0].valor}"  )
                return parametro[0].valor.toString()

            }
            return "El parámetro Ruta no fue encontrado"
        }

        fun getClaveElectronica(parametro: MutableList<Parametro>) : String {

            if (parametro.isEmpty()) {
                return "No existe valor para el parámetro: Clave Firma Electrónica"
            } else if (parametro.size > 1) {
                return "Existen más de un valor para el parámetro: Clave Firma Electrónica"
            } else {
//                println("Clave Firma Electrónica ${parametro[0].nombre} ${parametro[0].valor}"  )
                val claveFirmaElectronica : String = parametro[0].valor.toString()
                return Encriptar.decrypt(claveFirmaElectronica)
            }
            return "El parámetro Clave Firma Electrónica no fue encontrado"
        }

        fun getDatosCorreo(parametro: MutableList<Parametro>) : ConfiguracionCorreo {
            var servidor = ""
            var puerto = 0
            var correo = ""
            var clave = ""

            if (parametro.isEmpty()) {
                return ConfiguracionCorreo(servidor, puerto, correo, clave)
            }
            else {
                for (i in parametro.indices) {
                    if (parametro[i].nombre.toString() == "Servidor Correo"){
                        servidor = parametro[i].valor.toString()
                    }
                    if (parametro[i].nombre.equals("Puerto Servidor Correo")){
                        puerto = parametro[i].valor!!.toInt()
                    }
                    if (parametro[i].nombre.equals("Correo")){
                        correo = parametro[i].valor.toString()
                    }
                    if (parametro[i].nombre.equals("Clave Correo")){
                        clave = parametro[i].valor.toString()
                    }
                }
                println("Configuración Correo: $servidor $puerto $correo $clave")
                return ConfiguracionCorreo(servidor, puerto, correo, Encriptar.decrypt(clave))
            }
        }
    }

}
