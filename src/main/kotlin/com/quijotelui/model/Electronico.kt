package com.quijotelui.model

import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "ele_documentos_electronicos")
class Electronico : Serializable {

    @Id
    /*
    Secuencia para Oracle
     */
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")  //Oracle
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "SEQ_ELE_DOCUMENTOS") //Oracle
    @GeneratedValue(strategy = GenerationType.AUTO)//MySql
    @Column(name = "id")
    var id : Long? = null

    @Column(name = "codigo", nullable = false)
    var codigo : String? = null

    @Column(name = "numero", nullable = false)
    var numero : String? = null

    @Column(name = "numero_autorizacion")
    var numeroAutorizacion : String? = null

    @Column(name = "fecha_autorizacion")
    @Temporal(TemporalType.TIMESTAMP)
//    @Type(type="date")
    var fechaAutorizacion : Date? = null

    @Column(name = "observacion")
    var observacion : String? = null

    @Column(name = "estado")
    var estado : String? = null

}