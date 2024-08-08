package com.jobkorea.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jobkorea.app.data.ActivityParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.NONE)
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<MainEvent>()
    val event = _event.asSharedFlow()

    fun goActivity(screenParams: ActivityParams) = viewModelScope.launch {
        _event.emit(MainEvent.GoActivity(screenParams))
    }


}

sealed class MainUiState {

    data object NONE : MainUiState()

}

sealed class MainEvent {

    data class GoActivity(val params : ActivityParams) : MainEvent()

}