package com.example.domain.usecase

import com.example.domain.repository.SplashScreenRepository

class GetSplashSharedPrefUseCase(private val splashScreenRepository: SplashScreenRepository) {

    fun execute() = splashScreenRepository.getSplashSharedPref()
}