package com.quijotelui.electronico.comprobantes

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(name = "", propOrder = arrayOf(
        "ambiente",
        "tipoEmision",
        "razonSocial",
        "nombreComercial",
        "ruc",
        "claveAcceso",
        "codDoc",
        "estab",
        "ptoEmi",
        "secuencial",
        "dirMatriz"))

class InformacionTributaria(

        @XmlElement var ambiente: String? = null,
        @XmlElement var tipoEmision: String? = null,
        @XmlElement var razonSocial: String? = null,
        @XmlElement var nombreComercial: String? = null,
        @XmlElement var ruc: String? = null,
        @XmlElement var claveAcceso: String? = null,
        @XmlElement var codDoc: String? = null,
        @XmlElement var estab: String? = null,
        @XmlElement var ptoEmi: String? = null,
        @XmlElement var secuencial: String? = null,
        @XmlElement var dirMatriz: String? = null

)

