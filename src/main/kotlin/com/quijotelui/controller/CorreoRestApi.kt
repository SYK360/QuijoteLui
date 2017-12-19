package com.quijotelui.controller

import com.quijotelui.electronico.correo.EnviarCorreo
import com.quijotelui.model.Informacion
import com.quijotelui.service.IInformacionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.File

@RestController
@RequestMapping("/rest/v1")
class CorreoRestApi {

    @Autowired
    lateinit var informacionService : IInformacionService

    @CrossOrigin(value = "*")
    @GetMapping("/correo/{documento}")
    fun getContribuyentes(@PathVariable(value = "documento") documento : String)
            : ResponseEntity<MutableList<Informacion>> {

        val informacion = informacionService.correoByDocumento(documento)

        val correo = EnviarCorreo("smtp.googlemail.com", 465)

        correo.remitente("chaskiq.ecuador@gmail.com", "Gluc4g0n")
        correo.pdf(File("/app/Quijotelui/comprobante/pdf/" +
                "1310201701100245687700110010020000003441234567810.pdf"),"Factura PDF")
        correo.xml(File("/app/Quijotelui/comprobante/autorizado/" +
                "1310201701100245687700110010020000003451234567816.xml"), "Factura XML")
        correo.enviar("jorjoluiso@hotmail.com", "Prueba de Envío", "Hola")

        return ResponseEntity<MutableList<Informacion>>(informacion, HttpStatus.OK)
    }
}