package com.quijotelui.electronico.util

import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

object Encriptar {
    private val ALGO = "AES"
    //Cambia la serie de números para generar una clave distinta
    private val keyValue = byteArrayOf('q'.toByte(), 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)

    /**
     * Encrypt a string with AES algorithm.
     *
     * @param data is a string
     * @return the encrypted string
     */
    @Throws(Exception::class)
    fun encrypt(data: String): String {
        val key = generateKey()
        val c = Cipher.getInstance(ALGO)
        c.init(Cipher.ENCRYPT_MODE, key)
        val encVal = c.doFinal(data.toByteArray())
        return Base64.getEncoder().encodeToString(encVal)
    }

    /**
     * Decrypt a string with AES algorithm.
     *
     * @param encryptedData is a string
     * @return the decrypted string
     */
    @Throws(Exception::class)
    fun decrypt(encryptedData: String): String {
        val key = generateKey()
        val c = Cipher.getInstance(ALGO)
        c.init(Cipher.DECRYPT_MODE, key)
        val decordedValue = Base64.getDecoder().decode(encryptedData)
        val decValue = c.doFinal(decordedValue)
        return String(decValue)
    }

    /**
     * Generate a new encryption key.
     */
    @Throws(Exception::class)
    private fun generateKey(): Key {
        return SecretKeySpec(keyValue, ALGO)
    }

}

/*fun main(args: Array<String>) {
    val claveEncriptada = Encriptar.encrypt("MiClaveDeLaFirma")
    val claveDesencriptada = Encriptar.decrypt(claveEncriptada)

    println("Clave encriptada: " + claveEncriptada)
    println("Clave desencriptada: " + claveDesencriptada)
}*/
