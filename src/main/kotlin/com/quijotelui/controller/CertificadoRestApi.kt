package com.quijotelui.controller

import com.quijotelui.electronico.util.Parametros
import com.quijotelui.key.KeyStoreHistory
import com.quijotelui.service.IParametroService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.quijotelui.key.KeyInfo

@RestController
@RequestMapping("/rest/v1")
class CertificadoRestApi {

    @Autowired
    lateinit var parametroService : IParametroService

    @CrossOrigin(value = "*")
    @GetMapping("/keyinfo")
    fun getKeyInfo() : ResponseEntity<Map<String, String>> {
        val keyStoreHistory = KeyStoreHistory()
        val pathElectronica = Parametros.getRuta(parametroService.findByNombre("Firma Electrónica"))
        val keyElectronica = Parametros.getClaveElectronica(parametroService.findByNombre("Clave Firma Electrónica"))

        val k = keyStoreHistory.getInformacion(pathElectronica, keyElectronica)

        val readWriteMap :HashMap<String, String> = hashMapOf()
//        readWriteMap = hashMapOf("foo" to "a", "bar" to "s") //Sin iterator
        for (keyInfo in k) {
            println(keyInfo.nombre + " -> " + keyInfo.valor)
            readWriteMap.put(keyInfo.nombre, keyInfo.valor)
        }
        val snapshot: Map<String, String> = HashMap(readWriteMap)

        return ResponseEntity(snapshot, HttpStatus.OK)
    }
}