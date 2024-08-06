package com.jobkorea.app.presentation.web

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.jobkorea.app.data.ScreenParams

@SuppressLint("JavascriptInterface")
@Composable
fun JkWebview(
    url : String,
    callbackWhenScrollingTop: (Int) -> Unit = {},
    onNavigate : (ScreenParams) -> Unit
){

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                webViewClient = JkWebviewClient()
                addJavascriptInterface(AndroidWebBridge(context, onNavigate = onNavigate), AndroidWebBridge.BRIDGE_NAME)
                settings.javaScriptEnabled = true
                settings.userAgentString = "Mozilla/5.0 (Linux; Android 14; SM-F731N Build/UP1A.231005.007; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/126.0.6478.134 Mobile Safari/537.36;Mobile JK_App(appversion=4.0.17&apptsxml=5&key_no=18735653)/GA_Android AFID/1721871225881-7244531544046902200"
                loadUrl(url)
                setOnScrollChangeListener { _, _, scrollY, _, _ ->
                    callbackWhenScrollingTop(scrollY)
                }
            }
        })
    }


}