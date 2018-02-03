package com.quijotelui.controller

import com.quijotelui.electronico.ejecutar.Electronica
import com.quijotelui.electronico.util.TipoComprobante
import com.quijotelui.model.NotaCredito
import com.quijotelui.service.IElectronicoService
import com.quijotelui.service.IInformacionService
import com.quijotelui.service.INotaCreditoService
import com.quijotelui.service.IParametroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/rest/v1")
class NotaCreditoRestApi {

    @Autowired
    lateinit var notaCreditoService: INotaCreditoService

    @Autowired
    lateinit var parametroService : IParametroService

    @Autowired
    lateinit var electronicoService : IElectronicoService

    @Autowired
    lateinit var informacionService : IInformacionService

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

    /*
    Envía y Autoriza el comprobante electrónico
    */
    @CrossOrigin(value = "*")
    @GetMapping("/nota_credito_procesar/codigo/{codigo}/numero/{numero}")
    fun procesarXml(@PathVariable(value = "codigo") codigo : String,
                    @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<Any>> {

        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        else {
            val notaCredito = notaCreditoService.findByComprobante(codigo, numero)

            if (notaCredito.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            } else {
                val genera = Electronica(notaCreditoService, codigo, numero, parametroService, electronicoService)

                genera.enviar(TipoComprobante.NOTA_CREDITO)

                println("Espere 3 segundos por favor")
                TimeUnit.SECONDS.sleep(3)

                genera.comprobar(informacionService, TipoComprobante.NOTA_CREDITO)

                val estado = notaCreditoService.findEstadoByComprobante(codigo, numero)

                return ResponseEntity<MutableList<Any>>(estado, HttpStatus.OK)
            }
        }
    }
    /*
    Estado del comprobante
    */
    @GetMapping("/estado_nota_credito/codigo/{codigo}/numero/{numero}")
    fun getEstado(@PathVariable(value = "codigo") codigo : String,
                  @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<Any>> {
        val notaCredito = notaCreditoService.findEstadoByComprobante(codigo, numero)
        return ResponseEntity<MutableList<Any>>(notaCredito, HttpStatus.OK)
    }
}