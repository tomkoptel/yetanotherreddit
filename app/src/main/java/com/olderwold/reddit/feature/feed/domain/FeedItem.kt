package com.olderwold.reddit.feature.feed.domain

data class FeedItem(
    val id: String,
    val author: String?,
    val title: String?,
    val url: String?,
)
