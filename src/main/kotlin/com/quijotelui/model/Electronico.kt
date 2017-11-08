package com.quijotelui.model

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

    @Column(name = "codigo", nullable = false)
    var codigo : String? = null

    @Column(name = "numero", nullable = false)
    var numero : String? = null

    @Column(name = "numero_autorizacion")
    var numeroAutorizacion : String? = null

    @Column(name = "fecha_autorizacion")
    var fechaAutorizacion : String? = null

    @Column(name = "observacion")
    var observacion : String? = null

    @Column(name = "estado")
    var estado : String? = null

}