package com.olderwold.reddit.feature.feed.data

import com.olderwold.reddit.tape
import kotlinx.coroutines.runBlocking
import okreplay.OkReplay
import org.amshove.kluent.shouldNotBeEmpty
import org.junit.Rule
import org.junit.Test

class RedditApiTest {
    @get:Rule
    internal val testRule = tape()

    @Test
    @OkReplay
    fun smokeTest(): Unit = runBlocking {
        val hotListing = testRule.api.hotListing(limit = 1)
        hotListing.data?.children!!.shouldNotBeEmpty()
    }
}
