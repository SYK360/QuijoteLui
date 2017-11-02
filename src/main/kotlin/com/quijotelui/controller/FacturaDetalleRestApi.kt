package com.quijotelui.controller

import com.quijotelui.model.FacturaDetalle
import com.quijotelui.service.IFacturaDetalleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/v1")
class FacturaDetalleRestApi {

    @Autowired
    lateinit var facturaDetalleService: IFacturaDetalleService

    @GetMapping("/facturadetalle/codigo/{codigo}/numero/{numero}")
    fun getFacturaDetalle(@PathVariable(value = "codigo") codigo: String, @PathVariable(value = "numero") numero: String): ResponseEntity<MutableList<FacturaDetalle>> {
        val facturaDetalle = facturaDetalleService.findByComprobante(codigo, numero)

        return ResponseEntity<MutableList<FacturaDetalle>>(facturaDetalle, HttpStatus.OK)

    }

    @GetMapping("/facturasdetalle")
    fun getFacturasDetalle() : ResponseEntity<MutableList<FacturaDetalle>> {
        val facturasDetalle = facturaDetalleService.findAll()

        return ResponseEntity<MutableList<FacturaDetalle>>(facturasDetalle, HttpStatus.OK)
    }
}