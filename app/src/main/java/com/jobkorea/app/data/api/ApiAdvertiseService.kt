package com.jobkorea.app.data.api

import com.jobkorea.app.domain.models.ResponseSplashAdvertise
import com.jobkorea.app.util.Constants
import retrofit2.http.GET

interface ApiAdvertiseService {
    /**
     * https://wiki.jobkorea.co.kr/pages/viewpage.action?pageId=512590613
     * 광고 api
     */

    @GET(Constants.JK_SPLASH_AOS_AD)
    suspend fun requestSplashAdvertise(): ResponseSplashAdvertise
}