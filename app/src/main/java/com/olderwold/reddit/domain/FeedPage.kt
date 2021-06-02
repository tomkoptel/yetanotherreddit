package com.olderwold.reddit.domain

data class FeedPage(
    val nextKey: String?,
    val prevKey: String?,
    val data: List<FeedItem>
)
