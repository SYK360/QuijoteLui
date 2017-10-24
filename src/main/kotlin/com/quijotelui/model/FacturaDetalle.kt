package com.quijotelui.model

import org.hibernate.annotations.Immutable
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Immutable
@Table(name = "v_ele_facturas")
class FacturaDetalle : Serializable {

    @Id
    @Column(name = "id")
    var id : Long? = null

}