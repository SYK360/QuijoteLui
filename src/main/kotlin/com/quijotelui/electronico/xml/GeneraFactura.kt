package com.quijotelui.electronico.xml

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import com.quijotelui.electronico.comprobantes.factura.Factura
import com.quijotelui.model.Contribuyente
import com.quijotelui.model.Parametro
import com.quijotelui.service.IFacturaService
import comprobantes.CampoAdicional
import comprobantes.InformacionAdicional
import comprobantes.factura.*
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.StringWriter
import java.math.BigDecimal
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller


class GeneraFactura(val facturaService : IFacturaService, val codigo : String, val numero : String) {

    val factura = Factura()
    var error = ""

    fun xml(){

        factura.setInformacionTributaria(getInformacionTributaria())

        if ( !this.error.equals("")){
            println("Error: $error")
        }


        val jaxbContext = JAXBContext.newInstance(Factura::class.java)
        val marshaller = jaxbContext.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        marshaller.setProperty("jaxb.encoding", "UTF-8")

        val stringWriter = StringWriter()
        stringWriter.use {
            marshaller.marshal(this.factura, stringWriter)
        }

//        val out = OutputStreamWriter(FileOutputStream("1234.xml"), "UTF-8")
//        marshaller.marshal(this.factura, out)

        println(stringWriter)
    }

    fun getInformacionTributaria() : InformacionTributaria{
        val informacionTributaria = InformacionTributaria()
        val parametro = facturaService.findParametroByNombre("Ambiente")

        val contribuyenteConprobante = facturaService.findContribuyenteByComprobante(codigo, numero)

        var contribuyente = getContribuyente(contribuyenteConprobante)
//        var facturaDocumento = getFactura(contribuyenteConprobante)


        informacionTributaria.ambiente = getAmbiente(parametro)
        informacionTributaria.tipoEmision = "1"
        informacionTributaria.razonSocial = contribuyente?.razonSocial

        return informacionTributaria

    }

    fun getAmbiente(parametro : MutableList<Parametro>) : String {
        if (parametro.isEmpty()){
            error = "No existe valor para el parámetro Ambiente"
            return "0"
        }
        else if (parametro.size > 1){
            error = "Existen más de un valor para el parámetro Ambiente"
            return "0"
        }
        else {
            println("Ambiente " + parametro[0].valor)
            if ( parametro[0].valor == "Pruebas"){
                return "1"
            }
            else if ( parametro[0].valor == "Producción"){
                return "2"
            }
        }
        error = "El parámetro Ambiente no fue encontrado"
        return "0"
    }

    fun getContribuyente(contribuyenteConprobante : MutableList<Any>) : Contribuyente? {
        var contribuyente = Contribuyente()
        for (i in contribuyenteConprobante.indices) {
            val row = contribuyenteConprobante[i] as Array<Any>
            contribuyente = row[0] as Contribuyente
        }
        return contribuyente
    }
/*
    fun getFactura (contribuyenteConprobante : MutableList<Any>) : Factura? {
        if (contribuyenteConprobante.size > 0) {

            for (i in contribuyenteConprobante.indices) {
                val row = contribuyenteConprobante.get(i) as Array<Any>
                return row[1] as Factura
            }

        }
        return null
    }*/

    fun genera() {
        val informacionTributaria = InformacionTributaria()
        informacionTributaria.ambiente = "1"
        informacionTributaria.tipoEmision = "1"
        informacionTributaria.razonSocial = "Quiguango Jorge Luis"
        informacionTributaria.nombreComercial = "QuijoteLui"
        informacionTributaria.ruc = "102245612701"
        informacionTributaria.claveAcceso = "0000000000000000000000000000000000000000000"
        informacionTributaria.codDoc = "01"
        informacionTributaria.estab = "001"
        informacionTributaria.ptoEmi = "010"
        informacionTributaria.secuencial = "000000013"
        informacionTributaria.dirMatriz = "Cananvalle Calle Las Uvillas y Calle Cananvalle"


        val informacionFactura = InformacionFactura()
        informacionFactura.fechaEmision = "03/10/2017"
        informacionFactura.dirEstablecimiento = informacionTributaria.dirMatriz
        informacionFactura.obligadoContabilidad = "NO"
        informacionFactura.tipoIdentificacionComprador = "05"
        informacionFactura.razonSocialComprador = "Arenita"
        informacionFactura.identificacionComprador = "1002345644"
        informacionFactura.direccionComprador = "Priorato"
        informacionFactura.totalSinImpuestos = BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP)
        informacionFactura.totalDescuento = BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)

        val totalImpuesto = TotalImpuesto()
        totalImpuesto.codigo = "2"
        totalImpuesto.codigoPorcentaje = "2"
        totalImpuesto.baseImponible = BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP)
        totalImpuesto.tarifa = BigDecimal(12).setScale(0, BigDecimal.ROUND_HALF_UP)
        totalImpuesto.valor = BigDecimal(1.20).setScale(2, BigDecimal.ROUND_HALF_UP)

        var totalConImpuestos = TotalConImpuestos()
        totalConImpuestos.setTotalImpuesto(totalImpuesto)

        informacionFactura.setTotalConImpuestos(totalConImpuestos)

        informacionFactura.setPropina(BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP))
        informacionFactura.setImporteTotal(BigDecimal(11.20).setScale(2, BigDecimal.ROUND_HALF_UP))
        informacionFactura.setMoneda("DOLAR")

        val pago = Pago()
        pago.formaPago = "20"
        pago.total = BigDecimal(11.20).setScale(2, BigDecimal.ROUND_HALF_UP)


        var pagos = Pagos()
        pagos.setPago(pago)

        informacionFactura.setPagos(pagos)


        val detalle = Detalle()
        detalle.codigoPrincipal = "1"
        detalle.descripcion = "Servicio de Pruebas"
        detalle.Cantidad = BigDecimal(1).setScale(2, BigDecimal.ROUND_HALF_UP)
        detalle.precioUnitario = BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP)
        detalle.descuento = BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP)
        detalle.precioTotalSinImpuesto = BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP)


        val impuesto = Impuesto()
        impuesto.codigo = "2"
        impuesto.codigoPorcentaje = "2"
        impuesto.tarifa = BigDecimal(12).setScale(2, BigDecimal.ROUND_HALF_UP)
        impuesto.baseImponible = BigDecimal(10).setScale(2, BigDecimal.ROUND_HALF_UP)
        impuesto.valor = BigDecimal(1.2).setScale(2, BigDecimal.ROUND_HALF_UP)

        var impuestos = Impuestos()
        impuestos.setImpuesto(impuesto)

        detalle.setImpuestos(impuestos)

        var detalles = Detalles()
        detalles.setDetalle(detalle)


        val campoAdicional = CampoAdicional()
        campoAdicional.setNombre("Teléfono")
        campoAdicional.setValor("999999999")

        var informacionAdicional = InformacionAdicional()
        informacionAdicional.setCampoAdicional(campoAdicional)

        val factura = Factura()

        factura.setId(id = "comprobante")
        factura.setVersion(version = "1.0.0")

        factura.setInformacionTributaria(informacionTributaria)
        factura.setInformacionFactura(informacionFactura)
        factura.setDetalles(detalles)
        factura.setInformacionAdicional(informacionAdicional)


        val jaxbContext = JAXBContext.newInstance(Factura::class.java)
        val marshaller = jaxbContext.createMarshaller()
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
        marshaller.setProperty("jaxb.encoding", "UTF-8")

        val stringWriter = StringWriter()
        stringWriter.use {
            marshaller.marshal(factura, stringWriter)
        }

        val out = OutputStreamWriter(FileOutputStream("1234.xml"), "UTF-8")
        marshaller.marshal(factura, out)

        println(stringWriter)
    }

}
