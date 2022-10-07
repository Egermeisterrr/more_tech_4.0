package com.example.domain.repository

interface AccessTokenRepository {

    fun saveToken(token: String)

    fun getToken(): String

    fun deleteToken()
}