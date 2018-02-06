package com.quijotelui.controller

import com.quijotelui.electronico.ejecutar.Electronica
import com.quijotelui.electronico.util.TipoComprobante
import com.quijotelui.model.ReporteGuia
import com.quijotelui.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/rest/v1")
class ReporteGuiaRestApi {

    @Autowired
    lateinit var reporteGuiaService: IReporteGuiaService

    @Autowired
    lateinit var guiaService: IGuiaService

    @Autowired
    lateinit var parametroService : IParametroService

    @Autowired
    lateinit var electronicoService : IElectronicoService

    @Autowired
    lateinit var informacionService : IInformacionService

    @CrossOrigin(value = "*")
    @GetMapping("/reporte_guia/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun getGuiaByFechas(@PathVariable(value = "fechaInicio") fechaInicio : String,
                               @PathVariable(value = "fechaFin") fechaFin : String)
            : ResponseEntity<MutableList<ReporteGuia>> {

        val reporteGuia = reporteGuiaService.findByFechas(fechaInicio, fechaFin)
        return ResponseEntity<MutableList<ReporteGuia>>(reporteGuia, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/reporte_guia_estado/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}/estado/{estado}")
    fun getGuiaByFechasEstado(@PathVariable(value = "fechaInicio") fechaInicio : String,
                                     @PathVariable(value = "fechaFin") fechaFin : String,
                                     @PathVariable(value = "estado") estado : String)
            : ResponseEntity<MutableList<ReporteGuia>> {

        val reporteGuia = reporteGuiaService.findByFechasEstado(fechaInicio, fechaFin, estado)
        return ResponseEntity<MutableList<ReporteGuia>>(reporteGuia, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/guia_autoriza/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun autorizarGuia(@PathVariable(value = "fechaInicio") fechaInicio : String,
                             @PathVariable(value = "fechaFin") fechaFin : String) : ResponseEntity<MutableList<ReporteGuia>> {

        var reporteGuia = reporteGuiaService.findByFechasEstado(
                fechaInicio,
                fechaFin,
                "NoAutorizados")

        println("Guías entre: $fechaInicio y  $fechaFin")
        if (reporteGuia.size > 0) {
            for (i in reporteGuia.indices) {
                val row = reporteGuia.get(i)
                println("$i - ${row.codigo} ${row.numero} enviando")

                val guia = guiaService.findByComprobante(row.codigo.toString(), row.numero.toString())

                if (!guia.isEmpty()) {
                    val genera = Electronica(guiaService,
                            row.codigo.toString(),
                            row.numero.toString(),
                            parametroService,
                            electronicoService)

                    genera.enviar(TipoComprobante.GUIA)
                }
            }
            println("Espere 3 segundos para empezar la verificación")
            TimeUnit.SECONDS.sleep(3)
            for (i in reporteGuia.indices) {
                val row = reporteGuia.get(i)
                println("$i - ${row.codigo} ${row.numero} verificando")

                val guia = guiaService.findByComprobante(row.codigo.toString(), row.numero.toString())

                if (!guia.isEmpty()) {
                    val genera = Electronica(guiaService,
                            row.codigo.toString(),
                            row.numero.toString(),
                            parametroService,
                            electronicoService)

                    genera.comprobar(informacionService, TipoComprobante.GUIA)
                }
            }
        }

        reporteGuia.clear()

        reporteGuia = reporteGuiaService.findByFechas(fechaInicio, fechaFin)
        if (reporteGuia.size > 0) {
            println("Estado de guías")
            for (i in reporteGuia.indices) {
                val row = reporteGuia.get(i)
                println("$i - ${row.codigo} ${row.numero} ${row.estado}")
            }
        }

        return ResponseEntity<MutableList<ReporteGuia>>(reporteGuia, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/guia_verifica/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun verificarGuia(@PathVariable(value = "fechaInicio") fechaInicio : String,
                             @PathVariable(value = "fechaFin") fechaFin : String) :
            ResponseEntity<MutableList<ReporteGuia>> {

        var reporteGuia = reporteGuiaService.findByFechasEstado(
                fechaInicio,
                fechaFin,
                "NoAutorizados")

        println("Guías entre: $fechaInicio y  $fechaFin")
        if (reporteGuia.size > 0) {
            for (i in reporteGuia.indices) {
                val row = reporteGuia.get(i)

                val guia = guiaService.findByComprobante(row.codigo.toString(), row.numero.toString())

                if (!guia.isEmpty() && (row.estado == "RECIBIDA" || row.estado == "DEVUELTA")) {
                    println("$i - ${row.codigo} ${row.numero} ${row.estado}")
                    val genera = Electronica(guiaService,
                            row.codigo.toString(),
                            row.numero.toString(),
                            parametroService,
                            electronicoService)

                    genera.comprobar(informacionService, TipoComprobante.GUIA)
                }
            }
        }

        return ResponseEntity<MutableList<ReporteGuia>>(reporteGuia, HttpStatus.OK)
    }
}