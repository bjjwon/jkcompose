package com.jobkorea.app.presentation.screens.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.jobkorea.app.data.ScreenParams
import com.jobkorea.app.presentation.web.JkWebview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopupScreen(screenParams: ScreenParams?) {

    screenParams?.let { data ->
        Column {
            TopAppBar(title = { Text(text = data.uiType) })
            JkWebview(url = data.url, onNavigate = {

            })
        }
    }
}


