package com.jobkorea.app.presentation.web

import android.net.Uri
import com.jobkorea.app.data.ActivityParams
import com.jobkorea.app.utils.Constants
import java.util.Locale

open class ToAppHelper {

    protected open var originalUrl: String = ""

    protected val originalUri: Uri
        get() = Uri.parse(originalUrl)

    protected val lowerUrl: String
        get() = originalUrl.lowercase()

    protected val lowerUri: Uri
        get() = Uri.parse(lowerUrl)

    protected val goUrl: String
        get() {
            var url = Uri.parse(originalUrl)?.let { uri ->
                uri.getQueryParameter("url") ?: kotlin.run {
                    uri.getQueryParameter("Url") ?: ""
                }
            } ?: ""
            if (url.isNotEmpty()) {
                url = addHTTP(url)
            }
            return url
        }


    /**
     * HTTP, HTTPS를 인자 URL에 추가한다.
     * @param url 주소
     * @return 완성된 주소를 넘긴다.
     */
    fun addHTTP(url: String): String {
        var url = url
        val lowerurl = url.lowercase(Locale.ROOT)

        if (!lowerurl.startsWith("http:/") && !lowerurl.startsWith("https:/")) {
            if (url.startsWith("/") && url.length > 1)
                url = url.substring(1, url.length)
            url = Constants.DOMAIN + url
        }
        return url
    }


    protected fun goSub(onNavigate: (ActivityParams) -> Unit) {

        var uri = lowerUri

        var uiType = "1"
        var urlType = uri.getQueryParameter("urltype") ?: ""
        var title = uri.getQueryParameter("title") ?: ""

        var searchKeyword = ""
        var searchUrl = ""


        uri.getQueryParameter("uitype")?.let {

            uiType = it

            if (it == "0") {
                searchKeyword = uri.getQueryParameter("keyword") ?: ""
                searchUrl = goUrl
            }

        }




        onNavigate(
            ActivityParams(uiType, urlType, goUrl, title, searchKeyword, searchUrl)
        )

    }

}