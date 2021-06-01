package com.olderwold.reddit.data

import androidx.paging.*
import com.olderwold.reddit.domain.FeedItem
import com.olderwold.reddit.ui.RedditPagingSource
import kotlinx.coroutines.runBlocking
import okreplay.*
import org.amshove.kluent.shouldNotBeEmpty
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class RedditPagingSource {
    private val okReplayInterceptor = OkReplayInterceptor()
    private val configuration = OkReplayConfig.Builder()
        .defaultMode(TapeMode.READ_ONLY)
        .sslEnabled(true)
        .interceptor(okReplayInterceptor)
        .build()
    private val api = RedditClient { addInterceptor(okReplayInterceptor) }

    @get:Rule
    val testRule: TestRule = RecorderRule(configuration)

    @Test
    @OkReplay(tape = "smoke_test")
    fun load() = runBlocking {
        val pagingSource = RedditPagingSource(api)
        val loadParams = PagingSource.LoadParams.Refresh<String>(
            key = null,
            loadSize = 2,
            placeholdersEnabled = false
        )

        val result = pagingSource.load(loadParams) as? PagingSource.LoadResult.Page<String, FeedItem>
        result?.data!!.shouldNotBeEmpty()
    }
}
