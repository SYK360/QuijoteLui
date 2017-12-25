package com.quijotelui.electronico.comprobantes.nota.debito

import java.math.BigDecimal
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder= arrayOf(
        "formaPago",
        "total",
        "plazo",
        "unidadTiempo"))

class Pago (

        @XmlElement var formaPago: String? = null,
        @XmlElement var total: BigDecimal? = null,
        @XmlElement var plazo: String? = null,
        @XmlElement var unidadTiempo: String? = null

        )