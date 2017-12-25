package com.quijotelui.electronico.comprobantes.nota.credito

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Detalles {
    @XmlElement
    private var detalle: MutableCollection<Detalle> = mutableListOf()

    fun setDetalle(detalle : Detalle) {
        this.detalle.add(detalle)
    }
}