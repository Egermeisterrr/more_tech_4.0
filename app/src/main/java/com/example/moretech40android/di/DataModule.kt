package com.example.moretech40android.di

import android.content.Context
import com.example.data.api.NewsApi
import com.example.data.repository.AccessTokenRepositoryImpl
import com.example.data.repository.NewsApiRepositoryImpl
import com.example.data.repository.RefreshTokenRepositoryImpl
import com.example.data.repository.SplashScreenRepositoryImpl
import com.example.domain.repository.AccessTokenRepository
import com.example.domain.repository.NewsApiRepository
import com.example.domain.repository.RefreshTokenRepository
import com.example.domain.repository.SplashScreenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideSplashScreenRepository(@ApplicationContext context: Context): SplashScreenRepository =
        SplashScreenRepositoryImpl(context = context)

    @Provides
    @Singleton
    fun provideNewsApiRepository(newsApi: NewsApi): NewsApiRepository =
        NewsApiRepositoryImpl(api = newsApi)

    @Provides
    @Singleton
    fun provideUserInfoRepository(@ApplicationContext context: Context): AccessTokenRepository =
        AccessTokenRepositoryImpl(context = context)

    @Provides
    @Singleton
    fun provideRefreshTokenRepository(@ApplicationContext context: Context): RefreshTokenRepository =
        RefreshTokenRepositoryImpl(context = context)
}