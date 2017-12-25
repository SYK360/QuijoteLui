package com.quijotelui.electronico.comprobantes.guia

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf(
        "dirEstablecimiento",
        "dirPartida",
        "razonSocialTransportista",
        "tipoIdentificacionTransportista",
        "rucTransportista",
        "obligadoContabilidad",
        "contribuyenteEspecial",
        "fechaIniTransporte",
        "fechaFinTransporte",
        "placa"
))
class InformacionGuiaRemision(

        @XmlElement var dirEstablecimiento: String? = null,
        @XmlElement var dirPartida: String? = null,
        @XmlElement var razonSocialTransportista: String? = null,
        @XmlElement var tipoIdentificacionTransportista: String? = null,
        @XmlElement var rucTransportista: String? = null,
        @XmlElement var obligadoContabilidad: String? = null,
        @XmlElement var contribuyenteEspecial: String? = null,
        @XmlElement var fechaIniTransporte: String? = null,
        @XmlElement var fechaFinTransporte: String? = null,
        @XmlElement var placa: String? = null

)