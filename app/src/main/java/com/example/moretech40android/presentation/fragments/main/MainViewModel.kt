package com.example.moretech40android.presentation.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.RefreshToken
import com.example.domain.usecase.accesstoken.SaveAccessTokenUseCase
import com.example.domain.usecase.refreshtoken.DecryptRefreshTokenUseCase
import com.example.domain.usecase.userinfo.UpdateAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val updateAccessTokenUseCase: UpdateAccessTokenUseCase,
    private val decryptRefreshTokenUseCase: DecryptRefreshTokenUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase
) : ViewModel() {

    fun updateToken() {
        viewModelScope.launch {
            saveAccessTokenUseCase.execute(
                async {
                    updateAccessTokenUseCase.execute(
                        RefreshToken(
                            async {
                                decryptRefreshTokenUseCase.execute()
                            }.await()
                        )
                    )
                }.await()
            )
        }
    }
}