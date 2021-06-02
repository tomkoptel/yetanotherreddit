package com.olderwold.reddit.feature.feed.domain

interface GetFeedPage {
    suspend operator fun invoke(
        limit: Int,
        after: String?
    ): FeedPage
}
