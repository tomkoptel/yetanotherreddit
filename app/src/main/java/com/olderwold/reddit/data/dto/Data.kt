package com.olderwold.reddit.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "after")
    val after: String? = null,
    @Json(name = "before")
    val before: String? = null,
    @Json(name = "children")
    val children: List<Children>? = null,
    @Json(name = "dist")
    val dist: Int? = null,
    @Json(name = "modhash")
    val modhash: String? = null
)
