package comprobantes.factura

import java.math.BigDecimal
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf(
        "fechaEmision",
        "dirEstablecimiento",
        "contribuyenteEspecial",
        "obligadoContabilidad",
        "tipoIdentificacionComprador",
        "razonSocialComprador",
        "identificacionComprador",
        "direccionComprador",
        "totalSinImpuestos",
        "totalDescuento",
        "totalConImpuestos",
        "propina",
        "importeTotal",
        "moneda",
        "pagos"))

class InformacionFactura(

        @XmlElement var fechaEmision: String? = null,
        @XmlElement var dirEstablecimiento: String? = null,
        @XmlElement var contribuyenteEspecial: String? = null,
        @XmlElement var obligadoContabilidad: String? = null,
        @XmlElement var tipoIdentificacionComprador: String? = null,
        @XmlElement var razonSocialComprador: String? = null,
        @XmlElement var identificacionComprador: String? = null,
        @XmlElement var direccionComprador: String? = null,
        @XmlElement var totalSinImpuestos: BigDecimal? = null,
        @XmlElement var totalDescuento: BigDecimal? = null

)
{
    @XmlElement
    private var totalConImpuestos = TotalConImpuestos()

    fun setTotalConImpuestos(totalConImpuestos : TotalConImpuestos) {
        this.totalConImpuestos = totalConImpuestos
    }

    @XmlElement
    private var propina : BigDecimal? = null

    fun setPropina(propina : BigDecimal) {
        this.propina = propina
    }

    @XmlElement
    private var importeTotal : BigDecimal? = null

    fun setImporteTotal(importeTotal : BigDecimal) {
        this.importeTotal = importeTotal
    }

    @XmlElement
    private var moneda : String? = null

    fun setMoneda(moneda : String) {
        this.moneda = moneda
    }

    @XmlElement
    private var pagos = Pagos()

    fun setPagos(pagos : Pagos) {
        this.pagos = pagos
    }
}