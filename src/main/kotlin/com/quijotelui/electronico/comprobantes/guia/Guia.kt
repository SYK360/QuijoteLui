package com.quijotelui.electronico.comprobantes.guia

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement(name = "guiaRemision")
@XmlType(propOrder = arrayOf(
        "informacionTributaria",
        "informacionGuiaRemision",
        "destinatarios"
))
class Guia {

    @XmlAttribute
    private var id : String? = null

    @XmlAttribute
    private var version : String? = null

    @XmlElement(name = "infoTributaria")
    private var informacionTributaria = InformacionTributaria()

    @XmlElement(name = "infoGuiaRemision")
    private var informacionGuiaRemision = InformacionGuiaRemision()

    @XmlElement
    private var destinatarios = Destinatarios()

    fun setId(id : String) {
        this.id = id
    }

    fun setVersion(version : String) {
        this.version = version
    }

    fun setInformacionTributaria(informacionTributaria : InformacionTributaria) {
        this.informacionTributaria = informacionTributaria
    }

    fun setInformacionGuiaRemision(informacionGuiaRemision : InformacionGuiaRemision) {
        this.informacionGuiaRemision = informacionGuiaRemision
    }

    fun setDestinatarios(destinatarios : Destinatarios) {
        this.destinatarios = destinatarios
    }

}