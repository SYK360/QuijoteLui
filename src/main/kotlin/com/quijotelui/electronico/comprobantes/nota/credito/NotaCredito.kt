package com.quijotelui.electronico.comprobantes.nota.credito

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import comprobantes.InformacionAdicional
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf("informacionTributaria", "informacionNotaCredito", "detalles", "informacionAdicional"))
class NotaCredito {

    @XmlAttribute
    private var id : String? = null

    @XmlAttribute
    private var version : String? = null

    @XmlElement(name = "infoTributaria")
    private var informacionTributaria = InformacionTributaria()

    @XmlElement(name = "infoNotaCredito")
    private var informacionNotaCredito = InformacionNotaCredito()

    @XmlElement
    private var detalles = Detalles()

    @XmlElement(name = "infoAdicional")
    private  var informacionAdicional = InformacionAdicional()

    fun setId(id : String) {
        this.id = id
    }

    fun setVersion(version : String) {
        this.version = version
    }

    fun setInformacionTributaria(informacionTributaria : InformacionTributaria) {
        this.informacionTributaria = informacionTributaria
    }

    fun setInformacionNotaCredito(informacionFactura : InformacionNotaCredito) {
        this.informacionNotaCredito = informacionFactura
    }

    fun setDetalles(detalles : Detalles) {
        this.detalles = detalles
    }

    fun setInformacionAdicional(informacionAdicional: InformacionAdicional) {
        this.informacionAdicional = informacionAdicional
    }

}