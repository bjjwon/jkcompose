package com.jobkorea.app.presentation.web
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class JkWebviewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

        request?.url?.let { uri ->
            when (uri.host) {
                "ui" -> {
                    return true
                } else -> {
                    return super.shouldOverrideUrlLoading(view, request)
                }
            }
        }
        return super.shouldOverrideUrlLoading(view, request)
    }
}