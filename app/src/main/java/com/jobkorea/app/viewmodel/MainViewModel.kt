package com.jobkorea.app.viewmodel

import androidx.lifecycle.ViewModel
import com.jobkorea.app.data.ScreenParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState?>(null)
    val uiState = _uiState.asStateFlow()

    fun showPopup(screenParams: ScreenParams) {
        _uiState.value = MainUiState.ShowPopup(screenParams)
    }

    fun setNone() {
        _uiState.value = MainUiState.None
    }

}

sealed class MainUiState {

    data class ShowPopup(val params : ScreenParams) : MainUiState()
    object None : MainUiState()


}