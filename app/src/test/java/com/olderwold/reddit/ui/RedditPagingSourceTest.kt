package com.olderwold.reddit.ui

import androidx.paging.PagingSource
import com.olderwold.reddit.data.NetworkGetFeedPage
import com.olderwold.reddit.data.dto.RedditHot
import com.olderwold.reddit.domain.FeedItem
import com.olderwold.reddit.tape
import kotlinx.coroutines.runBlocking
import okreplay.OkReplay
import org.amshove.kluent.shouldNotBeEmpty
import org.junit.Rule
import org.junit.Test

class RedditPagingSourceTest {
    @get:Rule
    internal val testRule = tape()

    @Test
    @OkReplay(tape = "smoke_test")
    fun `load should succeed with loading the data from the tape`(): Unit = runBlocking {
        val redditClient = NetworkGetFeedPage(testRule.api, RedditHot.Mapper())
        val pagingSource = RedditPagingSource(redditClient)

        val loadParams = PagingSource.LoadParams.Refresh<String>(
            key = null,
            loadSize = 2,
            placeholdersEnabled = false
        )
        val result =
            pagingSource.load(loadParams) as? PagingSource.LoadResult.Page<String, FeedItem>

        result?.data!!.shouldNotBeEmpty()
    }
}
