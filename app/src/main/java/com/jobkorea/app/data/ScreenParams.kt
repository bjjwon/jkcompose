package com.jobkorea.app.data

import kotlinx.serialization.Serializable

@Serializable
data class ScreenParams(

    val uiType: String = "1",
    val urlType: String = "",
    val url: String = "",
    val title : String = "",

    val searchKeyword : String = "",
    val searchUrl : String = "",


)