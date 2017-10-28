package comprobantes.factura

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Detalles {
    @XmlElement
    private var detalle: MutableCollection<Detalle> = mutableListOf()

    fun setDetalle(detalle : Detalle) {
        this.detalle.add(detalle)
    }
}