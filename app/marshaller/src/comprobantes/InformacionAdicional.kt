package comprobantes

import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class InformacionAdicional {

    @XmlElement
    private var campoAdicional: MutableCollection<CampoAdicional> = mutableListOf()

    fun setCampoAdicional(campoAdicional : CampoAdicional) {
        this.campoAdicional.add(campoAdicional)
    }

}