package com.example.domain.usecase.accesstoken

import com.example.domain.repository.AccessTokenRepository

class DeleteAccessTokenUseCase(private val accessTokenRepository: AccessTokenRepository) {

    fun execute() = accessTokenRepository.deleteToken()
}