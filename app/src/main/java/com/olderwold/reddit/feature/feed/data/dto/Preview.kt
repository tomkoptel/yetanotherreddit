package com.olderwold.reddit.feature.feed.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Preview(
    @Json(name = "enabled")
    val enabled: Boolean? = null,
    @Json(name = "images")
    val images: List<Image>? = null
)
