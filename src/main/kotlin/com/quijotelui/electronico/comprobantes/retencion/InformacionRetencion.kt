package com.quijotelui.electronico.comprobantes.retencion

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf(
        "fechaEmision",
        "dirEstablecimiento",
        "contribuyenteEspecial",
        "obligadoContabilidad",
        "tipoIdentificacionSujetoRetenido",
        "razonSocialSujetoRetenido",
        "identificacionSujetoRetenido",
        "periodoFiscal"
))

class InformacionRetencion (

    @XmlElement var fechaEmision: String? = null,
    @XmlElement var dirEstablecimiento: String? = null,
    @XmlElement var contribuyenteEspecial: String? = null,
    @XmlElement var obligadoContabilidad: String? = null,
    @XmlElement var tipoIdentificacionSujetoRetenido: String? = null,
    @XmlElement var razonSocialSujetoRetenido: String? = null,
    @XmlElement var identificacionSujetoRetenido: String? = null,
    @XmlElement var periodoFiscal: String? = null

)