package com.quijotelui.electronico.comprobantes.nota.debito

import java.math.BigDecimal
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf(
        "razon",
        "valor"
))
class Motivo(

        @XmlElement var razon: String? = null,
        @XmlElement var valor: BigDecimal? = null

)