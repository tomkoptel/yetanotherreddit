package com.olderwold.reddit.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RedditHot(
    @Json(name = "data")
    val `data`: Data? = null,
    @Json(name = "kind")
    val kind: String? = null
)
