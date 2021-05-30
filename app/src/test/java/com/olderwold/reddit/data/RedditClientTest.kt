package com.olderwold.reddit.data

import kotlinx.coroutines.runBlocking
import okreplay.*
import org.amshove.kluent.shouldNotBeEmpty
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class RedditClientTest {
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
    @OkReplay
    fun smokeTest(): Unit = runBlocking {
        val hotListing = api.hotListing(limit = 1)
        hotListing.shouldNotBeEmpty()
    }
}
