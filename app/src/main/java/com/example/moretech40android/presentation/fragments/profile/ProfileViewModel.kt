package com.example.moretech40android.presentation.fragments.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _navEvent = MutableLiveData<NavDirections>()
    val navEvent: LiveData<NavDirections> = _navEvent

    fun toEditUsernameNavigation() {
        _navEvent.postValue(
            ProfileFragmentDirections.actionProfileFragmentToEditUsernameFragment()
        )
    }

    fun toEditEmailNavigation() {
        _navEvent.postValue(
            ProfileFragmentDirections.actionProfileFragmentToEditEmailFragment()
        )
    }
}