package com.jobkorea.app.domain.models

import com.google.gson.annotations.SerializedName


/**
 * 앱 식별 고유 값 (TsNo)
 */
data class ResponseAppId (
    @SerializedName("rc") var rc: String = "",
    @SerializedName("msg") var msg: String = "",
    @SerializedName("items") var items: List<TsNo>
)

data class TsNo(
    @SerializedName("ts_no") var tsNo: String = ""
)
