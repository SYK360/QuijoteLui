package com.quijotelui.electronico.comprobantes.nota.debito

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import comprobantes.InformacionAdicional
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf("infoTributaria", "infoNotaDebito", "motivos", "infoAdicional"))
class NotaDebito {

    @XmlAttribute
    private var id : String? = null

    @XmlAttribute
    private var version : String? = null

    @XmlElement(name = "infoTributaria")
    private var informacionTributaria = InformacionTributaria()

    @XmlElement(name = "infoNotaDebito")
    private var informacionNotaDebito = InformacionNotaDebito()

    @XmlElement
    private var motivos = Motivos()

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

    fun setInformacionNotaCredito(informacionFactura : InformacionNotaDebito) {
        this.informacionNotaDebito = informacionFactura
    }

    fun setMotivos(motivos: Motivos) {
        this.motivos = motivos
    }

    fun setInformacionAdicional(informacionAdicional: InformacionAdicional) {
        this.informacionAdicional = informacionAdicional
    }

}