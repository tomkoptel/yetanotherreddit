package com.olderwold.reddit.feature.feed.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "resolutions")
    val resolutions: List<Resolution>? = null,
    @Json(name = "source")
    val source: Source? = null,
    @Json(name = "variants")
    val variants: Variants? = null
)
