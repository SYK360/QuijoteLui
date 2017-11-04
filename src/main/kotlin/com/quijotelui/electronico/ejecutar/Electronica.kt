package com.quijotelui.electronico.ejecutar
import com.firmador.XAdESBESSignature
import com.quijotelui.electronico.util.Parametros
import com.quijotelui.electronico.xml.GeneraFactura
import com.quijotelui.service.IFacturaService
import java.io.File

class Electronica(val codigo : String, val numero : String){

    var claveAcceso : String? = null
    private var facturaService : IFacturaService? = null

    constructor(facturaService : IFacturaService, codigo : String, numero : String) : this(codigo, numero){
        this.facturaService = facturaService
    }

    fun generarFactura() {
        val genera = GeneraFactura(this.facturaService!!, codigo, numero)
        this.claveAcceso = genera.xml()

        firmar()
    }

    fun firmar(){
        val xadesBesFirma = XAdESBESSignature()
        val rutaGenerado = Parametros.getRuta(facturaService!!.findParametroByNombre("Generado"))
        val rutaFirmado = Parametros.getRuta(facturaService!!.findParametroByNombre("Firmado"))
        val rutaFirmaElectronica = Parametros.getRuta(facturaService!!.findParametroByNombre("Firma Electrónica"))
        val firmaElectronica = Parametros.getClaveElectronica(facturaService!!.findParametroByNombre("Clave Firma Electrónica"))


        xadesBesFirma.firmar("$rutaGenerado" +
                "${File.separatorChar}" +
                "${this.claveAcceso}.xml","${this.claveAcceso}.xml",
                "$rutaFirmado",
                "$rutaFirmaElectronica",
                "$firmaElectronica")
    }
}