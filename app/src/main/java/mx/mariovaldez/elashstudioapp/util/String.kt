package mx.mariovaldez.elashstudioapp.util

import android.util.Base64
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.text.Charsets.UTF_8

fun String.encrypt(): String {
    val secretKeySpec = SecretKeySpec("ElashDatabaseKey".toByteArray(), "AES")
    val iv = ByteArray(16)
    val charArray = this.toCharArray()
    for (i in charArray.indices) {
        iv[i] = charArray[i].code.toByte()
    }
    val ivParameterSpec = IvParameterSpec(iv)

    val cipher = Cipher.getInstance("AES/GCM/NoPadding")
    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)

    val encryptedValue = cipher.doFinal(this.toByteArray())
    return Base64.encodeToString(encryptedValue, Base64.DEFAULT)
}

private fun md5(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))
private fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }

fun String.hash(): String {
    this.encrypt()
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}
fun String.decrypt(): String {
    val secretKeySpec = SecretKeySpec("ElashDatabaseKey".toByteArray(), "AES")
    val iv = ByteArray(16)
    val charArray = this.toCharArray()
    for (i in charArray.indices) {
        iv[i] = charArray[i].code.toByte()
    }
    val ivParameterSpec = IvParameterSpec(iv)

    val cipher = Cipher.getInstance("AES/GCM/NoPadding")
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)

    val decryptedByteValue = cipher.doFinal(Base64.decode(this, Base64.DEFAULT))
    return String(decryptedByteValue)
}
