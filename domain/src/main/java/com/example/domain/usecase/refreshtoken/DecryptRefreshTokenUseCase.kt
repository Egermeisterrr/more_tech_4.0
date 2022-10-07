package com.example.domain.usecase.refreshtoken

import com.example.domain.repository.RefreshTokenRepository

class DecryptRefreshTokenUseCase(private val refreshTokenRepository: RefreshTokenRepository) {

    fun execute() = refreshTokenRepository.decryptToken()
}