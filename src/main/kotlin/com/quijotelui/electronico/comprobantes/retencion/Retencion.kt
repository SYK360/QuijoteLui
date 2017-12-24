package com.quijotelui.electronico.comprobantes.retencion

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import comprobantes.InformacionAdicional
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement(name = "comprobanteRetencion")
@XmlType(propOrder = arrayOf(
        "informacionTributaria",
        "informacionRetencion",
        "impuestos",
        "informacionAdicional"
))
class Retencion{

    @XmlAttribute
    private var id : String? = null

    @XmlAttribute
    private var version : String? = null

    @XmlElement(name = "infoTributaria")
    private var informacionTributaria = InformacionTributaria()

    @XmlElement(name = "infoCompRetencion")
    private var informacionRetencion = InformacionRetencion()

    @XmlElement
    private var impuestos = Impuestos()

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

    fun setInformacionRetencion(informacionRetencion : InformacionRetencion) {
        this.informacionRetencion = informacionRetencion
    }

    fun setImpuestos(impuestos : Impuestos) {
        this.impuestos = impuestos
    }

    fun setInformacionAdicional(informacionAdicional : InformacionAdicional) {
        this.informacionAdicional = informacionAdicional
    }

}