package com.quijotelui.controller

import com.quijotelui.electronico.util.Parametros
import com.quijotelui.electronico.xml.GeneraFactura
import com.quijotelui.service.IFacturaService
import com.quijotelui.service.IParametroService
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

    @CrossOrigin(value = "*")
    @GetMapping("/pdf/codigo/{codigo}/numero/{numero}")
    fun getPDF(@PathVariable(value = "codigo") codigo : String,
                        @PathVariable(value = "numero") numero : String): ResponseEntity<ByteArray> {
        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        val factura = facturaService.findByComprobante(codigo, numero)

        if (factura.isEmpty()) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val genera = GeneraFactura(facturaService, codigo, numero)
        println("Clave de Acceso del PDF:" + genera.claveAcceso)
        val rutaPDF= Parametros.getRuta(parametroService.findByNombre("PDF"))

        try {
            val path = Paths.get("$rutaPDF${File.separatorChar}${genera.claveAcceso}.pdf")
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

        val factura = facturaService.findByComprobante(codigo, numero)

        if (factura.isEmpty()) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        val genera = GeneraFactura(facturaService, codigo, numero)
        println("Clave de Acceso del XML:" + genera.claveAcceso)
        val rutaPDF= Parametros.getRuta(parametroService.findByNombre("Autorizado"))

        try {
            val path = Paths.get("$rutaPDF${File.separatorChar}${genera.claveAcceso}.xml")
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