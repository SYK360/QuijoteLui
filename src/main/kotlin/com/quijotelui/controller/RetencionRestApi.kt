package com.quijotelui.controller

import com.quijotelui.model.Retencion
import com.quijotelui.service.IRetencionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/v1")
class RetencionRestApi {

    @Autowired
    lateinit var retencionService: IRetencionService

    @GetMapping("/retenciones")
    fun getRetenciones(): ResponseEntity<MutableList<Retencion>> {
        val retenciones = retencionService.findAll()
        return ResponseEntity<MutableList<Retencion>>(retenciones, HttpStatus.OK)
    }

    @GetMapping("/retencion/codigo/{codigo}/numero/{numero}")
    fun getRetencion(@PathVariable(value = "codigo") codigo: String,
                     @PathVariable(value = "numero") numero: String): ResponseEntity<MutableList<Any>> {

        val retencion = retencionService.findByComprobante(codigo, numero)

        return ResponseEntity<MutableList<Any>>(retencion, HttpStatus.OK)
    }

}