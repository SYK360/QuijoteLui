package com.quijotelui.electronico.comprobantes.nota.debito

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Motivos {
    @XmlElement
    private var motivo: MutableCollection<Motivo> = mutableListOf()

    fun setMotivo(impuesto : Motivo){
        this.motivo.add(impuesto)
    }
}