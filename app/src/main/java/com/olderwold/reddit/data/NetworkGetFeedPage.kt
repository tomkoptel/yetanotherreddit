package com.olderwold.reddit.data

import com.olderwold.reddit.data.dto.RedditHot
import com.olderwold.reddit.domain.FeedPage
import com.olderwold.reddit.domain.GetFeedPage
import dagger.Binds
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

internal class NetworkGetFeedPage @Inject constructor(
    private val api: RedditApi,
    private val mapper: RedditHot.Mapper,
) : GetFeedPage {
    override suspend operator fun invoke(
        limit: Int,
        after: String?
    ): FeedPage {
        val hotListing = api.hotListing(limit = limit, after = after)
        return mapper.toDomain(hotListing)
    }

    @InstallIn(SingletonComponent::class)
    @dagger.Module
    interface Module {
        @get:Binds
        @get:Reusable
        val NetworkGetFeedPage.instance: GetFeedPage
    }
}
