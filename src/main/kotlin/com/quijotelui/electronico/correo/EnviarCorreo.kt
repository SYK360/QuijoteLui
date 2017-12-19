package com.quijotelui.electronico.correo

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.EmailAttachment
import org.apache.commons.mail.HtmlEmail
import java.io.File

class EnviarCorreo(val servidor : String, val puerto : Int) {

    val email = HtmlEmail()
    var pdfAdjunto = EmailAttachment()
    var xmlAdjunto = EmailAttachment()

    init {
        email.hostName = servidor
        email.setSmtpPort(puerto)
        email.isSSLOnConnect = true
        println("Correo inicializado")
    }

    fun remitente(correoRemitente : String, clave : String) {
        email.setFrom(correoRemitente)
        email.setAuthenticator(DefaultAuthenticator(correoRemitente, clave))
    }

    /*
      Para enviar un adjunto, ejemplo
      val file = File("/tmp/pdf/archivo.pdf")
    */
    private fun adjunto(rutaArchivo : File) : EmailAttachment {
        val adjunto = EmailAttachment()

        adjunto.path = rutaArchivo.absolutePath
        adjunto.name = rutaArchivo.name
        adjunto.disposition = EmailAttachment.ATTACHMENT

        return adjunto
    }


    fun xml(rutaArchivo : File, descripcion : String) {
        xmlAdjunto = adjunto(rutaArchivo)
        xmlAdjunto.description = descripcion
    }

    fun pdf(rutaArchivo : File, descripcion : String) {
        pdfAdjunto = adjunto(rutaArchivo)
        pdfAdjunto.description = descripcion
    }

    fun enviar(correoDestinatario : String, asunto : String ,mensaje : String) {
        email.addTo(correoDestinatario)
        email.subject = asunto
        email.setTextMsg(mensaje)
        email.attach(pdfAdjunto)
        email.attach(xmlAdjunto)
        email.send()
        println("Enviado a $correoDestinatario")
    }


}

