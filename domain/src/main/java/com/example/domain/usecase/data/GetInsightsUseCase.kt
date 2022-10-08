package com.example.domain.usecase.data

import com.example.domain.repository.NewsApiRepository

class GetInsightsUseCase(private val newsApiRepository: NewsApiRepository) {

    suspend fun execute() = newsApiRepository.getInsight()
}