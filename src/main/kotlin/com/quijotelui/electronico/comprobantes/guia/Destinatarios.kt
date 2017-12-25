package com.quijotelui.electronico.comprobantes.guia

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Destinatarios {

    @XmlElement
    private var destinatario: MutableCollection<Destinatario> = mutableListOf()

    fun setDestinatario(destinatario : Destinatario){
        this.destinatario.add(destinatario)
    }

}