package com.quijotelui.electronico.comprobantes.retencion

import java.math.BigDecimal
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf(
        "codigo",
        "codigoRetencion",
        "baseImponible",
        "porcentajeRetener",
        "valorRetenido",
        "codDocSustento",
        "numDocSustento",
        "fechaEmisionDocSustento"))

class Impuesto (

    @XmlElement var codigo: String? = null,
    @XmlElement var codigoRetencion: String? = null,
    @XmlElement var baseImponible: BigDecimal? = null,
    @XmlElement var porcentajeRetener: BigDecimal? = null,
    @XmlElement var valorRetenido: BigDecimal? = null,
    @XmlElement var codDocSustento: String? = null,
    @XmlElement var numDocSustento: String? = null,
    @XmlElement var fechaEmisionDocSustento: String? = null

)