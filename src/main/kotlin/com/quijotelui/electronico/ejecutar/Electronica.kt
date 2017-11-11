package com.quijotelui.electronico.ejecutar

import com.quijotelui.clientews.Comprobar
import com.quijotelui.clientews.Enviar
import com.quijotelui.firmador.XAdESBESSignature
import com.quijotelui.electronico.util.Parametros
import com.quijotelui.electronico.xml.GeneraFactura
import com.quijotelui.model.Electronico
import com.quijotelui.printer.pdf.FacturaPDF
import com.quijotelui.service.IElectronicoService
import com.quijotelui.service.IFacturaService
import com.quijotelui.service.IParametroService
import com.quijotelui.ws.definicion.AutorizacionEstado
import ec.gob.sri.comprobantes.ws.RespuestaSolicitud
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import com.quijotelui.ws.definicion.Estado
import ec.gob.sri.comprobantes.ws.Comprobante
import ec.gob.sri.comprobantes.ws.Mensaje
import ec.gob.sri.comprobantes.ws.aut.Autorizacion




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

        val genera = GeneraFactura(this.facturaService!!, codigo, numero)
        this.claveAcceso = genera.xml()

        firmar()
        val respuesta = enviar()
        val electronico = Electronico()

        electronico.codigo = this.codigo
        electronico.numero = this.numero
        electronico.observacion = respuesta?.estado
        electronico.estado = respuesta?.estado

        electronicoService?.saveElectronico(electronico)

        imprimirFactura("","")

    }

    fun comprobarFactura() {

        comprobar()
        imprimirFactura("","")

    }

    private fun firmar() {
        val xadesBesFirma = XAdESBESSignature()
        val rutaGenerado = Parametros.getRuta(parametroService.findByNombre("Generado"))
        val rutaFirmado = Parametros.getRuta(parametroService.findByNombre("Firmado"))
        val rutaFirmaElectronica = Parametros.getRuta(parametroService.findByNombre("Firma Electrónica"))
        val firmaElectronica = Parametros.getClaveElectronica(parametroService.findByNombre("Clave Firma Electrónica"))


        xadesBesFirma.firmar("$rutaGenerado" + "${File.separatorChar}" + "${this.claveAcceso}.xml",
                "${this.claveAcceso}.xml",
                "$rutaFirmado",
                "$rutaFirmaElectronica",
                "$firmaElectronica")
    }

    private fun enviar(): RespuestaSolicitud? {

        var respuesta = RespuestaSolicitud()
        val direccionWebServiceEnviado = Parametros.getRuta(parametroService.findByNombre("Web Service Recepción"))

        if (!isWSDLAlive(direccionWebServiceEnviado)){
            var comprobante = Comprobante()
            comprobante.claveAcceso = this.claveAcceso
            var mensaje = Mensaje()
            mensaje.mensaje = "No existe conexión con el Web Service $direccionWebServiceEnviado"
            mensaje.tipo = "ERROR"
            comprobante.mensajes.mensaje.add(Mensaje())
            respuesta.comprobantes.comprobante.add(comprobante)
            respuesta.estado = "ERROR"
            return respuesta
        }

        val rutaFirmado = Parametros.getRuta(parametroService.findByNombre("Firmado"))
        val rutaEnviado= Parametros.getRuta(parametroService.findByNombre("Enviado"))
        val rutaRechazado= Parametros.getRuta(parametroService.findByNombre("Rechazado"))


        val enviar = Enviar(rutaFirmado + File.separatorChar + this.claveAcceso + ".xml",
                    rutaEnviado,
                    rutaRechazado,
                    direccionWebServiceEnviado)

        respuesta = enviar.executeEnviar()

        println("Estado del comprobante ${this.claveAcceso} : ${respuesta.estado}")

        return respuesta

    }

    private fun comprobar(): AutorizacionEstado {

        var autorizacionEstado = AutorizacionEstado(Autorizacion(), Estado.NO_AUTORIZADO)
        val direccionWebServiceAutorizacion = Parametros.getRuta(parametroService.findByNombre("Web Service Autorización"))

        if (!isWSDLAlive(direccionWebServiceAutorizacion)){
            return autorizacionEstado
        }

        val rutaEnviado= Parametros.getRuta(parametroService.findByNombre("Enviado"))
        val rutaAutorizado= Parametros.getRuta(parametroService.findByNombre("Autorizado"))
        val rutaNoAutorizado= Parametros.getRuta(parametroService.findByNombre("NoAutorizado"))


        val comprobar = Comprobar(rutaEnviado + File.separatorChar + this.claveAcceso + ".xml",
                rutaAutorizado,
                rutaNoAutorizado,
                direccionWebServiceAutorizacion)

        autorizacionEstado = comprobar.executeComprobar()

        println("Estado del comprobante ${this.claveAcceso} : ${autorizacionEstado.estadoAutorizacion.descripcion}")

        return autorizacionEstado
    }

    private fun imprimirFactura(autorizacion : String, fechaAutorizacion : String) {

        val rutaGenerado = Parametros.getRuta(parametroService.findByNombre("Generado"))
        val rutaReportes= Parametros.getRuta(parametroService.findByNombre("Reportes"))
        val logo= Parametros.getRuta(parametroService.findByNombre("Logo"))
        val rutaPDF= Parametros.getRuta(parametroService.findByNombre("PDF"))

        val pdf = FacturaPDF(rutaReportes, logo, rutaPDF)
        pdf.genera(rutaGenerado + File.separatorChar + this.claveAcceso + ".xml",
                autorizacion,
                fechaAutorizacion)
    }

    private fun isWSDLAlive(direccionWSDL : String) : Boolean {
        var c: HttpURLConnection? = null
        try {
            val u = URL(direccionWSDL)
            c = u.openConnection() as HttpURLConnection
            c.requestMethod = "GET"
            c.inputStream
            if (c.responseCode == 200) {
                return true
            }
        } catch (e: IOException) {
            println("Error de conexión con WSDL : " + e.message)
            return false
        } finally {
            if (c != null) {
                c.disconnect()
            }
        }
        return false
    }
}