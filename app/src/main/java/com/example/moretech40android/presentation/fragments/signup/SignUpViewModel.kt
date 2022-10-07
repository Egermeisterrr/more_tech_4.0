package com.example.moretech40android.presentation.fragments.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.domain.models.ProfileModel
import com.example.domain.usecase.refreshtoken.EncryptRefreshTokenUseCase
import com.example.domain.usecase.userinfo.RegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val encryptRefreshTokenUseCase: EncryptRefreshTokenUseCase
) : ViewModel() {

    private val _navEvent = MutableLiveData<NavDirections>()
    val navEvent: LiveData<NavDirections> = _navEvent

    private var _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMessage

    private var _registrationSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    val registrationSuccessful: LiveData<Boolean> = _registrationSuccessful

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _errorMessage.postValue("Something went wrong")
    }

    fun toSignInNavigation() {
        _navEvent.postValue(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
    }

    fun toMainFragmentNavigation() {
        _navEvent.postValue(SignUpFragmentDirections.actionSignUpFragmentToMainFragment())
    }

    fun registration(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            encryptRefreshTokenUseCase.execute(
                async {
                    registrationUseCase.execute(ProfileModel(username, password)).refresh_token
                }.await()
            )
            _registrationSuccessful.postValue(true)
        }
    }
}

