package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.repository.AccessTokenRepository

class AccessTokenRepositoryImpl(context: Context): AccessTokenRepository {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_NAME_TOKEN, token).apply()
    }

    override fun getToken(): String {
        return sharedPreferences.getString(KEY_NAME_TOKEN, "")!!
    }

    override fun deleteToken() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        private const val SHARED_PREF_NAME = "USERINFO"
        private const val KEY_NAME_TOKEN = "Token"
    }
}