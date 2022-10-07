package com.example.data.api

import  com.example.domain.models.ProfileModel
import com.example.domain.models.RefreshToken
import com.example.domain.models.Tokens
import com.example.domain.models.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NewsApi {

    @POST("/auth/login")
    suspend fun userLogin(@Body user: ProfileModel): Response<Tokens>

    @GET("/profile")
    suspend fun getProfile(@Header("Authorization") authToken: String): Response<UserData>

    @POST("/auth/update-tokens?get-refresh-also=true")
    suspend fun updateTokens(@Body refreshToken: RefreshToken): Response<Tokens>

    @POST("/auth/register")
    suspend fun registration(@Body user: ProfileModel): Response<Tokens>
}