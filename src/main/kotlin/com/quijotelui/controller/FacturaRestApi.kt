package com.quijotelui.controller

import com.quijotelui.electronico.ejecutar.Electronica
import com.quijotelui.electronico.util.TipoComprobante
import com.quijotelui.model.Factura
import com.quijotelui.service.IElectronicoService
import com.quijotelui.service.IFacturaService
import com.quijotelui.service.IInformacionService
import com.quijotelui.service.IParametroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit


@RestController
@RequestMapping("/rest/v1")
class FacturaRestApi {

    @Autowired
    lateinit var facturaService : IFacturaService

    @Autowired
    lateinit var parametroService : IParametroService

    @Autowired
    lateinit var electronicoService : IElectronicoService

    @Autowired
    lateinit var informacionService : IInformacionService

    @GetMapping("/facturas")
    fun getFacturas() : ResponseEntity<MutableList<Factura>> {
        val factura = facturaService.findAll()
        return ResponseEntity<MutableList<Factura>>(factura, HttpStatus.OK)
    }

    @GetMapping("/facturaFecha/{fecha}")
    fun getByFecha(@PathVariable(value = "fecha") fecha : String) : ResponseEntity<MutableList<Factura>> {

        val factura = facturaService.findByFecha(fecha)
        return ResponseEntity<MutableList<Factura>>(factura, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/facturaFechas/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun getByFechas(@PathVariable(value = "fechaInicio") fechaInicio : String,
                    @PathVariable(value = "fechaFin") fechaFin : String) : ResponseEntity<MutableList<Factura>> {

        val factura = facturaService.findByFechas(fechaInicio, fechaFin)
        return ResponseEntity<MutableList<Factura>>(factura, HttpStatus.OK)
    }

    /*
    Genera, firma y envía el comprobante electrónico
     */
    @GetMapping("/facturaEnviar/codigo/{codigo}/numero/{numero}")
    fun enviaXml(@PathVariable(value = "codigo") codigo : String,
                 @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<Factura>> {

        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        else {
            val factura = facturaService.findByComprobante(codigo, numero)

            if (factura.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            } else {
                val genera = Electronica(facturaService, codigo, numero, parametroService, electronicoService)

                genera.enviar(TipoComprobante.FACTURA)
                return ResponseEntity<MutableList<Factura>>(factura, HttpStatus.OK)
            }
        }
    }

    /*
    Autoriza el comprobante electrónico
    */
    @GetMapping("/factura_autorizar/codigo/{codigo}/numero/{numero}")
    fun autorizarXml(@PathVariable(value = "codigo") codigo : String, @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<Factura>> {

        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        else {
            val factura = facturaService.findByComprobante(codigo, numero)

            if (factura.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            } else {
                val genera = Electronica(facturaService, codigo, numero, parametroService, electronicoService)

                genera.comprobar(informacionService, TipoComprobante.FACTURA)
                return ResponseEntity<MutableList<Factura>>(factura, HttpStatus.OK)
            }
        }
    }

    /*
    Envía y Autoriza el comprobante electrónico
    */
    @CrossOrigin(value = "*")
    @GetMapping("/factura_procesar/codigo/{codigo}/numero/{numero}")
    fun procesarXml(@PathVariable(value = "codigo") codigo : String,
                    @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<Any>> {

        if (codigo == null || numero == null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        else {
            val factura = facturaService.findByComprobante(codigo, numero)

            if (factura.isEmpty()) {
                return ResponseEntity(HttpStatus.NOT_FOUND)
            } else {
                val genera = Electronica(facturaService, codigo, numero, parametroService, electronicoService)

                genera.enviar(TipoComprobante.FACTURA)

                println("Espere 3 segundos por favor hasta que el servicio del SRI autorice")
                TimeUnit.SECONDS.sleep(3)

                genera.comprobar(informacionService, TipoComprobante.FACTURA)

                val estado = facturaService.findEstadoByComprobante(codigo, numero)

                return ResponseEntity<MutableList<Any>>(estado, HttpStatus.OK)
            }
        }
    }

    /*
    Estado del comprobante
    */

    @GetMapping("/estado_factura/codigo/{codigo}/numero/{numero}")
    fun getEstado(@PathVariable(value = "codigo") codigo : String,
                         @PathVariable(value = "numero") numero : String) : ResponseEntity<MutableList<Any>> {
        val factura = facturaService.findEstadoByComprobante(codigo, numero)
        return ResponseEntity<MutableList<Any>>(factura, HttpStatus.OK)
    }

}