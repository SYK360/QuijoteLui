package com.quijotelui.controller

import com.quijotelui.electronico.ejecutar.Electronica
import com.quijotelui.electronico.util.TipoComprobante
import com.quijotelui.model.Guia
import com.quijotelui.model.NotaCredito
import com.quijotelui.service.IElectronicoService
import com.quijotelui.service.INotaCreditoService
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
class NotaCreditoRestApi {

    @Autowired
    lateinit var notaCreditoService: INotaCreditoService

    @Autowired
    lateinit var parametroService : IParametroService

    @Autowired
    lateinit var electronicoService : IElectronicoService

    @GetMapping("/nota_credito/codigo/{codigo}/numero/{numero}")
    fun getNotaCredito(@PathVariable(value = "codigo") codigo: String,
                @PathVariable(value = "numero") numero: String): ResponseEntity<MutableList<Any>> {

        val guia = notaCreditoService.findContribuyenteByComprobante(codigo, numero)

        return ResponseEntity<MutableList<Any>>(guia, HttpStatus.OK)
    }

    /*
 *  Genera, firma y envía el comprobante electrónico
 */
    @GetMapping("/nota_credito_enviar/codigo/{codigo}/numero/{numero}")
    fun enviaXml(@PathVariable(value = "codigo") codigo : String,
                 @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<NotaCredito>> {

        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        else {
            val notaCredito = notaCreditoService.findByComprobante(codigo, numero)

            if (notaCredito.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }
            else {
                val genera = Electronica(notaCreditoService, codigo, numero, parametroService, electronicoService)

                genera.enviar(TipoComprobante.NOTA_CREDITO)
                return ResponseEntity<MutableList<NotaCredito>>(notaCredito, HttpStatus.OK)
            }
        }
    }

}