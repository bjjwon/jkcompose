package com.jobkorea.app.presentation.web

import android.content.Context
import android.webkit.JavascriptInterface
import com.jobkorea.app.data.ScreenParams

class AndroidWebBridge(
    context : Context,
    private val onNavigate : (ScreenParams) -> Unit = {}
) {

    /**
     * 브릿지 이름
     */
    companion object {
        const val BRIDGE_NAME = "android"
    }

    /**
     * Toapp 명령어 처리 함수
     * @param command 명령
     */
    @JavascriptInterface
    fun jktoappHandler(command: String) {
        ToappManager.toApp(command, onNavigate)
    }

}