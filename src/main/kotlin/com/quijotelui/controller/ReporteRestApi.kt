package com.quijotelui.controller

import com.quijotelui.model.ReporteFactura
import com.quijotelui.service.IReporteFacturaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/rest/v1")
class ReporteRestApi {

    @Autowired
    lateinit var reporteFacturaService : IReporteFacturaService

    @CrossOrigin(value = "*")
    @GetMapping("/reporte_factura/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}")
    fun getByFechas(@PathVariable(value = "fechaInicio") fechaInicio : String,
                    @PathVariable(value = "fechaFin") fechaFin : String)
            : ResponseEntity<MutableList<ReporteFactura>> {

        val reporteFactura = reporteFacturaService.findByFechas(fechaInicio, fechaFin)
        return ResponseEntity<MutableList<ReporteFactura>>(reporteFactura, HttpStatus.OK)
    }

    @CrossOrigin(value = "*")
    @GetMapping("/reporte_factura_estado/fechaInicio/{fechaInicio}/fechaFin/{fechaFin}/estado/{estado}")
    fun getByFechasEstado(@PathVariable(value = "fechaInicio") fechaInicio : String,
                          @PathVariable(value = "fechaFin") fechaFin : String,
                          @PathVariable(value = "estado") estado : String)
            : ResponseEntity<MutableList<ReporteFactura>> {

        val reporteFactura = reporteFacturaService.findByFechasEstado(fechaInicio, fechaFin, estado)
        return ResponseEntity<MutableList<ReporteFactura>>(reporteFactura, HttpStatus.OK)
    }
}