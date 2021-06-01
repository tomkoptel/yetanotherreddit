package com.olderwold.reddit.ui

import androidx.paging.*
import com.olderwold.reddit.domain.FeedItem
import com.olderwold.reddit.tape
import kotlinx.coroutines.runBlocking
import okreplay.*
import org.amshove.kluent.shouldNotBeEmpty
import org.junit.Rule
import org.junit.Test

class RedditPagingSourceTest {
    @get:Rule
    val testRule = tape()

    @Test
    @OkReplay(tape = "smoke_test")
    fun `load should succeed with loading the data from the tape`(): Unit = runBlocking {
        val pagingSource = RedditPagingSource(testRule.api)
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
