package com.quijotelui.electronico.comprobantes.nota.credito

import java.math.BigDecimal
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf(
        "codigo",
        "codigoPorcentaje",
        "baseImponible",
        "valor"))

class TotalImpuesto (
        @XmlElement var codigo: String? = null,
        @XmlElement var codigoPorcentaje: String? = null,
        @XmlElement var baseImponible: BigDecimal? = null,
        @XmlElement var valor: BigDecimal? = null
)