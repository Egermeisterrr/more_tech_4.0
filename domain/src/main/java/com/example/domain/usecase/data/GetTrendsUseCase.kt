package com.example.domain.usecase.data

import com.example.domain.repository.NewsApiRepository

class GetTrendsUseCase(private val newsApiRepository: NewsApiRepository) {

    suspend fun execute() = newsApiRepository.getTrends()
}