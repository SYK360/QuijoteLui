package comprobantes.factura

import comprobantes.InformacionTributaria
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Factura {

    @XmlAttribute
    private var id : String? = null

    @XmlAttribute
    private var version : String? = null

    @XmlElement(name = "infoTributaria")
    private var informacionTributaria = InformacionTributaria()

    @XmlElement(name = "infoFactura")
    private var informacionFactura = InformacionFactura()

    fun setId(id : String) {
        this.id = id
    }

    fun setVersion(version : String) {
        this.version = version
    }

    fun setInformacionTributaria(informacionTributaria : InformacionTributaria) {
        this.informacionTributaria = informacionTributaria
    }

    fun setInformacionFactura(informacionFactura : InformacionFactura) {
        this.informacionFactura = informacionFactura
    }

}