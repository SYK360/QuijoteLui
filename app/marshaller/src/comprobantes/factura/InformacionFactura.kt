package comprobantes.factura

import java.math.BigDecimal
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf(
        "fechaEmision",
        "dirEstablecimiento",
        "contribuyenteEspecial",
        "obligadoContabilidad",
        "tipoIdentificacionComprador",
        "razonSocialComprador",
        "identificacionComprador",
        "totalSinImpuestos",
        "totalDescuento"))

class InformacionFactura(

        @XmlElement var fechaEmision: String? = null,
        @XmlElement var dirEstablecimiento: String? = null,
        @XmlElement var contribuyenteEspecial: String? = null,
        @XmlElement var obligadoContabilidad: String? = null,
        @XmlElement var tipoIdentificacionComprador: String? = null,
        @XmlElement var razonSocialComprador: String? = null,
        @XmlElement var identificacionComprador: String? = null,
        @XmlElement var totalSinImpuestos: BigDecimal? = null,
        @XmlElement var totalDescuento: BigDecimal? = null

)