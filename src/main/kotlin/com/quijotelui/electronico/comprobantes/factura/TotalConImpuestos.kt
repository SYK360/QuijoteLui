package comprobantes.factura

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class TotalConImpuestos {

    @XmlElement
    private var totalImpuesto: MutableCollection<TotalImpuesto> = mutableListOf()

    fun setTotalImpuesto(totalImpuesto : TotalImpuesto) {
        this.totalImpuesto.add(totalImpuesto)
    }
}
