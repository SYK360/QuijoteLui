package com.quijotelui.controller

import com.quijotelui.electronico.ejecutar.Electronica
import com.quijotelui.electronico.util.TipoComprobante
import com.quijotelui.model.ReporteRetencion
import com.quijotelui.service.IRetencionService
import com.quijotelui.service.IParametroService
import com.quijotelui.service.IElectronicoService
import com.quijotelui.service.IReporteRetencionService
import com.quijotelui.service.IInformacionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/rest/v1")
class ReporteRetencionRestApi {

    @Autowired
    lateinit var reporteRetencionService: IReporteRetencionService

    @Autowired
    lateinit var retencionService: IRetencionService

    @Autowired
    lateinit var parametroService : IParametroService

    @Autowired
    lateinit var electronicoService : IElectronicoService

    @Autowired
    lateinit var informacionService : IInformacionService


    @CrossOrigin(value = "*")
    @GetMapping("/reporte_retencion/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun getRetencionByFechas(@PathVariable(value = "fechaInicio") fechaInicio : String,
                            @PathVariable(value = "fechaFin") fechaFin : String)
            : ResponseEntity<MutableList<ReporteRetencion>> {

        val reporteRetencion = reporteRetencionService.findByFechas(fechaInicio, fechaFin)
        return ResponseEntity<MutableList<ReporteRetencion>>(reporteRetencion, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/reporte_retencion_estado/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}/estado/{estado}")
    fun getRetencionesByFechasEstado(@PathVariable(value = "fechaInicio") fechaInicio : String,
                                  @PathVariable(value = "fechaFin") fechaFin : String,
                                  @PathVariable(value = "estado") estado : String)
            : ResponseEntity<MutableList<ReporteRetencion>> {

        val reporteRetencion = reporteRetencionService.findByFechasEstado(fechaInicio, fechaFin, estado)
        return ResponseEntity<MutableList<ReporteRetencion>>(reporteRetencion, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/retencion_autoriza/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun autorizarRetencion(@PathVariable(value = "fechaInicio") fechaInicio : String,
                          @PathVariable(value = "fechaFin") fechaFin : String) : ResponseEntity<MutableList<ReporteRetencion>> {

        var retenciones = reporteRetencionService.findByFechasEstado(
                fechaInicio,
                fechaFin,
                "NoAutorizados")

        println("Retenciones entre: $fechaInicio y  $fechaFin")
        if (retenciones.size > 0) {
            for (i in retenciones.indices) {
                val row = retenciones[i]
                println("$i - ${row.codigo} ${row.numero}, Estado -> ${row.estado}")

                val retencion = retencionService.findByComprobante(row.codigo.toString(), row.numero.toString())

                if (!retencion.isEmpty() && !row.estado.equals("RECIBIDA")) {
                    val genera = Electronica(retencionService,
                            row.codigo.toString(),
                            row.numero.toString(),
                            parametroService,
                            electronicoService)

                    genera.enviar(TipoComprobante.RETENCION)
                }
            }
            println("Espere 3 segundos para empezar la verificación")
            TimeUnit.SECONDS.sleep(3)
            for (i in retenciones.indices) {
                val row = retenciones[i]
                println("$i - ${row.codigo} ${row.numero} verificando")

                val retencion = retencionService.findByComprobante(row.codigo.toString(), row.numero.toString())
                if (!retencion.isEmpty()) {
                    val genera = Electronica(retencionService,
                            row.codigo.toString(),
                            row.numero.toString(),
                            parametroService,
                            electronicoService)

                    println("Respuesta: ${row.estado}")

                    genera.comprobar(informacionService, TipoComprobante.RETENCION)
                }
            }
        }

        retenciones.clear()

        retenciones = reporteRetencionService.findByFechas(fechaInicio, fechaFin)
        if (retenciones.size > 0) {
            println("Estado de retenciones")
            for (i in retenciones.indices) {
                val row = retenciones.get(i)
                println("$i - ${row.codigo} ${row.numero} ${row.estado}")
            }
        }

        return ResponseEntity<MutableList<ReporteRetencion>>(retenciones, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/retencion_verifica/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun verificarRetencion(@PathVariable(value = "fechaInicio") fechaInicio : String,
                          @PathVariable(value = "fechaFin") fechaFin : String) :
            ResponseEntity<MutableList<ReporteRetencion>> {

        var reporteRetencion = reporteRetencionService.findByFechasEstado(
                fechaInicio,
                fechaFin,
                "NoAutorizados")

        println("Retenciones entre: $fechaInicio y  $fechaFin")
        if (reporteRetencion.size > 0) {
            for (i in reporteRetencion.indices) {
                val row = reporteRetencion.get(i)

                val retencion = retencionService.findByComprobante(row.codigo.toString(), row.numero.toString())

                if (!retencion.isEmpty() && (row.estado == "RECIBIDA" || row.estado == "DEVUELTA")) {
                    println("$i - ${row.codigo} ${row.numero} ${row.estado}")
                    val genera = Electronica(retencionService,
                            row.codigo.toString(),
                            row.numero.toString(),
                            parametroService,
                            electronicoService)

                    genera.comprobar(informacionService, TipoComprobante.RETENCION)
                }
            }
        }

        return ResponseEntity<MutableList<ReporteRetencion>>(reporteRetencion, HttpStatus.OK)
    }

}