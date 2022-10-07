package com.example.domain.repository

interface SplashScreenRepository {

    fun getSplashSharedPref(): Boolean

    fun saveSplashSharedPref()
}