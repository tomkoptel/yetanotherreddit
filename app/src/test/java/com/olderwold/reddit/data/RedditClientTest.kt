package com.olderwold.reddit.data

import com.olderwold.reddit.tape
import kotlinx.coroutines.runBlocking
import okreplay.*
import org.amshove.kluent.shouldNotBeEmpty
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class RedditClientTest {
    @get:Rule
    val testRule = tape()

    @Test
    @OkReplay
    fun smokeTest(): Unit = runBlocking {
        val hotListing = testRule.api.hotListing(limit = 1)
        hotListing.data.shouldNotBeEmpty()
    }
}
