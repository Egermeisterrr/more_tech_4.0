package com.example.moretech40android.di

import android.content.Context
import com.example.data.SplashScreenRepositoryImpl
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
}