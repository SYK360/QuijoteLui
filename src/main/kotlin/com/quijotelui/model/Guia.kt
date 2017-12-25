package com.quijotelui.model

import org.hibernate.annotations.Immutable
import org.hibernate.annotations.Type
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Immutable
@Table(name = "v_ele_guias")
class Guia : Serializable {

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

    @Column(name = "fecha_fin")
    @Type(type="date")
    var fechaFin : Date? = null

    @Column(name = "direccion_partida")
    var direccionPartida : String? = null

    @Column(name = "razon_social_transportista")
    var razonSocialTransportista : String? = null

    @Column(name = "tipo_documento")
    var tipoDocumento : String? = null

    @Column(name = "documento")
    var documento : String? = null

    @Column(name = "placa")
    var placa : String? = null

    @Column(name = "direccion_establecimiento")
    var direccionEstablecimiento : String? = null

}