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
class PopupViewModel  @Inject constructor(

) : ViewModel() {

    private val _event = MutableSharedFlow<PopupEvent>()
    val event = _event.asSharedFlow()

    fun goActivity(screenParams: ActivityParams) = viewModelScope.launch {
        _event.emit(PopupEvent.GoActivity(screenParams))
    }


    sealed class PopupEvent {

        data class GoActivity(val params : ActivityParams) : PopupEvent()

    }
}