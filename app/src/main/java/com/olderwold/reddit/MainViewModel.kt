package com.olderwold.reddit

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.olderwold.reddit.domain.FeedItem
import com.olderwold.reddit.ui.RedditPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val pagingSource: RedditPagingSource,
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