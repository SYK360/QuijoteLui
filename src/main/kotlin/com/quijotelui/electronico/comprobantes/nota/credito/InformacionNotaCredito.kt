package com.quijotelui.electronico.comprobantes.nota.credito

import java.math.BigDecimal
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf(
        "fechaEmision",
        "dirEstablecimiento",
        "tipoIdentificacionComprador",
        "razonSocialComprador",
        "identificacionComprador",
        "contribuyenteEspecial",
        "obligadoContabilidad",
        "codDocModificado",
        "numDocModificado",
        "fechaEmisionDocSustento",
        "totalSinImpuestos",
        "valorModificacion",
        "moneda",
        "totalConImpuestos",
        "motivo"
))
class InformacionNotaCredito(

        @XmlElement var fechaEmision: String? = null,
        @XmlElement var dirEstablecimiento: String? = null,
        @XmlElement var tipoIdentificacionComprador: String? = null,
        @XmlElement var razonSocialComprador: String? = null,
        @XmlElement var identificacionComprador: String? = null,
        @XmlElement var contribuyenteEspecial: String? = null,
        @XmlElement var obligadoContabilidad: String? = null,
        @XmlElement var codDocModificado: String? = null,
        @XmlElement var numDocModificado: String? = null,
        @XmlElement var fechaEmisionDocSustento: String? = null,
        @XmlElement var totalSinImpuestos: BigDecimal? = null,
        @XmlElement var valorModificacion: BigDecimal? = null,
        @XmlElement var moneda: String? = null

) {

    @XmlElement
    private var totalConImpuestos = TotalConImpuestos()

    fun setTotalConImpuestos(totalConImpuestos : TotalConImpuestos) {
        this.totalConImpuestos = totalConImpuestos
    }

    @XmlElement
    private var motivo : String? = null

    fun setMotivo(motivo : String) {
        this.motivo = motivo
    }

}