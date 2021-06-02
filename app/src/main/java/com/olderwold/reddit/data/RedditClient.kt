package com.olderwold.reddit.data

import com.olderwold.reddit.data.dto.RedditHot
import com.olderwold.reddit.domain.FeedPage
import dagger.Reusable
import javax.inject.Inject

@Reusable
internal class RedditClient @Inject constructor(
    private val api: RedditApi,
    private val mapper: RedditHot.Mapper,
) {
    suspend fun hotListing(
        limit: Int,
        after: String?
    ): FeedPage {
        val hotListing = api.hotListing(limit = limit, after = after)
        return mapper.toDomain(hotListing)
    }
}
