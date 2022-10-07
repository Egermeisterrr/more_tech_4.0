package com.example.moretech40android.presentation.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel  @Inject constructor() : ViewModel() {

    private val _actionBarEvent: MutableLiveData<Boolean> = MutableLiveData()
    val actionBarEvent: LiveData<Boolean>
        get() = _actionBarEvent

    fun showActionBar() {
        viewModelScope.launch {
            _actionBarEvent.postValue(true)
        }
    }

    fun hideActionBar() {
        viewModelScope.launch {
            _actionBarEvent.postValue(false)
        }
    }
}