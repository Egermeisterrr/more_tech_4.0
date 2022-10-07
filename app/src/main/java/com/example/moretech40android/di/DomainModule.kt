package com.example.moretech40android.di

import com.example.domain.repository.AccessTokenRepository
import com.example.domain.repository.NewsApiRepository
import com.example.domain.repository.RefreshTokenRepository
import com.example.domain.repository.SplashScreenRepository
import com.example.domain.usecase.accesstoken.DeleteAccessTokenUseCase
import com.example.domain.usecase.accesstoken.GetAccessTokenUseCase
import com.example.domain.usecase.accesstoken.SaveAccessTokenUseCase
import com.example.domain.usecase.refreshtoken.DecryptRefreshTokenUseCase
import com.example.domain.usecase.refreshtoken.DeleteRefreshTokenUseCase
import com.example.domain.usecase.refreshtoken.EncryptRefreshTokenUseCase
import com.example.domain.usecase.splashscreen.GetSplashSharedPrefUseCase
import com.example.domain.usecase.splashscreen.SaveSplashSharedPrefUseCase
import com.example.domain.usecase.userinfo.GetProfileUseCase
import com.example.domain.usecase.userinfo.RegistrationUseCase
import com.example.domain.usecase.userinfo.UpdateAccessTokenUseCase
import com.example.domain.usecase.userinfo.UserLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetSharedPrefUseCase(splashScreenRepository: SplashScreenRepository): GetSplashSharedPrefUseCase =
        GetSplashSharedPrefUseCase(splashScreenRepository = splashScreenRepository)

    @Provides
    fun provideSaveSharedPrefUseCase(splashScreenRepository: SplashScreenRepository): SaveSplashSharedPrefUseCase =
        SaveSplashSharedPrefUseCase(splashScreenRepository = splashScreenRepository)

    @Provides
    fun provideUserLoginUseCase(newsApiRepository: NewsApiRepository): UserLoginUseCase =
        UserLoginUseCase(newsApiRepository = newsApiRepository)

    @Provides
    fun provideGetProfileUseCase(newsApiRepository: NewsApiRepository): GetProfileUseCase =
        GetProfileUseCase(newsApiRepository = newsApiRepository)

    @Provides
    fun provideUpdateAccessTokenUseCase(newsApiRepository: NewsApiRepository): UpdateAccessTokenUseCase =
        UpdateAccessTokenUseCase(newsApiRepository = newsApiRepository)

    @Provides
    fun provideRegistrationUseCase(newsApiRepository: NewsApiRepository): RegistrationUseCase =
        RegistrationUseCase(newsApiRepository = newsApiRepository)

    @Provides
    fun provideGetAccessTokenUseCase(accessTokenRepository: AccessTokenRepository): GetAccessTokenUseCase =
        GetAccessTokenUseCase(accessTokenRepository = accessTokenRepository)

    @Provides
    fun provideDeleteAccessTokenUseCase(accessTokenRepository: AccessTokenRepository): DeleteAccessTokenUseCase =
        DeleteAccessTokenUseCase(accessTokenRepository = accessTokenRepository)

    @Provides
    fun provideSaveAccessTokenUseCase(accessTokenRepository: AccessTokenRepository): SaveAccessTokenUseCase =
        SaveAccessTokenUseCase(accessTokenRepository = accessTokenRepository)

    @Provides
    fun provideEncryptRefreshTokenUseCase(refreshTokenRepository: RefreshTokenRepository): EncryptRefreshTokenUseCase =
        EncryptRefreshTokenUseCase(refreshTokenRepository = refreshTokenRepository)

    @Provides
    fun provideDecryptRefreshTokenUseCase(refreshTokenRepository: RefreshTokenRepository): DecryptRefreshTokenUseCase =
        DecryptRefreshTokenUseCase(refreshTokenRepository = refreshTokenRepository)

    @Provides
    fun provideDeleteRefreshTokenUseCase(refreshTokenRepository: RefreshTokenRepository): DeleteRefreshTokenUseCase =
        DeleteRefreshTokenUseCase(refreshTokenRepository = refreshTokenRepository)
}