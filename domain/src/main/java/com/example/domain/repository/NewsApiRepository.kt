package com.example.domain.repository

import com.example.domain.models.ProfileModel
import com.example.domain.models.RefreshToken
import com.example.domain.models.Tokens
import com.example.domain.models.UserData

interface NewsApiRepository {

    suspend fun userLogin(user: ProfileModel): Tokens

    suspend fun getProfile(token: String): UserData

    suspend fun updateTokens(refreshToken: RefreshToken): String

    suspend fun registration(user: ProfileModel): Tokens
}