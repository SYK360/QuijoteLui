package com.quijotelui.model

import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ele_truncados")
class Truncado : Serializable {

    @Id
    @Column(name = "id")
    var id : Long? = null

    @Column(name = "codigo")
    var codigo : String? = null

    @Column(name = "numero")
    var numero : String? = null

    @Column(name = "fecha")
    @Type(type="date")
    var fecha : Date? = null

}