package com.example.data.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import android.util.Log

class EncodingData(context: Context) {

    private val sharedPreferencesEncryption: SharedPreferences =
        context.getSharedPreferences(ENCRYPTION_SHARED_PREF_NAME, Context.MODE_PRIVATE)

    private val sharedPreferencesIV: SharedPreferences =
        context.getSharedPreferences(IV_SHARED_PREF_NAME, Context.MODE_PRIVATE)

    fun saveEncryption(encryption: ByteArray) {
        sharedPreferencesEncryption.edit().putString(
            ENCRYPTION_KEY_NAME, Base64.encodeToString(encryption, Base64.DEFAULT)
        ).apply()
    }

    fun getEncryption(): ByteArray {
        return Base64.decode(sharedPreferencesEncryption.getString(ENCRYPTION_KEY_NAME, ""), Base64.DEFAULT)
    }

    fun saveIv(iv: ByteArray) {
        sharedPreferencesIV.edit().putString(
            IV_KEY_NAME, Base64.encodeToString(iv, Base64.DEFAULT)
        ).apply()
    }

    fun getIv(): ByteArray {
        Log.i("Tag", sharedPreferencesIV.getString(IV_KEY_NAME, "").toString())
        return Base64.decode(sharedPreferencesIV.getString(IV_KEY_NAME, ""), Base64.DEFAULT)
    }

    companion object {
        private const val ENCRYPTION_SHARED_PREF_NAME = "Encryption"
        private const val ENCRYPTION_KEY_NAME = "Encryption"
        private const val IV_SHARED_PREF_NAME = "Iv"
        private const val IV_KEY_NAME = "Iv"
    }
}