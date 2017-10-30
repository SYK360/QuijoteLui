package com.quijotelui.controller

import com.quijotelui.model.Parametro
import com.quijotelui.service.IParametroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/v1")
class ParametroRestApi{

    @Autowired
    lateinit var parametroService : IParametroService

    @GetMapping("/parametros")
    fun getParametros() : ResponseEntity<MutableList<Parametro>> {

        val parametro = parametroService.findAll()
        return ResponseEntity<MutableList<Parametro>>(parametro, HttpStatus.OK)

    }

    @GetMapping("/parametro/{nombre}")
    fun getByFecha(@PathVariable(value = "nombre") nombre : String) : ResponseEntity<MutableList<Parametro>> {

        val parametro = parametroService.findByNombre(nombre)
        return ResponseEntity<MutableList<Parametro>>(parametro, HttpStatus.OK)

    }

}