package com.example.data.api

import com.example.domain.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NewsApi {

    @POST("/auth/login")
    suspend fun userLogin(@Body user: ProfileLoginModel): Response<Tokens>

    @GET("users/profile")
    suspend fun getProfile(@Header("Authorization") authToken: String): Response<UserData>

    @POST("/auth/update-tokens?get-refresh-also=true")
    suspend fun updateTokens(@Body refreshToken: RefreshToken): Response<Tokens>

    @POST("/auth/register")
    suspend fun registration(@Body user: ProfileModel): Response<Tokens>

    @POST("/news/digest")
    suspend fun digest(@Body user: ProfileModel): Response<Tokens>

    @POST("/news/trends")
    suspend fun trends(@Body user: ProfileModel): Response<Tokens>

    @POST("/auth/insights")
    suspend fun insights(@Body user: ProfileModel): Response<Tokens>
}