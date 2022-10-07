package com.example.domain.usecase.userinfo

import com.example.domain.repository.NewsApiRepository

class GetProfileUseCase(private val newsApiRepository: NewsApiRepository) {

    suspend fun execute(token: String) = newsApiRepository.getProfile(token)
}