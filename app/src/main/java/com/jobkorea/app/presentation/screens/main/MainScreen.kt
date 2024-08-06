package com.jobkorea.app.presentation.screens.main

import MainDynamicTopBar
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.jobkorea.app.presentation.web.JkWebview
import com.jobkorea.app.utils.Constants
import com.jobkorea.app.utils.dpToPx
import com.jobkorea.app.viewmodel.MainUiState
import com.jobkorea.app.viewmodel.MainViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navHostController: NavHostController) {
    val isTopBarCollapsed = remember { mutableIntStateOf(0) }

    val viewModel = hiltViewModel<MainViewModel>()
    val uiState by viewModel.uiState.collectAsState()



    LaunchedEffect(uiState) {

        uiState?.let {
            when (it) {
                is MainUiState.ShowPopup -> {
                    // navigate url 인코딩 필수, 평문 시, 에러발생
                    navHostController.navigate(it.params)
                    viewModel.setNone()
                }
                MainUiState.None -> {

                }
            }
        }


    }

    Scaffold {
        Column {
            MainDynamicTopBar(isTopBarCollapsed.intValue)
            MainWebView(
                isScrollTop = { isScrollTop ->
                    isTopBarCollapsed.intValue = if (isScrollTop) 0 else 1
                })
        }
    }


}



@SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
@Composable
fun MainWebView(isScrollTop: (Boolean) -> Unit) {
    var previousScrollY by remember { mutableIntStateOf(0) }
    var topBarHeight = dpToPx(dp = MainUiConstant.MIN_HEIGHT_TOBAR.dp)
    val viewModel = hiltViewModel<MainViewModel>()

    JkWebview(url = Constants.DOMAIN,
        callbackWhenScrollingTop = { scrollY ->
            val offset = kotlin.math.abs(scrollY - previousScrollY)
            if (scrollY >= previousScrollY) {
                if (scrollY > topBarHeight)
                    isScrollTop(false)
            } else if (scrollY < previousScrollY) {

                if (scrollY == 0)
                    isScrollTop(true)
                else if (offset < topBarHeight)
                    return@JkWebview
                else
                    isScrollTop(false)

            }
            previousScrollY = scrollY
        }, onNavigate = {
            viewModel.showPopup(it)
        })
}

