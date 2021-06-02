package com.olderwold.reddit.feature.feed.domain

data class FeedPage(
    val nextKey: String?,
    val prevKey: String?,
    val data: List<FeedItem>
)
