package com.quijotelui.controller

import com.quijotelui.electronico.util.Parametros
import com.quijotelui.electronico.xml.GeneraFactura
import com.quijotelui.electronico.xml.GeneraGuia
import com.quijotelui.electronico.xml.GeneraNotaCredito
import com.quijotelui.electronico.xml.GeneraRetencion
import com.quijotelui.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.nio.file.Paths
import java.nio.file.Files
import org.springframework.http.MediaType
import java.io.File

@RestController
@RequestMapping("/rest/v1")
class ArchivosRestApi {

    @Autowired
    lateinit var parametroService : IParametroService

    @Autowired
    lateinit var facturaService : IFacturaService

    @Autowired
    lateinit var retencionService : IRetencionService

    @Autowired
    lateinit var notaCreditoService : INotaCreditoService

    @Autowired
    lateinit var guiaService : IGuiaService

    @CrossOrigin(value = "*")
    @GetMapping("/pdf/codigo/{codigo}/numero/{numero}")
    fun getPDF(@PathVariable(value = "codigo") codigo : String,
                        @PathVariable(value = "numero") numero : String): ResponseEntity<ByteArray> {
        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        var claveAcceso = ""

        if (codigo == "FAC") {
            val factura = facturaService.findByComprobante(codigo, numero)

            if (factura.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }

            val genera = GeneraFactura(facturaService, codigo, numero)
            claveAcceso = genera.claveAcceso.toString()
        }
        else if (codigo == "RET") {
            val retencion = retencionService.findByComprobante(codigo, numero)

            if (retencion.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }

            val genera = GeneraRetencion(retencionService, codigo, numero)
            claveAcceso = genera.claveAcceso.toString()
        }
        else if (codigo == "DVC" || codigo == "NCC") {
            val notaCredito = notaCreditoService.findByComprobante(codigo, numero)

            if (notaCredito.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }

            val genera = GeneraNotaCredito(notaCreditoService, codigo, numero)
            claveAcceso = genera.claveAcceso.toString()
        }
        else if (codigo == "GUI") {
            val guia = guiaService.findByComprobante(codigo, numero)

            if (guia.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }

            val genera = GeneraGuia(guiaService, codigo, numero)
            claveAcceso = genera.claveAcceso.toString()
        }
        else {
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        println("Clave de Acceso del PDF:" + claveAcceso)
        val rutaPDF= Parametros.getRuta(parametroService.findByNombre("PDF"))

        try {
            val path = Paths.get("$rutaPDF${File.separatorChar}${claveAcceso}.pdf")
            val f = path.toFile()

            if (!f.exists()) {
                return ResponseEntity(HttpStatus.CONFLICT)
            }
            val archivo = Files.readAllBytes(path)
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(archivo)

        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity(HttpStatus.CONFLICT)
        }

    }

    @CrossOrigin(value = "*")
    @GetMapping("/xml/codigo/{codigo}/numero/{numero}")
    fun getXML(@PathVariable(value = "codigo") codigo : String,
               @PathVariable(value = "numero") numero : String): ResponseEntity<ByteArray> {
        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        var claveAcceso = ""
        if (codigo == "FAC") {
            val factura = facturaService.findByComprobante(codigo, numero)

            if (factura.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }

            val genera = GeneraFactura(facturaService, codigo, numero)
            claveAcceso = genera.claveAcceso.toString()
        }
        else if (codigo == "RET") {
            val retencion = retencionService.findByComprobante(codigo, numero)

            if (retencion.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }

            val genera = GeneraRetencion(retencionService, codigo, numero)
            claveAcceso = genera.claveAcceso.toString()
        }
        else if (codigo == "DVC" || codigo == "NCC") {
            val notaCredito = notaCreditoService.findByComprobante(codigo, numero)

            if (notaCredito.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }

            val genera = GeneraNotaCredito(notaCreditoService, codigo, numero)
            claveAcceso = genera.claveAcceso.toString()
        }
        else if (codigo == "GUI") {
            val guia = guiaService.findByComprobante(codigo, numero)

            if (guia.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }

            val genera = GeneraGuia(guiaService, codigo, numero)
            claveAcceso = genera.claveAcceso.toString()
        }
        else {
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        if (claveAcceso == "") {
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        println("Clave de Acceso del XML:" + claveAcceso)
        val rutaPDF= Parametros.getRuta(parametroService.findByNombre("Autorizado"))

        try {
            val path = Paths.get("$rutaPDF${File.separatorChar}${claveAcceso}.xml")
            val f = path.toFile()

            if (!f.exists()) {
                return ResponseEntity(HttpStatus.CONFLICT)
            }
            val archivo = Files.readAllBytes(path)
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(archivo)

        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity(HttpStatus.CONFLICT)
        }

    }

}