package com.quijotelui.controller


import com.quijotelui.model.Factura
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
    fun getContribuyentes() : ResponseEntity<MutableList<Factura>> {
        val factura = facturaService.findAll()
        return ResponseEntity<MutableList<Factura>>(factura, HttpStatus.OK)
    }

    @GetMapping("/factura/{fecha}")
    fun getByRuc(@PathVariable(value = "fecha") fecha : String) : ResponseEntity<MutableList<Factura>> {

        val factura = facturaService.findByFecha(fecha)
        return ResponseEntity<MutableList<Factura>>(factura, HttpStatus.OK)
    }

}