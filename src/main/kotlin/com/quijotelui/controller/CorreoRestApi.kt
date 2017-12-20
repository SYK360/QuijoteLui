package com.quijotelui.controller

import com.quijotelui.electronico.correo.EnviarCorreo
import com.quijotelui.model.Informacion
import com.quijotelui.service.IFacturaService
import com.quijotelui.service.IInformacionService
import com.quijotelui.service.IParametroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/v1")
class CorreoRestApi {

    @Autowired
    lateinit var informacionService : IInformacionService

    @Autowired
    lateinit var parametroService : IParametroService

    @Autowired
    lateinit var facturaService : IFacturaService

    @CrossOrigin(value = "*")
    @GetMapping("/correo/codigo/{codigo}/numero/{numero}")
    fun enviaArchivos(@PathVariable(value = "codigo") codigo : String,
                 @PathVariable(value = "numero") numero : String)
            : ResponseEntity<MutableList<Informacion>> {

        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        var correo : EnviarCorreo? = null

        if (codigo == "FAC") {
            correo = EnviarCorreo(codigo, numero, parametroService, informacionService, facturaService)
        }

        return correo!!.enviar()
    }
}