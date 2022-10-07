package com.example.moretech40android.presentation.fragments.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.domain.models.ProfileModel
import com.example.domain.usecase.accesstoken.SaveAccessTokenUseCase
import com.example.domain.usecase.refreshtoken.EncryptRefreshTokenUseCase
import com.example.domain.usecase.userinfo.UserLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val encryptRefreshTokenUseCase: EncryptRefreshTokenUseCase
) : ViewModel() {

    private val _navEvent = MutableLiveData<NavDirections>()
    val navEvent: LiveData<NavDirections> = _navEvent

    private var _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = _errorMessage

    private var _loginSuccessful: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccessful: LiveData<Boolean> = _loginSuccessful

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _errorMessage.postValue("Something went wrong")
    }

    fun toSignUpNavigation() {
        _navEvent.postValue(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
    }

    fun toMainFragmentNavigation() {
        _navEvent.postValue(SignInFragmentDirections.actionSignInFragmentToMainFragment())
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            saveAccessTokenUseCase.execute(
                async {
                    userLoginUseCase.execute(ProfileModel(username, password)).access_token
                }.await()
            )
            encryptRefreshTokenUseCase.execute(
                async {
                    userLoginUseCase.execute(ProfileModel(username, password)).refresh_token
                }.await()
            )
            _loginSuccessful.postValue(true)
        }
    }
}