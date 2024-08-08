package com.jobkorea.app.presentation.screens.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jobkorea.app.data.ActivityParams
import com.jobkorea.app.presentation.web.JkWebview
import com.jobkorea.app.presentation.viewmodel.PopupViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupScreen(screenParams: ActivityParams?) {

    val viewModel = hiltViewModel<PopupViewModel>()

    screenParams?.let { data ->
        Column {
            TopAppBar(title = { Text(text = data.uiType) })
            JkWebview(url = data.url, onNavigate = {
                viewModel.goActivity(it)
            })
        }
    }
}


