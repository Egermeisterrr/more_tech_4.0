package com.example.data.repository

import com.example.data.api.NewsApi
import com.example.domain.models.*
import com.example.domain.repository.NewsApiRepository
import java.io.IOException

class NewsApiRepositoryImpl(private val api: NewsApi): NewsApiRepository {

    override suspend fun userLogin(user: ProfileLoginModel): Tokens {
        val response = api.userLogin(user)
        if (response.body() != null) {
            return response.body()!!
        } else {
            throw IOException(response.code().toString())
        }
    }

    override suspend fun getProfile(token: String): UserData {
        val response = api.getProfile("Bearer $token")
        if (response.body() != null) {
            return response.body()!!
        } else {
            throw IOException(response.code().toString())
        }
    }

    override suspend fun updateTokens(refreshToken: RefreshToken): String {
        val response = api.updateTokens(refreshToken)
        if (response.body() != null) {
            return response.body()!!.access_token
        } else {
            throw IOException(response.code().toString())
        }
    }

    override suspend fun registration(user: ProfileModel): Tokens {
        val response = api.registration(user)
        if (response.body() != null) {
            return response.body()!!
        } else {
            throw IOException(response.code().toString())
        }
    }

    override suspend fun getDigest(): NewsModel {
        val response = api.digest()
        if (response.body() != null) {
            return response.body()!!
        } else {
            throw IOException(response.code().toString())
        }
    }

    override suspend fun getTrends(): NewsModel {
        val response = api.trends()
        if (response.body() != null) {
            return response.body()!!
        } else {
            throw IOException(response.code().toString())
        }
    }

    override suspend fun getInsight(): NewsModel {
        val response = api.insights()
        if (response.body() != null) {
            return response.body()!!
        } else {
            throw IOException(response.code().toString())
        }
    }
}