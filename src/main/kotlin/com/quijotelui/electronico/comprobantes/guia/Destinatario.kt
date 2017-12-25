package com.quijotelui.electronico.comprobantes.guia

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf(
        "identificacionDestinatario",
        "razonSocialDestinatario",
        "dirDestinatario",
        "motivoTraslado",
        "docAduaneroUnico",
        "codEstabDestino",
        "ruta",
        "codDocSustento",
        "numDocSustento",
        "numAutDocSustento",
        "fechaEmisionDocSustento",
        "detalles"
))
class Destinatario (

        @XmlElement var identificacionDestinatario: String? = null,
        @XmlElement var  razonSocialDestinatario: String? = null,
        @XmlElement var dirDestinatario: String? = null,
        @XmlElement var motivoTraslado: String? = null,
        @XmlElement var docAduaneroUnico: String? = null,
        @XmlElement var codEstabDestino: String? = null,
        @XmlElement var ruta: String? = null,
        @XmlElement var codDocSustento: String? = null,
        @XmlElement var numDocSustento: String? = null,
        @XmlElement var numAutDocSustento: String? = null,
        @XmlElement var fechaEmisionDocSustento: String? = null

)
{

    @XmlElement
    private var detalles = Detalles()

    fun setDetalles(detalles : Detalles) {
        this.detalles = detalles
    }

}