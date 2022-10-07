package com.example.domain.usecase.userinfo

import com.example.domain.models.ProfileModel
import com.example.domain.repository.NewsApiRepository

class UserLoginUseCase(private val newsApiRepository: NewsApiRepository) {

    suspend fun execute(user: ProfileModel) = newsApiRepository.userLogin(user)
}