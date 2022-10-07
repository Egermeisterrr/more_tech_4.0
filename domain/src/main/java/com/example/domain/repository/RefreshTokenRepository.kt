package com.example.domain.repository

interface RefreshTokenRepository {

    fun encryptToken(token: String)

    fun decryptToken(): String

    fun deleteToken()
}