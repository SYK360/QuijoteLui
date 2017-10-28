package com.quijotelui.electronico.comprobantes.factura

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Factura {

    @XmlAttribute
    private var id : String? = null

    @XmlAttribute
    private var version : String? = null


    fun setId(id : String) {
        this.id = id
    }

    fun setVersion(version : String) {
        this.version = version
    }

    @XmlElement(name = "infoTributaria")
    private var informacionTributaria : InformacionTributaria = InformacionTributaria(ambiente = "")

    fun setInformacionTributaria(informacionTributaria : InformacionTributaria){
        this.informacionTributaria = informacionTributaria
    }

}