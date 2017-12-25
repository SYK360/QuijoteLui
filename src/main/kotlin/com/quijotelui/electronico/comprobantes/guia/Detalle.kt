package com.quijotelui.electronico.comprobantes.guia

import java.math.BigDecimal
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf("codigoInterno", "descripcion", "cantidad"))
class Detalle(
        @XmlElement var codigoInterno: String? = null,
        @XmlElement var descripcion: String? = null,
        @XmlElement var cantidad: BigDecimal? = null
)