package com.example.domain.repository

import com.example.domain.models.*

interface NewsApiRepository {

    suspend fun userLogin(user: ProfileLoginModel): Tokens

    suspend fun getProfile(token: String): UserData

    suspend fun updateTokens(refreshToken: RefreshToken): String

    suspend fun registration(user: ProfileModel): Tokens

    suspend fun getDigest(): NewsModel

    suspend fun getTrends(): NewsModel

    suspend fun getInsight(): NewsModel
}