package com.quijotelui.electronico.ejecutar

import com.quijotelui.clientews.Comprobar
import com.quijotelui.clientews.Enviar
import com.quijotelui.firmador.XAdESBESSignature
import com.quijotelui.electronico.util.Parametros
import com.quijotelui.electronico.xml.GeneraFactura
import com.quijotelui.service.IFacturaService
import com.quijotelui.service.IParametroService
import java.io.File
import java.util.concurrent.TimeUnit

class Electronica(val codigo : String, val numero : String, val parametroService : IParametroService) {

    var claveAcceso : String? = null
    private var facturaService : IFacturaService? = null

    constructor(facturaService : IFacturaService, codigo : String, numero : String, parametroService : IParametroService)
            : this(codigo, numero, parametroService) {
        this.facturaService = facturaService
    }

    fun generarFactura() {
        val genera = GeneraFactura(this.facturaService!!, codigo, numero)
        this.claveAcceso = genera.xml()

        firmar()
        enviar()

        println("Espere 10 segundos por favor")
        TimeUnit.SECONDS.sleep(10)

        comprobar()
    }

    fun firmar() {
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

    fun enviar() {
        val rutaFirmado = Parametros.getRuta(parametroService.findByNombre("Firmado"))
        val rutaEnviado= Parametros.getRuta(parametroService.findByNombre("Enviado"))
        val rutaRechazado= Parametros.getRuta(parametroService.findByNombre("Rechazado"))
        val direccionWebServiceEnviado = Parametros.getRuta(parametroService.findByNombre("Web Service Recepción"))

        val enviar = Enviar(rutaFirmado + File.separatorChar + this.claveAcceso + ".xml",
                rutaEnviado,
                rutaRechazado,
                direccionWebServiceEnviado)

        val respuesta = checkNotNull(enviar.executeEnviar()){
            return
        }

        println("Estado del comprobante ${this.claveAcceso} : ${respuesta.estado}")
    }

    fun comprobar() {
        val rutaEnviado= Parametros.getRuta(parametroService.findByNombre("Enviado"))
        val rutaAutorizado= Parametros.getRuta(parametroService.findByNombre("Autorizado"))
        val rutaNoAutorizado= Parametros.getRuta(parametroService.findByNombre("NoAutorizado"))
        val direccionWebServiceAutorizacion = Parametros.getRuta(parametroService.findByNombre("Web Service Autorización"))

        val comprobar = Comprobar(rutaEnviado + File.separatorChar + this.claveAcceso + ".xml",
                rutaAutorizado,
                rutaNoAutorizado,
                direccionWebServiceAutorizacion)

        val autorizacion = checkNotNull(comprobar.executeComprobar()){
            return
        }

        println("Estado del comprobante ${this.claveAcceso} : ${autorizacion.estadoAutorizacion.descripcion}")
    }
}