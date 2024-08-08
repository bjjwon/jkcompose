package com.jobkorea.app.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jobkorea.app.domain.models.ResponseSplashAdvertise
import com.jobkorea.app.data.repository.SplashRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: SplashRepositoryImpl,
) : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState?>(null)
    val uiState: StateFlow<SplashUiState?> = _uiState


    /***
     * 딥링크 여부 확인
     */
    fun checkDeepLink (intentUri : Uri) {

    }

    fun fetchSplashImage() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.requestSplashAdvertise()
         //   _uiState.value = SplashUiState.ShowAdvertise(response)
            _uiState.value = SplashUiState.ShowDefaultSplash
        }
    }

}

sealed class SplashUiState {
    data class ShowAdvertise(val responseSplashAdvertise: ResponseSplashAdvertise) : SplashUiState()
    data object ShowDefaultSplash : SplashUiState()

}