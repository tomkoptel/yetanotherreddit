package com.olderwold.reddit.feature.feed.data.dto

import com.olderwold.reddit.feature.feed.domain.FeedItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dagger.Reusable
import javax.inject.Inject

@JsonClass(generateAdapter = true)
data class Children(
    @Json(name = "data")
    val data: DataX? = null,
    @Json(name = "kind")
    val kind: String? = null
) {
    @Reusable
    class Mapper @Inject constructor() {
        fun toDomain(dto: Children): FeedItem? {
            val data = dto.data
            val id = data?.id

            return if (id == null) {
                null
            } else {
                FeedItem(
                    id = id,
                    title = data.title,
                    author = data.author,
                    url = data.url
                )
            }
        }
    }
}
