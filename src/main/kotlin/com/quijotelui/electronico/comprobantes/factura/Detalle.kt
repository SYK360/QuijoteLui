package comprobantes.factura

import java.math.BigDecimal
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf(
        "codigoPrincipal",
        "descripcion",
        "cantidad",
        "precioUnitario",
        "descuento",
        "precioTotalSinImpuesto",
        "impuestos"))

class Detalle (
    @XmlElement var codigoPrincipal: String? = null,
    @XmlElement var descripcion: String? = null,
    @XmlElement var Cantidad: BigDecimal? = null,
    @XmlElement var precioUnitario: BigDecimal? = null,
    @XmlElement var descuento: BigDecimal? = null,
    @XmlElement var precioTotalSinImpuesto: BigDecimal? = null
)
{
    @XmlElement
    private var impuestos = Impuestos()

    fun setImpuestos(impuestos : Impuestos) {
        this.impuestos = impuestos
    }

}