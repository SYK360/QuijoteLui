package com.quijotelui.electronico.ejecutar

import com.firmador.XAdESBESSignature
import com.quijotelui.electronico.util.Parametros
import com.quijotelui.electronico.xml.GeneraFactura
import com.quijotelui.service.IFacturaService
import com.quijotelui.service.IParametroService
import java.io.File

class Electronica(val codigo : String, val numero : String, val parametroService : IParametroService){

    var claveAcceso : String? = null
    private var facturaService : IFacturaService? = null

    constructor(facturaService : IFacturaService, codigo : String, numero : String, parametroService : IParametroService)
            : this(codigo, numero, parametroService){
        this.facturaService = facturaService
    }

    fun generarFactura() {
        val genera = GeneraFactura(this.facturaService!!, codigo, numero)
        this.claveAcceso = genera.xml()

        firmar()
    }

    fun firmar(){
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
}