package com.example.domain.usecase.refreshtoken

import com.example.domain.repository.RefreshTokenRepository

class EncryptRefreshTokenUseCase(private val refreshTokenRepository: RefreshTokenRepository) {

    fun execute(token: String) = refreshTokenRepository.encryptToken(token)
}