package com.example.moretech40android.presentation.fragments.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.domain.usecase.refreshtoken.DecryptRefreshTokenUseCase
import com.example.domain.usecase.splashscreen.GetSplashSharedPrefUseCase
import com.example.domain.usecase.splashscreen.SaveSplashSharedPrefUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getSplashSharedPrefUseCase: GetSplashSharedPrefUseCase,
    private val saveSplashSharedPrefUseCase: SaveSplashSharedPrefUseCase,
    private val decryptRefreshTokenUseCase: DecryptRefreshTokenUseCase
) : ViewModel() {

    private val _navEvent = MutableLiveData<NavDirections>()
    val navEvent: LiveData<NavDirections> = _navEvent

    private fun toSignInNavigation() {
        _navEvent.postValue(
            SplashScreenFragmentDirections.actionSplashScreenFragmentToSignInFragment()
        )
    }

    private fun toOnboardingNavigation() {
        _navEvent.postValue(
            SplashScreenFragmentDirections.actionSplashScreenFragmentToOnboardingFragment()
        )
    }

    private fun toMainNavigation() {
        _navEvent.postValue(
            SplashScreenFragmentDirections.actionSplashScreenFragmentToMainFragment()
        )
    }

    private fun checkToken() {
        try {
            decryptRefreshTokenUseCase.execute()
            toMainNavigation()
        } catch (e: NullPointerException) {
            toSignInNavigation()
        }
    }

    fun checkSharedPref() {
        if (getSplashSharedPrefUseCase.execute()) {
            checkToken()
        } else {
            saveSharedPref()
            toOnboardingNavigation()
        }
    }

    private fun saveSharedPref() = saveSplashSharedPrefUseCase.execute()
}