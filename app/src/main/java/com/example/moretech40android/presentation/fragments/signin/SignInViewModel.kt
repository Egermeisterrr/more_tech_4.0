package com.example.moretech40android.presentation.fragments.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(): ViewModel() {

    private val _navEvent = MutableLiveData<NavDirections>()
    val navEvent: LiveData<NavDirections> = _navEvent

    fun toSignUpNavigation() {
        _navEvent.postValue(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }

    fun toMainFragmentNavigation() {
        _navEvent.postValue(SignInFragmentDirections.actionSignInFragmentToMainFragment())
    }
}