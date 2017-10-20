package com.quijotelui.controller

import com.quijotelui.model.Contribuyente
import com.quijotelui.repository.ContribuyenteDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rest/v1")
class ContribuyenteRestApi {

    @Autowired
    lateinit var contribuyente : ContribuyenteDao

    @GetMapping("/contribuyentes")
    fun findContribuyente() : List<Contribuyente>{
        val c: Contribuyente
        c = contribuyente.findAll()[0]
        println(c.nombreComercial)
        return contribuyente.findAll()
    }
}