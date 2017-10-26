package com.quijotelui.electronico.xml

import com.quijotelui.electronico.xml.comprobantes.Factura
import com.quijotelui.service.IContribuyenteService
import java.io.FileNotFoundException
import java.io.UnsupportedEncodingException
import javax.xml.bind.JAXBException
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.util.logging.Logger
import javax.xml.bind.JAXBContext



class GeneraFactura() {

    val factura : Factura = Factura()
    lateinit var contribuyenteService: IContribuyenteService

    fun genera(){
        val contribuyente = contribuyenteService.findByRuc("1091707752001")
        println("Nombre Comercial : " + contribuyente.nombreComercial)

        factura.id = contribuyente.nombreComercial.toString()

        try {
            val context = JAXBContext.newInstance(Factura::class.java)
            val marshaller = context.createMarshaller()
            marshaller.setProperty("jaxb.encoding", "UTF-8")
            marshaller.setProperty("jaxb.formatted.output", java.lang.Boolean.valueOf(true))
            var out = OutputStreamWriter(FileOutputStream("/tmp/1234.xml"), "UTF-8")
            marshaller.marshal(factura, out)
            println("Archivo factura generado.")
        } catch (ex: JAXBException) {
            println("Error al generar: " + ex.message)
            Logger.getLogger("Hola")
        } catch (ex: UnsupportedEncodingException) {
            println("Error al generar: " + ex.message)
            Logger.getLogger("Hola")
        } catch (ex: FileNotFoundException) {
            println("Error al generar: " + ex.message)
            Logger.getLogger("Hola")
        }


    }
}
