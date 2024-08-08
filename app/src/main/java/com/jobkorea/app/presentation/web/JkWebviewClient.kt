package com.jobkorea.app.presentation.web
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jobkorea.app.data.ActivityParams

class JkWebviewClient(
    private val onNavigate : (ActivityParams) -> Unit = {}
) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {


        request?.url?.let { uri ->

            val lowerUrl = uri.toString().lowercase()
            val lowerUri : Uri = Uri.parse(lowerUrl)
            var goUrl = uri.getQueryParameter("url") ?: kotlin.run {
                uri.getQueryParameter("Url") ?: ""
            }
            if (goUrl.isNotEmpty()) {
                goUrl = ToappManager.addHTTP(goUrl)
            }

            when (lowerUri.scheme) {
                "toapp" -> {
                    return ToappManager.toApp(uri.toString(), onNavigate = onNavigate)
                }
                else -> {
                    return false
                }
            }
        }
        return super.shouldOverrideUrlLoading(view, request)
    }
}