package com.quijotelui.controller

import com.quijotelui.electronico.ejecutar.Electronica
import com.quijotelui.electronico.util.TipoComprobante
import com.quijotelui.model.ReporteNotaCredito
import com.quijotelui.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/rest/v1")
class ReporteNotaCreditoRestApi {

    @Autowired
    lateinit var reporteNotaCreditoService: IReporteNotaCreditoService

    @Autowired
    lateinit var notaCreditoService: INotaCreditoService

    @Autowired
    lateinit var parametroService : IParametroService

    @Autowired
    lateinit var electronicoService : IElectronicoService

    @Autowired
    lateinit var informacionService : IInformacionService

    @CrossOrigin(value = "*")
    @GetMapping("/reporte_nota_cedito/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun getNotaCreditoByFechas(@PathVariable(value = "fechaInicio") fechaInicio : String,
                               @PathVariable(value = "fechaFin") fechaFin : String)
            : ResponseEntity<MutableList<ReporteNotaCredito>> {

        val reporteNotaCreditoService = reporteNotaCreditoService.findByFechas(fechaInicio, fechaFin)
        return ResponseEntity<MutableList<ReporteNotaCredito>>(reporteNotaCreditoService, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/reporte_nota_credito_estado/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}/estado/{estado}")
    fun getNotaCreditoByFechasEstado(@PathVariable(value = "fechaInicio") fechaInicio : String,
                                     @PathVariable(value = "fechaFin") fechaFin : String,
                                     @PathVariable(value = "estado") estado : String)
            : ResponseEntity<MutableList<ReporteNotaCredito>> {

        val reporteNotaCredito= reporteNotaCreditoService.findByFechasEstado(fechaInicio, fechaFin, estado)
        return ResponseEntity<MutableList<ReporteNotaCredito>>(reporteNotaCredito, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/nota_credito_autoriza/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun autorizarNotaCredito(@PathVariable(value = "fechaInicio") fechaInicio : String,
                             @PathVariable(value = "fechaFin") fechaFin : String)
            : ResponseEntity<MutableList<ReporteNotaCredito>> {

        var reporteNotaCredito = reporteNotaCreditoService.findByFechasEstado(
                fechaInicio,
                fechaFin,
                "NoAutorizados")

        println("Notas de Crédito entre: $fechaInicio y  $fechaFin")
        if (reporteNotaCredito.size > 0) {
            for (i in reporteNotaCredito.indices) {
                val row = reporteNotaCredito[i]
                println("$i - ${row.codigo} ${row.numero} enviando")

                val notaCredito = notaCreditoService.findByComprobante(row.codigo.toString(), row.numero.toString())

                if (!notaCredito.isEmpty()) {
                    val genera = Electronica(notaCreditoService,
                            row.codigo.toString(),
                            row.numero.toString(),
                            parametroService,
                            electronicoService)

                    genera.enviar(TipoComprobante.NOTA_CREDITO)
                }
            }
            println("Espere 3 segundos para empezar la verificación")
            TimeUnit.SECONDS.sleep(3)
            for (i in reporteNotaCredito.indices) {
                val row = reporteNotaCredito[i]
                println("$i - ${row.codigo} ${row.numero} verificando")

                val notaCredito = notaCreditoService.findByComprobante(row.codigo.toString(), row.numero.toString())

                if (!notaCredito.isEmpty()) {
                    val genera = Electronica(notaCreditoService,
                            row.codigo.toString(),
                            row.numero.toString(),
                            parametroService,
                            electronicoService)

                    println("Respuesta: ${row.estado}")

                    genera.comprobar(informacionService, TipoComprobante.NOTA_CREDITO)
                }
            }
        }

        reporteNotaCredito.clear()

        reporteNotaCredito = reporteNotaCreditoService.findByFechas(fechaInicio, fechaFin)
        if (reporteNotaCredito.size > 0) {
            println("Estado de notas de crédito")
            for (i in reporteNotaCredito.indices) {
                val row = reporteNotaCredito.get(i)
                println("$i - ${row.codigo} ${row.numero} ${row.estado}")
            }
        }

        return ResponseEntity<MutableList<ReporteNotaCredito>>(reporteNotaCredito, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/nota_credito_verifica/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun verificarNotaCredito(@PathVariable(value = "fechaInicio") fechaInicio : String,
                             @PathVariable(value = "fechaFin") fechaFin : String) :
            ResponseEntity<MutableList<ReporteNotaCredito>> {

        var factura = reporteNotaCreditoService.findByFechasEstado(
                fechaInicio,
                fechaFin,
                "NoAutorizados")

        println("Notas de Crédito entre: $fechaInicio y  $fechaFin")
        if (factura.size > 0) {
            for (i in factura.indices) {
                val row = factura.get(i)

                val factura = notaCreditoService.findByComprobante(row.codigo.toString(), row.numero.toString())

                if (!factura.isEmpty() && (row.estado == "RECIBIDA" || row.estado == "DEVUELTA")) {
                    println("$i - ${row.codigo} ${row.numero} ${row.estado}")
                    val genera = Electronica(notaCreditoService,
                            row.codigo.toString(),
                            row.numero.toString(),
                            parametroService,
                            electronicoService)

                    genera.comprobar(informacionService, TipoComprobante.NOTA_CREDITO)
                }
            }
        }

        return ResponseEntity<MutableList<ReporteNotaCredito>>(factura, HttpStatus.OK)
    }
}