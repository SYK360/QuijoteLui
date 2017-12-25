package com.quijotelui.electronico.xml

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import com.quijotelui.electronico.comprobantes.guia.*
import com.quijotelui.electronico.util.Modulo11
import com.quijotelui.electronico.util.Parametros
import com.quijotelui.model.Contribuyente
import com.quijotelui.model.GuiaDestinatario
import com.quijotelui.service.IGuiaService
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.StringWriter
import java.math.BigDecimal
import java.text.SimpleDateFormat
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

class GeneraGuia(val guiaService: IGuiaService, val codigo : String, val numero : String) {

    val contribuyenteGuia = guiaService.findContribuyenteByComprobante(codigo, numero)

    val guia = Guia()

    var claveAcceso : String? = null
        get(){
            val informacionTributaria = InformacionTributaria()

            informacionTributaria.ambiente = Parametros.getAmbiente(guiaService.findParametroByNombre("Ambiente"))
            informacionTributaria.tipoEmision = Parametros.getEmision(guiaService.findParametroByNombre("Emisión"))

            return getClaveAcceso(
                    getContribuyente(this.contribuyenteGuia),
                    getGuia(this.contribuyenteGuia),
                    informacionTributaria.ambiente!!,
                    informacionTributaria.tipoEmision!!)
        }

    fun xml() : String {

        try {
            guia.setId(id = "comprobante")
            guia.setVersion(version = "1.1.0")

            guia.setInformacionTributaria(getInformacionTributaria())
            guia.setInformacionGuiaRemision(getInformacionGuia())
            guia.setDestinatarios(getDestinatario())

            val jaxbContext = JAXBContext.newInstance(Guia::class.java)
            val marshaller = jaxbContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            marshaller.setProperty("jaxb.encoding", "UTF-8")

            val stringWriter = StringWriter()
            stringWriter.use {
                marshaller.marshal(this.guia, stringWriter)
            }

            val rutaGenerado = Parametros.getRuta(guiaService.findParametroByNombre("Generado"))
            val out = OutputStreamWriter(FileOutputStream("$rutaGenerado" +
                    "${File.separatorChar}" +
                    "${this.claveAcceso}.xml"), "UTF-8")

            marshaller.marshal(this.guia, out)
            println(stringWriter)

        }
        catch (e: Exception) {
            println("Error en Genera Guía: ${e.message}")
            return ""
        }

        return this.claveAcceso.toString()
    }

    private fun getInformacionTributaria() : InformacionTributaria{

        val informacionTributaria = InformacionTributaria()


        var contribuyente = getContribuyente(this.contribuyenteGuia)
        var retencionDocumento = getGuia(this.contribuyenteGuia)


        informacionTributaria.ambiente = Parametros.getAmbiente(guiaService.findParametroByNombre("Ambiente"))
        informacionTributaria.tipoEmision = Parametros.getEmision(guiaService.findParametroByNombre("Emisión"))
        informacionTributaria.razonSocial = contribuyente.razonSocial
        informacionTributaria.nombreComercial = contribuyente.nombreComercial
        informacionTributaria.ruc = contribuyente.ruc

        this.claveAcceso = getClaveAcceso(contribuyente, retencionDocumento, informacionTributaria.ambiente!!, informacionTributaria.tipoEmision!!)

        informacionTributaria.claveAcceso = this.claveAcceso
        informacionTributaria.codDoc = retencionDocumento.codigoDocumento
        informacionTributaria.estab = retencionDocumento.establecimiento
        informacionTributaria.ptoEmi = retencionDocumento.puntoEmision
        informacionTributaria.secuencial = retencionDocumento.secuencial
        informacionTributaria.dirMatriz = contribuyente.direccion

        return informacionTributaria

    }

    private fun getInformacionGuia() : InformacionGuiaRemision {

        val informacionGuiaRemision = InformacionGuiaRemision()

        var contribuyente = getContribuyente(this.contribuyenteGuia)
        var guiaComprobante = getGuia(this.contribuyenteGuia)

        informacionGuiaRemision.dirEstablecimiento = guiaComprobante.direccionEstablecimiento
        informacionGuiaRemision.dirPartida =guiaComprobante.direccionPartida
        informacionGuiaRemision.razonSocialTransportista = guiaComprobante.razonSocialTransportista
        informacionGuiaRemision.tipoIdentificacionTransportista = guiaComprobante.tipoDocumento
        informacionGuiaRemision.rucTransportista = guiaComprobante.documento
        informacionGuiaRemision.obligadoContabilidad = contribuyente.obligadoContabilidad
        informacionGuiaRemision.contribuyenteEspecial = contribuyente.contribuyenteEspecial
        informacionGuiaRemision.fechaIniTransporte = SimpleDateFormat("dd/MM/yyyy").format(guiaComprobante.fecha)
        informacionGuiaRemision.fechaFinTransporte = SimpleDateFormat("dd/MM/yyyy").format(guiaComprobante.fechaFin)
        informacionGuiaRemision.placa = guiaComprobante.placa

        return informacionGuiaRemision

    }

    private fun getDestinatario() : Destinatarios {
        val guiaDestinatarios = guiaService.findDestinatarioByComprobante(codigo, numero)
        var destinatarios = Destinatarios()

        for (i in guiaDestinatarios.indices) {
            var destinatario = Destinatario()
            destinatario.identificacionDestinatario = guiaDestinatarios[i].documento
            destinatario.razonSocialDestinatario = guiaDestinatarios[i].razonSocial
            destinatario.dirDestinatario = guiaDestinatarios[i].direccion
            destinatario.motivoTraslado = guiaDestinatarios[i].motivoTraslado

            destinatario.setDetalles(getDetalleImpuestos(guiaDestinatarios[i]))

            destinatarios.setDestinatario(destinatario)
        }

        return destinatarios
    }

    private fun getDetalleImpuestos(guiaDestinatario: GuiaDestinatario) : Detalles {

        val destinatarioDetalle = guiaService.findDestinatarioDetalleByComprobante(
                guiaDestinatario.codigo!!,
                guiaDestinatario.numero!!,
                guiaDestinatario.documento!!
        )
        var detalles = Detalles()

        for (i in destinatarioDetalle.indices) {
            var detalle = Detalle()
            detalle.codigoInterno = destinatarioDetalle[i].codigoArticulo
            detalle.descripcion = destinatarioDetalle[i].nombreArticulo
            detalle.cantidad = destinatarioDetalle[i].cantidad?.setScale(2, BigDecimal.ROUND_HALF_UP)
            detalles.setDetalle(detalle)
        }

        return detalles
    }

    private fun getContribuyente(contribuyenteComprobante : MutableList<Any>) : Contribuyente {
        var contribuyente = Contribuyente()
        for (i in contribuyenteComprobante.indices) {
            val row = contribuyenteComprobante[i] as Array<Any>
            contribuyente = row[0] as Contribuyente
        }
        return contribuyente
    }

    private fun getGuia(contribuyenteConprobante : MutableList<Any>) : com.quijotelui.model.Guia {
        var guiaModel = com.quijotelui.model.Guia()
        for (i in contribuyenteConprobante.indices) {
            val row = contribuyenteConprobante[i] as Array<Any>
            guiaModel = row[1] as com.quijotelui.model.Guia
        }
        return guiaModel
    }

    private fun getClaveAcceso(contribuyente : Contribuyente, guiaModel : com.quijotelui.model.Guia, ambiente : String, emision : String) : String {

        val m11 = Modulo11()
        val claveAcceso = SimpleDateFormat("ddMMyyyy").format(guiaModel.fecha) +
                guiaModel.codigoDocumento + contribuyente.ruc + ambiente +
                guiaModel.establecimiento + guiaModel.puntoEmision + guiaModel.secuencial +
                "12345678" + emision

        return claveAcceso + m11.modulo11(claveAcceso)
    }
}