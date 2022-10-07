package com.example.domain.usecase.userinfo

import com.example.domain.models.RefreshToken
import com.example.domain.repository.NewsApiRepository

class UpdateAccessTokenUseCase(private val newsApiRepository: NewsApiRepository) {

    suspend fun execute(refreshToken: RefreshToken) = newsApiRepository.updateTokens(refreshToken)
}