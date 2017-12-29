package com.quijotelui.electronico.xml

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import com.quijotelui.electronico.comprobantes.factura.Factura
import com.quijotelui.electronico.util.Modulo11
import com.quijotelui.electronico.util.Parametros
import com.quijotelui.model.Contribuyente
import com.quijotelui.model.FacturaDetalle
import com.quijotelui.service.IFacturaService
import comprobantes.CampoAdicional
import comprobantes.InformacionAdicional
import comprobantes.factura.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.StringWriter
import java.math.BigDecimal
import java.text.SimpleDateFormat
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

class GeneraFactura(val facturaService : IFacturaService, val codigo : String, val numero : String) {


    val contribuyenteFactura = facturaService.findContribuyenteByComprobante(codigo, numero)

    val factura = Factura()
    var claveAcceso : String? = null
        get(){
            val informacionTributaria = InformacionTributaria()

            informacionTributaria.ambiente = Parametros.getAmbiente(facturaService.findParametroByNombre("Ambiente"))
            informacionTributaria.tipoEmision = Parametros.getEmision(facturaService.findParametroByNombre("Emisión"))

            return getClaveAcceso(
                    getContribuyente(this.contribuyenteFactura),
                    getFactura(this.contribuyenteFactura),
                    informacionTributaria.ambiente!!,
                    informacionTributaria.tipoEmision!!)
        }

    /*
        Función que genera la factura en XML
     */
    fun xml() : String {

        try {
            factura.setId(id = "comprobante")
            factura.setVersion(version = "1.0.0")

            factura.setInformacionTributaria(getInformacionTributaria())
            factura.setInformacionFactura(getInformacionFactura())
            factura.setDetalles(getDetalle())
            factura.setInformacionAdicional(getInformacionAdicional())

            val jaxbContext = JAXBContext.newInstance(Factura::class.java)
            val marshaller = jaxbContext.createMarshaller()
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
            marshaller.setProperty("jaxb.encoding", "UTF-8")

            val stringWriter = StringWriter()
            stringWriter.use {
                marshaller.marshal(this.factura, stringWriter)
            }

            val rutaGenerado = Parametros.getRuta(facturaService.findParametroByNombre("Generado"))
            val out = OutputStreamWriter(FileOutputStream("$rutaGenerado" +
                    "${File.separatorChar}" +
                    "${this.claveAcceso}.xml"), "UTF-8")

            marshaller.marshal(this.factura, out)
            println(stringWriter)

        }
        catch (e: Exception) {
            println("Error en Genera Factura: ${e.message}")
            return ""
        }

        return this.claveAcceso.toString()
    }


    private fun getInformacionTributaria() : InformacionTributaria{

        val informacionTributaria = InformacionTributaria()


        var contribuyente = getContribuyente(this.contribuyenteFactura)
        var facturaDocumento = getFactura(this.contribuyenteFactura)


        informacionTributaria.ambiente = Parametros.getAmbiente(facturaService.findParametroByNombre("Ambiente"))
        informacionTributaria.tipoEmision = Parametros.getEmision(facturaService.findParametroByNombre("Emisión"))
        informacionTributaria.razonSocial = contribuyente.razonSocial
        informacionTributaria.nombreComercial = contribuyente.nombreComercial
        informacionTributaria.ruc = contribuyente.ruc

        this.claveAcceso = getClaveAcceso(contribuyente, facturaDocumento, informacionTributaria.ambiente!!, informacionTributaria.tipoEmision!!)

        informacionTributaria.claveAcceso = this.claveAcceso
        informacionTributaria.codDoc = facturaDocumento.codigoDocumento
        informacionTributaria.estab = facturaDocumento.establecimiento
        informacionTributaria.ptoEmi = facturaDocumento.puntoEmision
        informacionTributaria.secuencial = facturaDocumento.secuencial
        informacionTributaria.dirMatriz = contribuyente.direccion

        return informacionTributaria

    }

    private fun getInformacionFactura() : InformacionFactura {

        val informacionFactura = InformacionFactura()

        var contribuyente = getContribuyente(this.contribuyenteFactura)
        var facturaComprobante = getFactura(this.contribuyenteFactura)

        informacionFactura.fechaEmision = SimpleDateFormat("dd/MM/yyyy").format(facturaComprobante.fecha)
        informacionFactura.dirEstablecimiento = facturaComprobante.direccionEstablecimiento
        informacionFactura.contribuyenteEspecial = contribuyente.contribuyenteEspecial
        informacionFactura.obligadoContabilidad = contribuyente.obligadoContabilidad
        informacionFactura.tipoIdentificacionComprador = facturaComprobante.tipoDocumento
        informacionFactura.razonSocialComprador = facturaComprobante.razonSocial
        informacionFactura.identificacionComprador = facturaComprobante.documento
        informacionFactura.direccionComprador = facturaComprobante.direccion
        informacionFactura.totalSinImpuestos = facturaComprobante.totalSinIva!!.setScale(2, BigDecimal.ROUND_HALF_UP) +
                facturaComprobante.totalConIva!!.setScale(2, BigDecimal.ROUND_HALF_UP)
        informacionFactura.totalDescuento = facturaComprobante.descuentos!!.setScale(2, BigDecimal.ROUND_HALF_UP)

        informacionFactura.setTotalConImpuestos(getImpuesto())

        informacionFactura.setPropina(BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
        informacionFactura.setImporteTotal(facturaComprobante.total!!.setScale(2, BigDecimal.ROUND_HALF_UP))
        informacionFactura.setMoneda("DOLAR")

        informacionFactura.setPagos(getPago())


        return informacionFactura

    }

    private fun getImpuesto() : TotalConImpuestos {

        val impuestos = facturaService.findImpuestoByComprobante(codigo, numero)
        var totalConImpuestos = TotalConImpuestos()

        for (impuesto in impuestos){
            var totalImpuesto = TotalImpuesto()
            totalImpuesto.codigo = impuesto.codigoImpuesto
            totalImpuesto.codigoPorcentaje = impuesto.codigoPorcentaje
            totalImpuesto.baseImponible = impuesto.baseImponible
            totalImpuesto.tarifa = impuesto.tarifa
            totalImpuesto.valor = impuesto.valor

            totalConImpuestos.setTotalImpuesto(totalImpuesto)
        }

        return totalConImpuestos

    }

    private fun getPago() : Pagos {

        val pagosComprobante = facturaService.findPagoByComprobante(codigo, numero)
        var pagos = Pagos()

        for (pagoComprobante in pagosComprobante){
            var pago = Pago()
            pago.formaPago = pagoComprobante.formaPago
            pago.total = pagoComprobante.total?.setScale(2, BigDecimal.ROUND_HALF_UP)
            pago.plazo = pagoComprobante.plazo
            pago.unidadTiempo = pagoComprobante.tiempo

            pagos.setPago(pago)
        }

        return pagos
    }

    private fun getDetalle() : Detalles {
        val facturaDetalles = facturaService.findFacturaDetalleByComprobante(codigo, numero)
        var detalles = Detalles()

        for (i in facturaDetalles.indices){
            var detalle = Detalle()
            detalle.codigoPrincipal = facturaDetalles[i].codigoPrincipal
            detalle.descripcion = facturaDetalles[i].descripcion
            detalle.cantidad = facturaDetalles[i].cantidad?.setScale(2, BigDecimal.ROUND_HALF_UP)
            detalle.precioUnitario = facturaDetalles[i].precioUnitario?.setScale(2, BigDecimal.ROUND_HALF_UP)
            detalle.descuento = facturaDetalles[i].descuento?.setScale(2, BigDecimal.ROUND_HALF_UP)
            detalle.precioTotalSinImpuesto = facturaDetalles[i].precioTotalSinImpuesto?.setScale(2, BigDecimal.ROUND_HALF_UP)
            

            detalle.setImpuestos(getDetalleImpuestos(facturaDetalles[i]))

            detalles.setDetalle(detalle)
        }

        return detalles
    }

    private fun getDetalleImpuestos(detalle : FacturaDetalle) : Impuestos {
        /*
        Los detalles de impuestos por
        producto, solo está habilitado para el IVA.
        En futuras versiones se soportará ICE y más
        impuestos existentes.
        */

        val impuesto = Impuesto()
        var impuestos = Impuestos()

        impuesto.codigo = "2"
        impuesto.codigoPorcentaje = detalle.codigoPorcentaje
        impuesto.tarifa = detalle.porcentajeIva
        impuesto.baseImponible = detalle.precioTotalSinImpuesto?.setScale(2, BigDecimal.ROUND_HALF_UP)
        impuesto.valor = detalle.valorIva?.setScale(2, BigDecimal.ROUND_HALF_UP)

        impuestos.setImpuesto(impuesto)

        return impuestos
    }

    private fun getInformacionAdicional() : InformacionAdicional {

        val facturaDocumento = getFactura(this.contribuyenteFactura)

        val informaciones = facturaService.findInformacionByDocumento(facturaDocumento.documento.toString())
        var informacionAdicional = InformacionAdicional()

        for (informacion in informaciones){
            val campoAdicional = CampoAdicional()
            campoAdicional.setNombre(informacion.nombre.toString())
            campoAdicional.setValor(informacion.valor.toString())

            informacionAdicional.setCampoAdicional(campoAdicional)
        }

        return informacionAdicional
    }

    private fun getContribuyente(contribuyenteComprobante: MutableList<Any>) : Contribuyente {
        var contribuyente = Contribuyente()
        for (i in contribuyenteComprobante.indices) {
            val row = contribuyenteComprobante[i] as Array<Any>
            contribuyente = row[0] as Contribuyente
        }
        return contribuyente
    }

    private fun getFactura (contribuyenteConprobante : MutableList<Any>) : com.quijotelui.model.Factura {
        var facturaModel = com.quijotelui.model.Factura()
        for (i in contribuyenteConprobante.indices) {
            val row = contribuyenteConprobante[i] as Array<Any>
            facturaModel = row[1] as com.quijotelui.model.Factura
        }
        return facturaModel
    }

    private fun getClaveAcceso(contribuyente: Contribuyente, facturaModel: com.quijotelui.model.Factura, ambiente : String, emision : String) : String {

        val m11 = Modulo11()
        val claveAcceso = SimpleDateFormat("ddMMyyyy").format(facturaModel.fecha) +
                facturaModel.codigoDocumento + contribuyente.ruc + ambiente +
                facturaModel.establecimiento + facturaModel.puntoEmision + facturaModel.secuencial +
                "12345678" + emision

        return claveAcceso + m11.modulo11(claveAcceso)
    }

}