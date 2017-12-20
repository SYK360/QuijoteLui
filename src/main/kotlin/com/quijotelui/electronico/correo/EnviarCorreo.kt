package com.quijotelui.electronico.correo

import com.quijotelui.electronico.util.Parametros
import com.quijotelui.electronico.xml.GeneraFactura
import com.quijotelui.model.Informacion
import com.quijotelui.service.IFacturaService
import com.quijotelui.service.IInformacionService
import com.quijotelui.service.IParametroService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.io.File

class EnviarCorreo(val codigo : String,
                   val numero : String,
                   val parametroService : IParametroService,
                   val informacionService : IInformacionService) {

    var claveAcceso : String? = null
    private var facturaService : IFacturaService? = null

    constructor(codigo : String,
                numero : String,
                parametroService : IParametroService,
                informacionService : IInformacionService,
                facturaService : IFacturaService)
            : this(codigo, numero, parametroService, informacionService) {
        this.facturaService = facturaService
    }

    fun enviar() : ResponseEntity<MutableList<Informacion>> {

        var documento = ""
        var descripcion = ""

        if (codigo == "FAC") {
            val factura = facturaService!!.findByComprobante(codigo, numero)
            if (factura.isEmpty()) {
                return ResponseEntity(HttpStatus.CONFLICT)
            }
            documento = factura[0].documento!!
            descripcion = "Factura"

            val genera = GeneraFactura(this.facturaService!!, this.codigo, this.numero)
            claveAcceso = genera.claveAcceso
            println("Clave de Acceso: $claveAcceso")
        }


        val informacion = informacionService.correoByDocumento(documento)
        val parametro = parametroService.findAll()
        val datosCorreo = Parametros.getDatosCorreo(parametro)

        val rutaPDF= Parametros.getRuta(parametroService.findByNombre("PDF"))
        val rutaXML= Parametros.getRuta(parametroService.findByNombre("Autorizado"))


        try {
            val correo = Correo(datosCorreo.servidor, datosCorreo.puerto)

            correo.remitente(datosCorreo.correo, datosCorreo.clave)

            correo.pdf(File(rutaPDF +
                    File.separatorChar +
                    "$claveAcceso.pdf"), "$descripcion PDF")
            correo.xml(File(rutaXML +
                    File.separatorChar +
                    "$claveAcceso.xml"), "$descripcion XML")

            for (i in informacion.indices) {
                correo.destinatario(informacion[i].valor.toString())
            }
            correo.enviar("$descripcion Electrónica",
                    "Saludos cordiales, Adjunto el comprobante electrónico")

        }
        catch (e : java.lang.IllegalArgumentException) {
            println("Error al enviar el correo ${e.message}")
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        catch (e: javax.mail.AuthenticationFailedException){
            println("Error al enviar el correo ${e.message}")
            return ResponseEntity(HttpStatus.CONFLICT)
        }
        catch (e: java.io.IOException){
            println("Error al enviar el correo ${e.message}")
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        return ResponseEntity<MutableList<Informacion>>(informacion, HttpStatus.OK)

    }
}