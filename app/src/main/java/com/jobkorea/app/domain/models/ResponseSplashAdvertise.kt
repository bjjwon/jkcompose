package com.jobkorea.app.domain.models

import com.google.gson.annotations.SerializedName


data class ResponseSplashAdvertise(
    @SerializedName("campaignId")
    val campaignId: Int? = null,
    @SerializedName("campaignProductId")
    val campaignProductId: Int? = null,
    @SerializedName("contents")
    val contents: Contents? = null,
    @SerializedName("logApiUrl")
    val logApiUrl: String? = null
) {
    data class Contents(
        @SerializedName("backgroundColor")
        val backgroundColor: String? = null,
        @SerializedName("bannerImage")
        val bannerImage: String? = null,
        @SerializedName("logoImage")
        val logoImage: String? = null
    )
}