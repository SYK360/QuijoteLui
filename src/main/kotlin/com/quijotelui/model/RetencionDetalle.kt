package com.quijotelui.model

import org.hibernate.annotations.Immutable
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Immutable
@Table(name = "v_ele_retenciones_detalle")
class RetencionDetalle {

    @Id
    @Column(name = "id")
    var id : Long? = null

    @Column(name = "codigo")
    var codigo : String? = null

    @Column(name = "numero")
    var numero : String? = null

    @Column(name = "tipo")
    var tipo : String? = null

    @Column(name = "codigo_sri")
    var codigoSRI : String? = null

    @Column(name = "base_imponible")
    val baseImponible : BigDecimal? = null

    @Column(name = "porcentaje")
    val porcentaje : BigDecimal? = null

    @Column(name = "valor_retenido")
    val valorRetenido : BigDecimal? = null

    @Column(name = "tipo_comprobante")
    var tipoComprobante : String? = null

    @Column(name = "numero_comprobante")
    var numeroComprobante : String? = null

    @Column(name = "fecha_comprobante")
    var fechaComprobante : String? = null

}