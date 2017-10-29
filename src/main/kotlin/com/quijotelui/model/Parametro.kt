package com.quijotelui.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ele_parametros")
class Parametro : Serializable {

    @Id
    @Column(name = "id")
    var id : Long? = null

    @Column(name = "nombre")
    var nombre : String? = null

    @Column(name = "valor")
    var valor : String? = null

    @Column(name = "observacion")
    var observacion : String? = null

    @Column(name = "estado")
    var estado : String? = null

}