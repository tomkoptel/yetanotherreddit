package com.olderwold.reddit.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResizedStaticIcon(
    @Json(name = "height")
    val height: Int? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "width")
    val width: Int? = null
)
