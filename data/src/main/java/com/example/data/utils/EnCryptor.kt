package com.example.data.utils

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class EnCryptor(context: Context) {

    companion object {
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val ANDROID_KEY_STORE = "AndroidKeyStore"
    }

    private lateinit var encryption: ByteArray
    private lateinit var iv: ByteArray
    private val encodingData = EncodingData(context)

    fun encryptText(alias: String, textToEncrypt: String): ByteArray? {
        val cipher: Cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(alias))
        iv = cipher.iv
        encodingData.saveIv(iv)
        return cipher.doFinal(textToEncrypt.toByteArray(charset("UTF-8"))).also {
            encryption = it
            encodingData.saveEncryption(encryption)
        }
    }

    private fun getSecretKey(alias: String): SecretKey {
        val keyGenerator: KeyGenerator = KeyGenerator
            .getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE)
        val keyGeneratorParameterSpec =
            KeyGenParameterSpec.Builder(
                alias,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()
        keyGenerator.init(keyGeneratorParameterSpec)
        return keyGenerator.generateKey()
    }

    fun getEncryption(): ByteArray {
        return if (::encryption.isInitialized) {
            encryption
        } else {
            encodingData.getEncryption()
        }
    }

    fun getIv(): ByteArray {
        return if(::iv.isInitialized) {
            iv
        } else {
            encodingData.getIv()
        }
    }
}