package comprobantes.factura

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Pagos {
    @XmlElement
    private var pago: MutableCollection<Pago> = mutableListOf()

    fun setPago(pago : Pago) {
        this.pago.add(pago)
    }
}