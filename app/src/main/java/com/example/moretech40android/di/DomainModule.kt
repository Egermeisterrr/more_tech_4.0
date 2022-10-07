package com.example.moretech40android.di

import com.example.domain.repository.SplashScreenRepository
import com.example.domain.usecase.GetSplashSharedPrefUseCase
import com.example.domain.usecase.SaveSplashSharedPrefUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetSplashSharedPrefUseCase(splashScreenRepository: SplashScreenRepository): GetSplashSharedPrefUseCase =
        GetSplashSharedPrefUseCase(splashScreenRepository = splashScreenRepository)

    @Provides
    fun provideSaveSplashSharedPrefUseCase(splashScreenRepository: SplashScreenRepository): SaveSplashSharedPrefUseCase =
        SaveSplashSharedPrefUseCase(splashScreenRepository = splashScreenRepository)
}