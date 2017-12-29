package com.quijotelui.electronico.xml

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import com.quijotelui.electronico.comprobantes.nota.credito.InformacionNotaCredito
import com.quijotelui.electronico.comprobantes.nota.credito.NotaCredito
import com.quijotelui.electronico.comprobantes.nota.credito.TotalConImpuestos
import com.quijotelui.electronico.comprobantes.nota.credito.TotalImpuesto
import com.quijotelui.electronico.util.Modulo11
import com.quijotelui.electronico.util.Parametros
import com.quijotelui.model.Contribuyente
import com.quijotelui.service.INotaCreditoService
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

class GeneraNotaCredito (val notaCreditoService: INotaCreditoService, val codigo : String, val numero : String) {

    val contribuyenteNotaCredito = notaCreditoService.findContribuyenteByComprobante(codigo, numero)

    val notaCredito = NotaCredito()

    var claveAcceso : String? = null
        get(){
            val informacionTributaria = InformacionTributaria()

            informacionTributaria.ambiente = Parametros.getAmbiente(notaCreditoService.findParametroByNombre("Ambiente"))
            informacionTributaria.tipoEmision = Parametros.getEmision(notaCreditoService.findParametroByNombre("Emisión"))

            return getClaveAcceso(
                    getContribuyente(this.contribuyenteNotaCredito),
                    getNotaCredito(this.contribuyenteNotaCredito),
                    informacionTributaria.ambiente!!,
                    informacionTributaria.tipoEmision!!)
        }
    fun xml() : String {

//        try {
            notaCredito.setId(id = "comprobante")
            notaCredito.setVersion(version = "1.0.0")

            notaCredito.setInformacionTributaria(getInformacionTributaria())
            notaCredito.setInformacionNotaCredito(getInformacionNotaCredito())
//            notaCredito.setImpuestos(getImpuestos())
//            notaCredito.setInformacionAdicional(getInformacionAdicional())

            val jaxbContext = JAXBContext.newInstance(NotaCredito::class.java)
            val marshaller = jaxbContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            marshaller.setProperty("jaxb.encoding", "UTF-8")

            val stringWriter = StringWriter()
            stringWriter.use {
                marshaller.marshal(this.notaCredito, stringWriter)
            }

            val rutaGenerado = Parametros.getRuta(notaCreditoService.findParametroByNombre("Generado"))
            val out = OutputStreamWriter(FileOutputStream("$rutaGenerado" +
                    "${File.separatorChar}" +
                    "${this.claveAcceso}.xml"), "UTF-8")

            marshaller.marshal(this.notaCredito, out)
            println(stringWriter)

//        }
//        catch (e: Exception) {
//            println("Error en Genera Nota de Crédito: ${e.message}")
//            return ""
//        }

        return this.claveAcceso.toString()
    }

    private fun getInformacionTributaria() : InformacionTributaria{

        val informacionTributaria = InformacionTributaria()


        var contribuyente = getContribuyente(this.contribuyenteNotaCredito)
        var retencionDocumento = getNotaCredito(this.contribuyenteNotaCredito)


        informacionTributaria.ambiente = Parametros.getAmbiente(notaCreditoService.findParametroByNombre("Ambiente"))
        informacionTributaria.tipoEmision = Parametros.getEmision(notaCreditoService.findParametroByNombre("Emisión"))
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

    private fun getInformacionNotaCredito() : InformacionNotaCredito {

        val informacionNotaCredito = InformacionNotaCredito()

        var contribuyente = getContribuyente(this.contribuyenteNotaCredito)
        var notaCreditoComprobante = getNotaCredito(this.contribuyenteNotaCredito)

        informacionNotaCredito.fechaEmision = SimpleDateFormat("dd/MM/yyyy").format(notaCreditoComprobante.fecha)
        informacionNotaCredito.dirEstablecimiento = notaCreditoComprobante.direccionEstablecimiento
        informacionNotaCredito.contribuyenteEspecial = contribuyente.contribuyenteEspecial
        informacionNotaCredito.obligadoContabilidad = contribuyente.obligadoContabilidad
        informacionNotaCredito.tipoIdentificacionComprador = notaCreditoComprobante.tipoDocumento
        informacionNotaCredito.razonSocialComprador = notaCreditoComprobante.razonSocial
        informacionNotaCredito.identificacionComprador = notaCreditoComprobante.documento
        informacionNotaCredito.totalSinImpuestos = notaCreditoComprobante.totalSinIva!!.setScale(2, BigDecimal.ROUND_HALF_UP) +
                notaCreditoComprobante.totalConIva!!.setScale(2, BigDecimal.ROUND_HALF_UP)

        informacionNotaCredito.setTotalConImpuestos(getImpuesto())

        informacionNotaCredito.moneda="DOLAR"
        informacionNotaCredito.setMotivo(notaCreditoComprobante.motivo!!)
        
        return informacionNotaCredito

    }

    private fun getImpuesto() : TotalConImpuestos {

        val impuestos = notaCreditoService.findImpuestoByComprobante(codigo, numero)
        var totalConImpuestos = TotalConImpuestos()

        for (impuesto in impuestos){
            var totalImpuesto = TotalImpuesto()
            totalImpuesto.codigo = impuesto.codigoImpuesto
            totalImpuesto.codigoPorcentaje = impuesto.codigoPorcentaje
            totalImpuesto.baseImponible = impuesto.baseImponible
            totalImpuesto.valor = impuesto.valor

            totalConImpuestos.setTotalImpuesto(totalImpuesto)
        }

        return totalConImpuestos

    }

    private fun getInformacionAdicional() : InformacionAdicional {

        val notaCreditoDocumento = getNotaCredito(this.contribuyenteNotaCredito)

        val informaciones = notaCreditoService.findInformacionByDocumento(notaCreditoDocumento.documento.toString())
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

    private fun getNotaCredito(contribuyenteConprobante : MutableList<Any>) : com.quijotelui.model.NotaCredito {
        var notaCreditoModel = com.quijotelui.model.NotaCredito()
        for (i in contribuyenteConprobante.indices) {
            val row = contribuyenteConprobante[i] as Array<Any>
            notaCreditoModel = row[1] as com.quijotelui.model.NotaCredito
        }
        return notaCreditoModel
    }

    private fun getClaveAcceso(contribuyente: Contribuyente, notaCreditoModel: com.quijotelui.model.NotaCredito, ambiente : String, emision : String) : String {

        val m11 = Modulo11()
        val claveAcceso = SimpleDateFormat("ddMMyyyy").format(notaCreditoModel.fecha) +
                notaCreditoModel.codigoDocumento + contribuyente.ruc + ambiente +
                notaCreditoModel.establecimiento + notaCreditoModel.puntoEmision + notaCreditoModel.secuencial +
                "12345678" + emision

        return claveAcceso + m11.modulo11(claveAcceso)
    }

}