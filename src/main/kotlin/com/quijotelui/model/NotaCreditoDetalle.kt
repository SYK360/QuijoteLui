package com.quijotelui.model

import org.hibernate.annotations.Immutable
import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Immutable
@Table(name = "v_ele_notas_credito_detalle")
class NotaCreditoDetalle :Serializable {

    @Id
    @Column(name = "id")
    var id : Long? = null

    @Column(name = "codigo")
    var codigo : String? = null

    @Column(name = "numero")
    var numero : String? = null

    @Column(name = "codigo_interno")
    var codigoInterno : String? = null

    @Column(name = "descripcion")
    var descripcion : String? = null

    @Column(name = "cantidad")
    var cantidad : BigDecimal? = null

    @Column(name = "precio_unitario")
    var precioUnitario : BigDecimal? = null

    @Column(name = "descuento")
    var descuento : BigDecimal? = null

    @Column(name = "precio_total_sin_impuesto")
    var precioTotalSinImpuesto : BigDecimal? = null

    @Column(name = "codigo_porcentaje")
    var codigoPorcentaje : String? = null

    @Column(name = "porcentaje_iva")
    var porcentajeIva : BigDecimal? = null

    @Column(name = "valor_iva")
    var valorIva : BigDecimal? = null

}