package com.quijotelui.electronico.xml.comprobantes

import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class Factura {

    @XmlAttribute
    var id : String? = null
}