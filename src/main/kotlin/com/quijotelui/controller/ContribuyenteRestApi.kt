package com.quijotelui.controller

import com.quijotelui.model.Contribuyente
import com.quijotelui.service.ContribuyenteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/v1")
class ContribuyenteRestApi {

    @Autowired
    lateinit var contribuyenteService: ContribuyenteService

    @GetMapping("/contribuyentes")
    fun findContribuyente() : ResponseEntity<MutableList<Contribuyente>> {
        val contribuyente = contribuyenteService.getContribuyentes()
        return ResponseEntity<MutableList<Contribuyente>>(contribuyente,HttpStatus.OK)
    }
}