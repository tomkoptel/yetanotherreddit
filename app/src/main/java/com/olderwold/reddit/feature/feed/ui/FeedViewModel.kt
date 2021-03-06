package com.olderwold.reddit.feature.feed.ui

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.olderwold.reddit.feature.feed.domain.FeedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class FeedViewModel @Inject constructor(
    private val pagingSource: FeedPagingSource,
) : ViewModel() {
    val pager: Pager<String, FeedItem>
        get() = Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = true,
                maxSize = 200
            ),
            pagingSourceFactory = { pagingSource }
        )
}
