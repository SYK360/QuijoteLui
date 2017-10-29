package com.quijotelui.controller


import com.quijotelui.electronico.xml.GeneraFactura
import com.quijotelui.model.Contribuyente
import com.quijotelui.model.Factura
import com.quijotelui.service.IContribuyenteService
import com.quijotelui.service.IFacturaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/rest/v1")
class FacturaRestApi {

    @Autowired
    lateinit var facturaService: IFacturaService

    @GetMapping("/facturas")
    fun getFacturas() : ResponseEntity<MutableList<Factura>> {
        val factura = facturaService.findAll()
        return ResponseEntity<MutableList<Factura>>(factura, HttpStatus.OK)
    }

    @GetMapping("/factura/{fecha}")
    fun getByFecha(@PathVariable(value = "fecha") fecha : String) : ResponseEntity<MutableList<Factura>> {

        val factura = facturaService.findByFecha(fecha)
        return ResponseEntity<MutableList<Factura>>(factura, HttpStatus.OK)
    }

    @GetMapping("/factura/codigo/{codigo}/numero/{numero}")
    fun generaXml(@PathVariable(value = "codigo") codigo : String, @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<Factura>> {

        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        else {
            val factura = facturaService.findByComprobante(codigo, numero)

            if (factura.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            } else {
                val genera = GeneraFactura()

                genera.genera()
                return ResponseEntity<MutableList<Factura>>(factura, HttpStatus.OK)
            }
        }

    }

    @GetMapping("/contribuyentefactura")
    fun getContribuyenteFactura() : ResponseEntity<MutableList<Any>> {
        val factura = facturaService.findContribuyenteByComprobante("FAC","001003003004626")
        return ResponseEntity<MutableList<Any>>(factura, HttpStatus.OK)
    }

}