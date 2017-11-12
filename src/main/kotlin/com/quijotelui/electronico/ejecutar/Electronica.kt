package com.quijotelui.electronico.ejecutar

import com.quijotelui.electronico.xml.GeneraFactura
import com.quijotelui.model.Electronico
import com.quijotelui.service.IElectronicoService
import com.quijotelui.service.IFacturaService
import com.quijotelui.service.IParametroService
import com.quijotelui.ws.definicion.AutorizacionEstado
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud
import ec.gob.sri.comprobantes.ws.Comprobante
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class Electronica(val codigo : String, val numero : String, val parametroService : IParametroService) {

    var claveAcceso : String? = null
    private var facturaService : IFacturaService? = null
    private var electronicoService : IElectronicoService? = null

    constructor(facturaService : IFacturaService,
                codigo : String,
                numero : String,
                parametroService : IParametroService,
                electronicoService : IElectronicoService)
            : this(codigo, numero, parametroService) {
        this.facturaService = facturaService
        this.electronicoService = electronicoService
    }

    fun enviarFactura() {

        val genera = GeneraFactura(this.facturaService!!, this.codigo, this.numero)
        val procesar = ProcesarElectronica(parametroService)
        this.claveAcceso = genera.xml()

        procesar.firmar(this.claveAcceso!!)

        val respuesta = procesar.enviar(this.claveAcceso!!)
        respuesta?.let { grabarRespuestaEnvio(it) }

        procesar.imprimirFactura(this.claveAcceso!!,"","")

    }

    fun comprobarFactura() {
        val genera = GeneraFactura(this.facturaService!!, this.codigo, this.numero)
        this.claveAcceso = genera.claveAcceso

        val procesar = ProcesarElectronica(parametroService)

        val autorizacionEstado = procesar.comprobar(this.claveAcceso!!)

        grabarAutorizacion(autorizacionEstado)

        procesar.imprimirFactura(this.claveAcceso!!,
                autorizacionEstado.autorizacion.numeroAutorizacion,
                autorizacionEstado.autorizacion.fechaAutorizacion.toString())
    }

    private fun grabarRespuestaEnvio(respuesta : RespuestaSolicitud) {

        var comprobante: Comprobante
        var electronico = Electronico()
        val fecha = LocalDateTime.now()
        var mensaje = "$fecha |"

        if (respuesta.comprobantes.comprobante.size > 0) {
            for (i in respuesta.comprobantes.comprobante.indices) {
                comprobante = respuesta.comprobantes.comprobante[i] as ec.gob.sri.comprobantes.ws.Comprobante
                mensaje = mensaje + " " + comprobante.claveAcceso + ": "
                for (m in comprobante.mensajes.mensaje.indices) {
                    val mensajeRespuesta = comprobante.mensajes.mensaje[m]
                    mensaje = mensaje + " " + mensajeRespuesta.mensaje
                }
                mensaje += " "
            }
        }

        if (mensaje.equals("RECIBIDA")){
            mensaje = "$mensaje Conexión exitosa"
        }
        // Si no existe en la base de datos se inserta
        if (this.electronicoService!!.findByComprobante(this.codigo, this.numero).isEmpty()) {

            electronico.codigo = this.codigo
            electronico.numero = this.numero
            electronico.observacion = mensaje
            electronico.estado = respuesta.estado

            this.electronicoService?.saveElectronico(electronico)
        }
        // Si existe en la base de datos se actualiza
        else{
            var electronicoUpdate = this.electronicoService!!.findByComprobante(this.codigo, this.numero)

            for (e in electronicoUpdate.indices) {
                electronico = electronicoUpdate[e]
            }

            electronico.observacion = mensaje + " | " + electronico.observacion
            electronico.estado = respuesta.estado

            this.electronicoService!!.updateElectronico(electronico)
        }
    }

    private fun grabarAutorizacion(autorizacionEstado : AutorizacionEstado) {

        var electronico = Electronico()
        var fecha : String? = null

        // Si no existe en la base de datos se inserta
        if (this.electronicoService!!.findByComprobante(this.codigo, this.numero).isEmpty()) {

            electronico.codigo = this.codigo
            electronico.numero = this.numero
            electronico.observacion = " | ${autorizacionEstado.autorizacion.numeroAutorizacion} " +
                    "${autorizacionEstado.autorizacion.fechaAutorizacion} "
            electronico.numeroAutorizacion = autorizacionEstado.autorizacion.numeroAutorizacion
            electronico.estado = autorizacionEstado.autorizacion.estado


            fecha = autorizacionEstado.autorizacion.fechaAutorizacion.year.toString() + "-" +
                    autorizacionEstado.autorizacion.fechaAutorizacion.month.toString() +  "-" +
                    autorizacionEstado.autorizacion.fechaAutorizacion.day.toString() + " " +
                    autorizacionEstado.autorizacion.fechaAutorizacion.hour.toString() + ":" +
                    autorizacionEstado.autorizacion.fechaAutorizacion.minute.toString()

            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val fechaInDateType : Date
            fechaInDateType = simpleDateFormat.parse(fecha)
            println("Fecha autorización: $fechaInDateType")

            electronico.fechaAutorizacion = fechaInDateType

            this.electronicoService!!.saveElectronico(electronico)

        }
        // Si existe en la base de datos se actualiza
        else {
            var electronicoUpdate = this.electronicoService!!.findByComprobante(this.codigo, this.numero)

            for (e in electronicoUpdate.indices) {
                electronico = electronicoUpdate[e]
            }

            electronico.observacion = " | ${autorizacionEstado.autorizacion.numeroAutorizacion} " +
                    "${autorizacionEstado.autorizacion.fechaAutorizacion} " + electronico.observacion
            electronico.numeroAutorizacion = autorizacionEstado.autorizacion.numeroAutorizacion

            fecha = autorizacionEstado.autorizacion.fechaAutorizacion.year.toString() + "-" +
                    autorizacionEstado.autorizacion.fechaAutorizacion.month.toString() +  "-" +
                    autorizacionEstado.autorizacion.fechaAutorizacion.day.toString() + " " +
                    autorizacionEstado.autorizacion.fechaAutorizacion.hour.toString() + ":" +
                    autorizacionEstado.autorizacion.fechaAutorizacion.minute.toString()

            electronico.estado = autorizacionEstado.autorizacion.estado

            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val fechaInDateType : Date
            fechaInDateType = simpleDateFormat.parse(fecha)
            println("Fecha autorización: $fechaInDateType")

            electronico.fechaAutorizacion = fechaInDateType

            this.electronicoService!!.updateElectronico(electronico)
        }
    }
}