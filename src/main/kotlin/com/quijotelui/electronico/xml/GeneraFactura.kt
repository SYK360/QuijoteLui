package com.quijotelui.electronico.xml

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import com.quijotelui.electronico.comprobantes.factura.Factura
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.StringWriter
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller


class GeneraFactura() {

    fun genera(){
        val informacionTributaria = InformacionTributaria()
        informacionTributaria.ambiente = "1"
        informacionTributaria.tipoEmision = "1"
        informacionTributaria.razonSocial = "Quiguango Terán Jorge Luis"
        informacionTributaria.nombreComercial = "QuijoteLui"
        informacionTributaria.ruc = "1002456877001"
        informacionTributaria.claveAcceso = "0000000000000000000000000000000000000000000"
        informacionTributaria.codDoc = "01"
        informacionTributaria.estab = "001"
        informacionTributaria.ptoEmi = "010"
        informacionTributaria.secuencial = "000000013"
        informacionTributaria.dirMatriz = "Cananvalle Calle Los Higos y Calle Cananvalle"

        val factura = Factura()
        factura.setId(id = "comprobante")
        factura.setVersion(version = "1.0.0")

        factura.setInformacionTributaria(informacionTributaria)

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
