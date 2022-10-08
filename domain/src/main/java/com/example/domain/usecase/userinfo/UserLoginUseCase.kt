package com.example.domain.usecase.userinfo

import com.example.domain.models.ProfileLoginModel
import com.example.domain.models.ProfileModel
import com.example.domain.repository.NewsApiRepository

class UserLoginUseCase(private val newsApiRepository: NewsApiRepository) {

    suspend fun execute(user: ProfileLoginModel) = newsApiRepository.userLogin(user)
}