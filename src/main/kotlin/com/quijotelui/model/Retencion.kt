package com.quijotelui.model

import org.hibernate.annotations.Immutable
import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Immutable
@Table(name = "v_ele_retenciones")
class Retencion : Serializable {

    @Id
    @Column(name = "id")
    var id : Long? = null

    @Column(name = "id_contribuyente")
    var idContribuyente : Long? = null

    @Column(name = "codigo")
    var codigo : String? = null

    @Column(name = "numero")
    var numero : String? = null

    @Column(name = "codigo_documento")
    var codigoDocumento : String? = null

    @Column(name = "establecimiento")
    var establecimiento : String? = null

    @Column(name = "punto_emision")
    var puntoEmision : String? = null

    @Column(name = "secuencial")
    var secuencial : String? = null

    @Column(name = "fecha")
    @Type(type="date")
    var fecha : Date? = null

    @Column(name = "tipo_documento")
    var tipoDocumento : String? = null

    @Column(name = "documento")
    var documento : String? = null

    @Column(name = "razon_social")
    var razonSocial : String? = null

    @Column(name = "periodo_fiscal")
    var periodoFiscal : String? = null

}