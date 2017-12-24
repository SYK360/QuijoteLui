package com.quijotelui.controller

import com.quijotelui.electronico.ejecutar.Electronica
import com.quijotelui.model.ReporteFactura
import com.quijotelui.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/rest/v1")
class ReporteFacturaRestApi {

    @Autowired
    lateinit var reporteFacturaService : IReporteFacturaService

    @Autowired
    lateinit var facturaService : IFacturaService

    @Autowired
    lateinit var parametroService : IParametroService

    @Autowired
    lateinit var electronicoService : IElectronicoService

    @Autowired
    lateinit var informacionService : IInformacionService


    @CrossOrigin(value = "*")
    @GetMapping("/reporte_factura/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun getFacturasByFechas(@PathVariable(value = "fechaInicio") fechaInicio : String,
                    @PathVariable(value = "fechaFin") fechaFin : String)
            : ResponseEntity<MutableList<ReporteFactura>> {

        val reporteFactura = reporteFacturaService.findByFechas(fechaInicio, fechaFin)
        return ResponseEntity<MutableList<ReporteFactura>>(reporteFactura, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/reporte_factura_estado/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}/estado/{estado}")
    fun getFacturasByFechasEstado(@PathVariable(value = "fechaInicio") fechaInicio : String,
                          @PathVariable(value = "fechaFin") fechaFin : String,
                          @PathVariable(value = "estado") estado : String)
            : ResponseEntity<MutableList<ReporteFactura>> {

        val reporteFactura = reporteFacturaService.findByFechasEstado(fechaInicio, fechaFin, estado)
        return ResponseEntity<MutableList<ReporteFactura>>(reporteFactura, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/facturaAutorizarFechas/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun autorizarFacturas(@PathVariable(value = "fechaInicio") fechaInicio : String,
                        @PathVariable(value = "fechaFin") fechaFin : String) : ResponseEntity<MutableList<ReporteFactura>> {

        var factura = reporteFacturaService.findByFechasEstado(
                fechaInicio,
                fechaFin,
                "NoAutorizados")

        println("Facturas entre: $fechaInicio y  $fechaFin")
        if (factura.size > 0) {
            for (i in factura.indices) {
                val row = factura.get(i)
                println("$i - ${row.codigo} ${row.numero}")

                val factura = facturaService.findByComprobante(row.codigo.toString(), row.numero.toString())

                if (!factura.isEmpty()) {
                    val genera = Electronica(facturaService,
                            row.codigo.toString(),
                            row.numero.toString(),
                            parametroService,
                            electronicoService)

                    genera.enviarFactura()

                    println("Espere 3 segundos por favor")
                    TimeUnit.SECONDS.sleep(3)

                    genera.comprobarFactura(informacionService)
                }
            }
        }

        factura.clear()

        factura = reporteFacturaService.findByFechas(fechaInicio, fechaFin)
        if (factura.size > 0) {
            println("Estado de facturas")
            for (i in factura.indices) {
                val row = factura.get(i)
                println("$i - ${row.codigo} ${row.numero} ${row.estado}")
            }
        }

        return ResponseEntity<MutableList<ReporteFactura>>(factura, HttpStatus.OK)
    }
}