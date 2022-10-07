package com.example.moretech40android.presentation.fragments.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.domain.usecase.GetSplashSharedPrefUseCase
import com.example.domain.usecase.SaveSplashSharedPrefUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val getSplashSharedPrefUseCase: GetSplashSharedPrefUseCase,
    private val saveSplashSharedPrefUseCase: SaveSplashSharedPrefUseCase
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

    fun checkSharedPref() {
        if (getSplashSharedPrefUseCase.execute()) {
            toSignInNavigation()
        } else {
            saveSplashSharedPrefUseCase.execute()
            toOnboardingNavigation()
        }
    }
}