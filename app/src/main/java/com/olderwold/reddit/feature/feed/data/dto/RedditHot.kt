package com.olderwold.reddit.feature.feed.data.dto


import androidx.annotation.RestrictTo
import com.olderwold.reddit.feature.feed.domain.FeedPage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dagger.Reusable
import javax.inject.Inject

@JsonClass(generateAdapter = true)
data class RedditHot(
    @Json(name = "data")
    val `data`: Data? = null,
    @Json(name = "kind")
    val kind: String? = null
) {
    @Reusable
    class Mapper @Inject constructor(
        private val feedMapper: Children.Mapper,
    ) {
        @RestrictTo(RestrictTo.Scope.TESTS)
        companion object {
            operator fun invoke(): Mapper {
                return Mapper(Children.Mapper())
            }
        }

        fun toDomain(dto: RedditHot): FeedPage {
            val data = dto.data?.children
                ?.filterNotNull()
                .orEmpty()
                .mapNotNull(feedMapper::toDomain)

            return FeedPage(
                data = data,
                nextKey = dto.data?.after,
                prevKey = dto.data?.before
            )
        }
    }
}
