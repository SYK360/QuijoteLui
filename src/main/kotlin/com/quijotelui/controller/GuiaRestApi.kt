package com.quijotelui.controller

import com.quijotelui.electronico.ejecutar.Electronica
import com.quijotelui.model.Guia
import com.quijotelui.service.IElectronicoService
import com.quijotelui.service.IGuiaService
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
class GuiaRestApi {

    @Autowired
    lateinit var guiaService: IGuiaService

    @Autowired
    lateinit var parametroService : IParametroService

    @Autowired
    lateinit var electronicoService : IElectronicoService

    @GetMapping("/guia/codigo/{codigo}/numero/{numero}")
    fun getGuia(@PathVariable(value = "codigo") codigo: String,
                     @PathVariable(value = "numero") numero: String): ResponseEntity<MutableList<Any>> {

        val guia = guiaService.findContribuyenteByComprobante(codigo, numero)

        return ResponseEntity<MutableList<Any>>(guia, HttpStatus.OK)
    }

    /*
 *  Genera, firma y envía el comprobante electrónico
 */
    @GetMapping("/guiaEnviar/codigo/{codigo}/numero/{numero}")
    fun enviaXml(@PathVariable(value = "codigo") codigo : String,
                 @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<Guia>> {

        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        else {
            val guia = guiaService.findByComprobante(codigo, numero)

            if (guia.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }
            else {
                val genera = Electronica(guiaService, codigo, numero, parametroService, electronicoService)

                genera.enviarGuia()
                return ResponseEntity<MutableList<Guia>>(guia, HttpStatus.OK)
            }
        }
    }

}