/*
Rest Api para manejar los comprobantes pendientes de enviar o autorizar.
La información se llena por un procedimiento almacenado, ejecutado en
un tarea programada.
*/

package com.quijotelui.controller

import com.quijotelui.controller.util.Extraer
import com.quijotelui.model.Truncado
import com.quijotelui.service.ITruncadoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMethod

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
    fun count() : ResponseEntity<Any> {
        val result = truncadoService.count()

        println("Contar Pendientes: ${Extraer.numero(result)}")

        return ResponseEntity(result, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @RequestMapping("/pendientes/{id}", method = [(RequestMethod.DELETE)])
    fun deleteTruncado(@PathVariable("id") id: Long): ResponseEntity<Any> {

        val existe = Extraer.numero(truncadoService.count(id)) > 0

//        println("Existe el $id, $existe")

        if (!existe) {
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }

        val truncado = truncadoService.findById(id)
        truncadoService.deleteById(truncado.id!!)

        return ResponseEntity(truncado,HttpStatus.OK)
    }
}
