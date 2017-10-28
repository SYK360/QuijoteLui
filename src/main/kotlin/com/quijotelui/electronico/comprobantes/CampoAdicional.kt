package comprobantes

import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlRootElement
import javax.xml.bind.annotation.XmlValue

@XmlRootElement
class CampoAdicional {

    @XmlAttribute
    private var nombre : String? = null

    @XmlValue
    private var valor : String? = null

    fun setNombre(nombre : String) {
        this.nombre = nombre
    }

    fun setValor(valor : String) {
        this.valor = valor
    }

}