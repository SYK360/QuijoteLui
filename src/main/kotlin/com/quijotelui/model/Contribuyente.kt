package com.quijotelui.model

import org.hibernate.annotations.Immutable
import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Immutable
@Table(name = "v_ele_contribuyentes")
class Contribuyente : Serializable{

    @Id
    @Column(name = "id")
    var id : Long? = null

    @Column(name = "razon_social")
    var razonSocial : String? = null

    @Column(name = "nombre_comercial")
    var nombreComercial : String? = null

    @Column(name = "ruc")
    var ruc : String? = null

    @Column(name = "direccion")
    var direccion : String? = null

    @Column(name = "obligado_contabilidad")
    var obligadoContabilidad : String? = null

    @Column(name = "contribuyente_especial")
    var contribuyenteEspecial : String? = null

}