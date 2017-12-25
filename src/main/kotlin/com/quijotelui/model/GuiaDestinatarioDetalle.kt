package com.quijotelui.model

import org.hibernate.annotations.Immutable
import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Immutable
@Table(name = "v_ele_guias_destinatario_detalle")
class GuiaDestinatarioDetalle : Serializable  {
}