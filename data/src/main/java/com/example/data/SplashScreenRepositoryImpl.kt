package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.repository.SplashScreenRepository

class SplashScreenRepositoryImpl(context: Context): SplashScreenRepository {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override fun getSplashSharedPref(): Boolean {
        return sharedPreferences.getBoolean(KEY_NAME, false)
    }

    override fun saveSplashSharedPref() {
        sharedPreferences.edit().putBoolean(KEY_NAME, USER_INTERACTION).apply()
    }

    companion object {
        private const val SHARED_PREF_NAME = "Splash screen"
        private const val KEY_NAME = "Splash screen visited"
        private const val USER_INTERACTION = true
    }
}