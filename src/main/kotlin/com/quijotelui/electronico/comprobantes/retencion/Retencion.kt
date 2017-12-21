package com.quijotelui.electronico.comprobantes.retencion

import com.quijotelui.electronico.comprobantes.InformacionTributaria
import comprobantes.InformacionAdicional
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlType

@XmlRootElement
@XmlType(propOrder = arrayOf("infoTributaria", "infoCompRetencion", "impuestos", "infoAdicional"))
class Retencion{

    @XmlAttribute
    private var id : String? = null

    @XmlAttribute
    private var version : String? = null

    @XmlElement(name = "infoTributaria")
    private var informacionTributaria = InformacionTributaria()

    @XmlElement(name = "infoCompRetencion")
    private var informacionRetencion = InformacionRetencion()

    @XmlElement
    private var impuestos = Impuestos()

    @XmlElement(name = "infoAdicional")
    private  var informacionAdicional = InformacionAdicional()

}