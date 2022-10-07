package com.example.domain.usecase.accesstoken

import com.example.domain.repository.AccessTokenRepository

class GetAccessTokenUseCase(private val accessTokenRepository: AccessTokenRepository) {

    fun execute() = accessTokenRepository.getToken()
}