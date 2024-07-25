package com.jobkorea.app.ui.screens.main

import DynamicTopBar
import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.jobkorea.app.utils.dpToPx

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

}

const val maxHeightTopbar = 100
const val minHeightTopBar = 50
const val defaultPadding = 10

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val isTopbarCollapsed = remember { mutableIntStateOf(0) }

    Scaffold {
        Column {
            DynamicTopBar(isTopbarCollapsed.value)
            JKMainWebView { isScrollTop ->
                isTopbarCollapsed.value = if (isScrollTop) 0 else 1
            }
        }
    }
}


@SuppressLint("SetJavaScriptEnabled")
@Composable
private fun JKMainWebView(isScrollTop: (Boolean) -> Unit) {
    var previousScrollY by remember { mutableIntStateOf(0) }
    var topbarHeight = dpToPx(dp = minHeightTopBar.dp)

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.userAgentString =
                    "Mozilla/5.0 (Linux; Android 14; SM-F731N Build/UP1A.231005.007; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/126.0.6478.134 Mobile Safari/537.36;Mobile JK_App(appversion=4.0.17&apptsxml=5&key_no=18735653)/GA_Android AFID/1721871225881-7244531544046902200"
                loadUrl("https://m.jobkorea.co.kr")
                setOnScrollChangeListener { _, _, scrollY, _, _ ->
                    val offset = kotlin.math.abs(scrollY - previousScrollY)
                    if (scrollY >= previousScrollY) {
                        if (scrollY > topbarHeight)
                            isScrollTop(false)
                    }
                    else if (scrollY < previousScrollY) {

                        if (scrollY == 0)
                            isScrollTop(true)
                        else if (offset < topbarHeight)
                            return@setOnScrollChangeListener
                        else
                            isScrollTop(false)

                    }
                    previousScrollY = scrollY
                }
            }
        })
    }
}


