package br.com.gutierre.productsdb.utils

// ref: https://www.baeldung.com/java-aes-encryption-decryption

import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

const val secret = "1234567890123456"
const val algorithm = "AES/CBC/PKCS5Padding"
val key = SecretKeySpec(secret.toByteArray(), "AES")
val iv = IvParameterSpec(secret.substring(0, 16).toByteArray())

fun decrypt(algorithm: String, cipherText: String, key: SecretKeySpec, iv: IvParameterSpec): String {
    val cipher = Cipher.getInstance(algorithm)
    cipher.init(Cipher.DECRYPT_MODE, key, iv)
    val plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText))
    return String(plainText)
}

fun encrypt(algorithm: String, inputText: String, key: SecretKeySpec, iv: IvParameterSpec): String {
    val cipher = Cipher.getInstance(algorithm)
    cipher.init(Cipher.ENCRYPT_MODE, key, iv)
    val cipherText = cipher.doFinal(inputText.toByteArray())
    return Base64.getEncoder().encodeToString(cipherText)
}

val cipherText = encrypt(algorithm, "abcdefghigklmnopqrstuvwxyz0123456789", key, iv)
val plainText = decrypt(algorithm, cipherText, key, iv)

fun decryptData(data: String): String {
    /*val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    val secretKeySpec = SecretKeySpec(secret.toByteArray(), "AES")
    val ivParameterSpec = IvParameterSpec(secret.substring(0, 16).toByteArray())
    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
    val decrypted = cipher.doFinal(Base64.getDecoder().decode(data))
    return String(decrypted)*/
    return try {
        decrypt(algorithm, data, key, iv)
    } catch (e: Exception) {
        println(e)
        ""
    }
}

fun encryptPass(password: String): String? {
    val encoders: MutableMap<String, PasswordEncoder> = HashMap()
    val pbkdf2Encoder =
        Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256)

    encoders["pbkdf2"] = pbkdf2Encoder
    val passwordEncoder = DelegatingPasswordEncoder("pbkdf2", encoders)
    passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder)
    val result = passwordEncoder.encode(password)
    println("My Hash $result")
    return result
}