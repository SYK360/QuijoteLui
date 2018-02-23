package com.quijotelui.electronico.xml

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import com.quijotelui.electronico.comprobantes.retencion.Impuesto
import com.quijotelui.electronico.comprobantes.retencion.Impuestos
import com.quijotelui.electronico.comprobantes.retencion.InformacionRetencion
import com.quijotelui.electronico.comprobantes.retencion.Retencion
import com.quijotelui.electronico.util.Modulo11
import com.quijotelui.electronico.util.Parametros
import com.quijotelui.model.Contribuyente
import com.quijotelui.service.IRetencionService
import comprobantes.CampoAdicional
import comprobantes.InformacionAdicional
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.StringWriter
import java.math.BigDecimal
import java.text.SimpleDateFormat
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

class GeneraRetencion(val retencionService : IRetencionService, val codigo : String, val numero : String) {

    val contribuyenteRetencion = retencionService.findContribuyenteByComprobante(codigo, numero)

    val retencion = Retencion()

    var claveAcceso : String? = null
        get(){
            val informacionTributaria = InformacionTributaria()

            informacionTributaria.ambiente = Parametros.getAmbiente(retencionService.findParametroByNombre("Ambiente"))
            informacionTributaria.tipoEmision = Parametros.getEmision(retencionService.findParametroByNombre("Emisión"))

            return getClaveAcceso(
                    getContribuyente(this.contribuyenteRetencion),
                    getRetencion(this.contribuyenteRetencion),
                    informacionTributaria.ambiente!!,
                    informacionTributaria.tipoEmision!!)
        }

    fun xml() : String {

        try {
            retencion.setId(id = "comprobante")
            retencion.setVersion(version = "1.0.0")

            retencion.setInformacionTributaria(getInformacionTributaria())
            retencion.setInformacionRetencion(getInformacionRetencion())
            retencion.setImpuestos(getImpuestos())
            getInformacionAdicional()?.let { retencion.setInformacionAdicional(it) }

            val jaxbContext = JAXBContext.newInstance(Retencion::class.java)
            val marshaller = jaxbContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            marshaller.setProperty("jaxb.encoding", "UTF-8")

            val stringWriter = StringWriter()
            stringWriter.use {
                marshaller.marshal(this.retencion, stringWriter)
            }

            val rutaGenerado = Parametros.getRuta(retencionService.findParametroByNombre("Generado"))
            val out = OutputStreamWriter(FileOutputStream("$rutaGenerado" +
                    "${File.separatorChar}" +
                    "${this.claveAcceso}.xml"), "UTF-8")

            marshaller.marshal(this.retencion, out)
            println(stringWriter)

        }
        catch (e: Exception) {
            println("Error en Genera Retención: ${e.message}")
            return ""
        }

        return this.claveAcceso.toString()
    }

    private fun getInformacionTributaria() : InformacionTributaria{

        val informacionTributaria = InformacionTributaria()


        var contribuyente = getContribuyente(this.contribuyenteRetencion)
        var retencionDocumento = getRetencion(this.contribuyenteRetencion)


        informacionTributaria.ambiente = Parametros.getAmbiente(retencionService.findParametroByNombre("Ambiente"))
        informacionTributaria.tipoEmision = Parametros.getEmision(retencionService.findParametroByNombre("Emisión"))
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

    private fun getInformacionRetencion() : InformacionRetencion {

        val informacionRetencion = InformacionRetencion()

        var contribuyente = getContribuyente(this.contribuyenteRetencion)
        var retencionComprobante = getRetencion(this.contribuyenteRetencion)

        informacionRetencion.fechaEmision = SimpleDateFormat("dd/MM/yyyy").format(retencionComprobante.fecha)
        informacionRetencion.dirEstablecimiento = retencionComprobante.direccionEstablecimiento
        informacionRetencion.contribuyenteEspecial = contribuyente.contribuyenteEspecial
        informacionRetencion.obligadoContabilidad = contribuyente.obligadoContabilidad
        informacionRetencion.tipoIdentificacionSujetoRetenido = retencionComprobante.tipoDocumento
        informacionRetencion.razonSocialSujetoRetenido = retencionComprobante.razonSocial
        informacionRetencion.identificacionSujetoRetenido = retencionComprobante.documento
        informacionRetencion.periodoFiscal = retencionComprobante.periodoFiscal

        return informacionRetencion

    }

    private fun getImpuestos() : Impuestos {
        val retencionDetalles = retencionService.findRetencionDetalleByComprobante(codigo, numero)
        var impuestos = Impuestos()

        for (i in retencionDetalles.indices){
            var impuesto = Impuesto()
            impuesto.codigo = retencionDetalles[i].tipo
            impuesto.codigoRetencion = retencionDetalles[i].codigoSRI
            impuesto.baseImponible = retencionDetalles[i].baseImponible?.setScale(2, BigDecimal.ROUND_HALF_UP)
            impuesto.porcentajeRetener = retencionDetalles[i].porcentaje?.setScale(2, BigDecimal.ROUND_HALF_UP)
            impuesto.valorRetenido = retencionDetalles[i].valorRetenido?.setScale(2, BigDecimal.ROUND_HALF_UP)
            impuesto.codDocSustento = retencionDetalles[i].tipoComprobante
            impuesto.numDocSustento = retencionDetalles[i].numeroComprobante
            impuesto.fechaEmisionDocSustento = retencionDetalles[i].fechaComprobante

            impuestos.setImpuesto(impuesto)
        }

        return impuestos
    }

    private fun getInformacionAdicional() : InformacionAdicional? {

        val retencionDocumento = getRetencion(this.contribuyenteRetencion)

        val informaciones = retencionService.findInformacionByDocumento(retencionDocumento.documento.toString())

        if (informaciones.isEmpty()) {
            return null
        }

        var informacionAdicional = InformacionAdicional()

        for (informacion in informaciones){
            val campoAdicional = CampoAdicional()
            campoAdicional.setNombre(informacion.nombre.toString())
            campoAdicional.setValor(informacion.valor.toString())

            informacionAdicional.setCampoAdicional(campoAdicional)
        }

        return informacionAdicional
    }

    private fun getContribuyente(contribuyenteComprobante : MutableList<Any>) : Contribuyente {
        var contribuyente = Contribuyente()
        for (i in contribuyenteComprobante.indices) {
            val row = contribuyenteComprobante[i] as Array<Any>
            contribuyente = row[0] as Contribuyente
        }
        return contribuyente
    }

    private fun getRetencion(contribuyenteConprobante : MutableList<Any>) : com.quijotelui.model.Retencion {
        var retencionModel = com.quijotelui.model.Retencion()
        for (i in contribuyenteConprobante.indices) {
            val row = contribuyenteConprobante[i] as Array<Any>
            retencionModel = row[1] as com.quijotelui.model.Retencion
        }
        return retencionModel
    }

    private fun getClaveAcceso(contribuyente: Contribuyente, retencionModel : com.quijotelui.model.Retencion, ambiente : String, emision : String) : String {

        val m11 = Modulo11()
        val claveAcceso = SimpleDateFormat("ddMMyyyy").format(retencionModel.fecha) +
                retencionModel.codigoDocumento + contribuyente.ruc + ambiente +
                retencionModel.establecimiento + retencionModel.puntoEmision + retencionModel.secuencial +
                "12345678" + emision

        return claveAcceso + m11.modulo11(claveAcceso)
    }

}