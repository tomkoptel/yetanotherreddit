package com.olderwold.reddit.domain

interface GetFeedPage {
    suspend operator fun invoke(
        limit: Int,
        after: String?
    ): FeedPage
}
