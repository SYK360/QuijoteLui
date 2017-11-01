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
@Table(name = "v_ele_impuestos")
class Impuesto : Serializable {

    @Id
    @Column(name = "id")
    var id : Long? = null

    @Column(name = "codigo")
    var codigo : String? = null

    @Column(name = "numero")
    var numero : String? = null

    @Column(name = "codigo_impuesto")
    var codigoImpuesto : String? = null

    @Column(name = "codigo_porcentaje")
    var codigoPorcentaje : String? = null

    @Column(name = "base_imponible")
    var baseImponible : BigDecimal? = null

    @Column(name = "tarifa")
    var tarifa : BigDecimal? = null

    @Column(name = "valor")
    var valor : BigDecimal? = null
}
