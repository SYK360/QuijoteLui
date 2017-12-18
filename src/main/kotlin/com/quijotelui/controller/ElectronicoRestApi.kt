package com.quijotelui.controller

import com.quijotelui.model.Electronico
import com.quijotelui.service.IElectronicoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/v1")
class ElectronicoRestApi {

    @Autowired
    lateinit var electronicoService : IElectronicoService

    @CrossOrigin(value = "*")
    @GetMapping("/electronico/codigo/{codigo}/numero/{numero}")
    fun getElectronico(@PathVariable(value = "codigo") codigo : String,
                       @PathVariable(value = "numero") numero : String)
            : ResponseEntity<MutableList<Electronico>> {

        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        val electronico = electronicoService.findByComprobante(codigo, numero)

        if (electronico.isEmpty()) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        return ResponseEntity<MutableList<Electronico>>(electronico, HttpStatus.OK)
    }

}