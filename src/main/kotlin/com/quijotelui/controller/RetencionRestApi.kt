package com.quijotelui.controller

import com.quijotelui.electronico.ejecutar.Electronica
import com.quijotelui.electronico.util.TipoComprobante
import com.quijotelui.model.Retencion
import com.quijotelui.service.IElectronicoService
import com.quijotelui.service.IInformacionService
import com.quijotelui.service.IParametroService
import com.quijotelui.service.IRetencionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/rest/v1")
class RetencionRestApi {

    @Autowired
    lateinit var retencionService: IRetencionService

    @Autowired
    lateinit var parametroService : IParametroService

    @Autowired
    lateinit var electronicoService : IElectronicoService

    @Autowired
    lateinit var informacionService : IInformacionService

    @GetMapping("/retenciones")
    fun getRetenciones(): ResponseEntity<MutableList<Retencion>> {
        val retenciones = retencionService.findAll()
        return ResponseEntity<MutableList<Retencion>>(retenciones, HttpStatus.OK)
    }

    @GetMapping("/retencion/codigo/{codigo}/numero/{numero}")
    fun getRetencion(@PathVariable(value = "codigo") codigo: String,
                     @PathVariable(value = "numero") numero: String): ResponseEntity<MutableList<Any>> {

        val retencion = retencionService.findContribuyenteByComprobante(codigo, numero)

        return ResponseEntity<MutableList<Any>>(retencion, HttpStatus.OK)
    }

    /*
     *  Genera, firma y envía el comprobante electrónico
     */
    @GetMapping("/retencion_enviar/codigo/{codigo}/numero/{numero}")
    fun enviaXml(@PathVariable(value = "codigo") codigo : String,
                 @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<Retencion>> {

        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        else {
            val retencion = retencionService.findByComprobante(codigo, numero)

            if (retencion.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            }
            else {
                val genera = Electronica(retencionService, codigo, numero, parametroService, electronicoService)

                genera.enviar(TipoComprobante.RETENCION)
                return ResponseEntity<MutableList<Retencion>>(retencion, HttpStatus.OK)
            }
        }
    }

    /*
    Envía y Autoriza el comprobante electrónico
    */
    @CrossOrigin(value = "*")
    @GetMapping("/retencion_procesar/codigo/{codigo}/numero/{numero}")
    fun procesarXml(@PathVariable(value = "codigo") codigo : String,
                    @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<Any>> {

        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        else {
            val factura = retencionService.findByComprobante(codigo, numero)

            if (factura.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            } else {
                val genera = Electronica(retencionService, codigo, numero, parametroService, electronicoService)

                genera.enviar(TipoComprobante.RETENCION)

                println("Espere 3 segundos por favor hasta que el servicio del SRI autorice")
                TimeUnit.SECONDS.sleep(3)

                genera.comprobar(informacionService, TipoComprobante.RETENCION)

                val estado = retencionService.findEstadoByComprobante(codigo, numero)

                return ResponseEntity<MutableList<Any>>(estado, HttpStatus.OK)
            }
        }
    }
    /*
    Estado del comprobante
    */
    @GetMapping("/estado_retencion/codigo/{codigo}/numero/{numero}")
    fun getEstado(@PathVariable(value = "codigo") codigo : String,
                         @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<Any>> {
        val retencion = retencionService.findEstadoByComprobante(codigo, numero)
        return ResponseEntity<MutableList<Any>>(retencion, HttpStatus.OK)
    }

}