package com.example.moretech40android.presentation.fragments.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _navEvent = MutableLiveData<NavDirections>()
    val navEvent: LiveData<NavDirections> = _navEvent

    fun toSignInNavigation() {
        _navEvent.postValue(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
    }
}

