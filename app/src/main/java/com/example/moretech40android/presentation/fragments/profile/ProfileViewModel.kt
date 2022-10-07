package com.example.moretech40android.presentation.fragments.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.domain.models.RefreshToken
import com.example.domain.usecase.accesstoken.DeleteAccessTokenUseCase
import com.example.domain.usecase.accesstoken.GetAccessTokenUseCase
import com.example.domain.usecase.refreshtoken.DecryptRefreshTokenUseCase
import com.example.domain.usecase.refreshtoken.DeleteRefreshTokenUseCase
import com.example.domain.usecase.userinfo.GetProfileUseCase
import com.example.domain.usecase.userinfo.UpdateAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val deleteRefreshTokenUseCase: DeleteRefreshTokenUseCase,
    private val updateAccessTokenUseCase: UpdateAccessTokenUseCase,
    private val decryptRefreshTokenUseCase: DecryptRefreshTokenUseCase,
    private val deleteAccessTokenUseCase: DeleteAccessTokenUseCase
) : ViewModel() {

    private val _navEvent = MutableLiveData<NavDirections>()
    val navEvent: LiveData<NavDirections> = _navEvent

    private var _username: MutableLiveData<String> = MutableLiveData()
    val username: LiveData<String> = _username

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        viewModelScope.launch(Dispatchers.IO) {
            updateAccessTokenUseCase.execute(RefreshToken(decryptRefreshTokenUseCase.execute()))
        }
        getProfile()
    }

    fun getProfile() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            var name = ""
            async {
                name = getProfileUseCase.execute(getAccessTokenUseCase.execute()).username
            }.await()
            _username.postValue(name)
        }
    }

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

    private fun toSignInNavigation() {
        _navEvent.postValue(
            ProfileFragmentDirections.actionProfileFragmentToSignInFragment()
        )
    }

    fun logout() {
        deleteAccessTokenUseCase.execute()
        deleteRefreshTokenUseCase.execute()
        toSignInNavigation()
    }
}