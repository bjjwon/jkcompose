package com.jobkorea.app.data

import java.io.Serializable


data class ActivityParams(

    val uiType: String = "1",
    val urlType: String = "",
    val url: String = "",
    val title: String = "",

    val searchKeyword: String = "",
    val searchUrl: String = "",

) : Serializable