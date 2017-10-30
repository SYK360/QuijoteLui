package com.quijotelui.controller

import com.quijotelui.model.Contribuyente
import com.quijotelui.service.IContribuyenteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/v1")
class ContribuyenteRestApi {

    @Autowired
    lateinit var contribuyenteService: IContribuyenteService

    @GetMapping("/contribuyentes")
    fun getContribuyentes() : ResponseEntity<MutableList<Contribuyente>> {
        val contribuyente = contribuyenteService.findAll()
        return ResponseEntity<MutableList<Contribuyente>>(contribuyente, HttpStatus.OK)
    }

    @GetMapping("/contribuyente/{ruc}")
    fun getByRuc(@PathVariable(value = "ruc") ruc : String) : ResponseEntity<MutableList<Contribuyente>> {
        println("Ruc: $ruc")
        val contribuyente = contribuyenteService.findByRuc(ruc)
        return ResponseEntity<MutableList<Contribuyente>>(contribuyente, HttpStatus.OK)
    }
}