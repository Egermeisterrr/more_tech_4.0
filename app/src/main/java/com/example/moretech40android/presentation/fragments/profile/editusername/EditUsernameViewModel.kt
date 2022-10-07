package com.example.moretech40android.presentation.fragments.profile.editusername

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditUsernameViewModel @Inject constructor(): ViewModel() {

    private val _navEvent = MutableLiveData<NavDirections>()
    val navEvent: LiveData<NavDirections> = _navEvent

    fun toProfileNavigation() {
        _navEvent.postValue(
            EditUsernameFragmentDirections.actionEditUsernameFragmentToProfileFragment()
        )
    }
}