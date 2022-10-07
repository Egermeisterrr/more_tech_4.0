package com.example.domain.usecase.accesstoken

import com.example.domain.repository.AccessTokenRepository

class SaveAccessTokenUseCase(private val accessTokenRepository: AccessTokenRepository) {

    fun execute(token: String) = accessTokenRepository.saveToken(token)
}