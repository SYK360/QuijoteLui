package comprobantes.factura

import comprobantes.InformacionAdicional
import comprobantes.InformacionTributaria
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf("informacionTributaria", "informacionFactura", "detalles", "informacionAdicional"))
class Factura {

    @XmlAttribute
    private var id : String? = null

    @XmlAttribute
    private var version : String? = null

    @XmlElement(name = "infoTributaria")
    private var informacionTributaria = InformacionTributaria()

    @XmlElement(name = "infoFactura")
    private var informacionFactura = InformacionFactura()

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

    fun setInformacionFactura(informacionFactura : InformacionFactura) {
        this.informacionFactura = informacionFactura
    }

    fun setDetalles(detalles : Detalles) {
        this.detalles = detalles
    }

    fun setInformacionAdicional(informacionAdicional: InformacionAdicional) {
        this.informacionAdicional = informacionAdicional
    }

}