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
@Table(name = "v_ele_pagos")
class Pago : Serializable {

    @Id
    @Column(name = "id")
    var id : Long? = null

    @Column(name = "codigo")
    var codigo : String? = null

    @Column(name = "numero")
    var numero : String? = null

    @Column(name = "forma_pago")
    var formaPago : String? = null

    @Column(name = "forma_pago_descripcion")
    var formaPagoDescripcion : String? = null

    @Column(name = "total")
    var total : BigDecimal? = null

    @Column(name = "plazo")
    var plazo : String? = null

    @Column(name = "tiempo")
    var tiempo : String? = null

}
