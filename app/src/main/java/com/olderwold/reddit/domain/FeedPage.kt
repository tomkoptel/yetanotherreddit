package com.olderwold.reddit.domain

data class FeedPage(
    val nextKey: String?,
    val prevKey: String?,
    val data: List<FeedItem>
)

data class FeedItem(
    val id: String,
    val author: String?,
    val title: String?
)
