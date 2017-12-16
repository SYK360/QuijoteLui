package com.quijotelui.electronico.util

import java.text.SimpleDateFormat
import java.util.Date

class Fechas {

    fun toDate(fecha : String) : Date {

        val simpleDateFormatInicio = SimpleDateFormat("yyyy-MM-dd")
        val fechaInDateType : Date
        fechaInDateType = simpleDateFormatInicio.parse(fecha)

        return fechaInDateType
    }
}