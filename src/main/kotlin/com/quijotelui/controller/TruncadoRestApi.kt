package com.quijotelui.controller

import com.quijotelui.model.Truncado
import com.quijotelui.service.ITruncadoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/v1")
class TruncadoRestApi {

    @Autowired
    lateinit var truncadoService: ITruncadoService

    @CrossOrigin(value = "*")
    @GetMapping("/pendientes")
    fun getTruncados() : ResponseEntity<MutableList<Truncado>> {
        val truncado = truncadoService.findAll()
        return ResponseEntity(truncado, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/contar_pendientes")
    fun count() : ResponseEntity<MutableList<Any>> {
        val c = truncadoService.count()
        return ResponseEntity<MutableList<Any>>(c, HttpStatus.OK)
    }

}