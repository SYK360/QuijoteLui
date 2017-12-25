package com.quijotelui.electronico.comprobantes.nota.debito

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
        "impuestos",
        "valorTotal"
))
class InformacionNotaDebito(
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
        @XmlElement var totalSinImpuestos: BigDecimal? = null
        )
{

    @XmlElement
    private var impuestos = Impuestos()

    fun setImpuestos(impuestos : Impuestos) {
        this.impuestos = impuestos
    }

    @XmlElement
    private var valorTotal : String? = null

    fun setValorTotal(valorTotal : String) {
        this.valorTotal = valorTotal
    }

    @XmlElement
    private var pagos = Pagos()

    fun setPagos(pagos : Pagos) {
        this.pagos = pagos
    }

}