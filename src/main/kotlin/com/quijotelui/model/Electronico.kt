package com.quijotelui.model

import org.hibernate.annotations.Immutable
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ele_documentos_electronicos")
class Electronico : Serializable {

    @Id
    @Column(name = "id")
    var id : Long? = null

    @Column(name = "codigo")
    var codigo : String? = null

    @Column(name = "numero")
    var numero : String? = null

    @Column(name = "codigo")
    var numero_autorizacion : String? = null

    @Column(name = "numero")
    var fecha_autorizacion : String? = null

    @Column(name = "codigo")
    var observacion : String? = null

    @Column(name = "numero")
    var estado : String? = null

}