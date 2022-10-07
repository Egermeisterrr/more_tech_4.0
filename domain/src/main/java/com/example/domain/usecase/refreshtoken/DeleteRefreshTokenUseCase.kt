package com.example.domain.usecase.refreshtoken

import com.example.domain.repository.RefreshTokenRepository

class DeleteRefreshTokenUseCase(private val refreshTokenRepository: RefreshTokenRepository) {

    fun execute() = refreshTokenRepository.deleteToken()
}