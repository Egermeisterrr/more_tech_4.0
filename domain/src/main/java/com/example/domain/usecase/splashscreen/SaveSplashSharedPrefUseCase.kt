package com.example.domain.usecase.splashscreen

import com.example.domain.repository.SplashScreenRepository

class SaveSplashSharedPrefUseCase(private val splashScreenRepository: SplashScreenRepository) {

    fun execute() = splashScreenRepository.saveSplashSharedPref()
}