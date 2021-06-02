package com.olderwold.reddit.feature.feed.data.dto

import com.olderwold.reddit.feature.feed.domain.FeedItem
import com.olderwold.reddit.feature.feed.domain.FeedPage
import io.mockk.Called
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.junit.Test

class RedditHotMapperTest {
    private val dtoData = mockk<Data>(relaxed = true)
    private val dto = mockk<RedditHot>(relaxed = true) {
        every { data } returns dtoData
    }
    private val feedItem = mockk<FeedItem>()
    private val feedMapper = mockk<Children.Mapper>() {
        every { toDomain(any()) } returns feedItem
    }
    private val mapper = RedditHot.Mapper(feedMapper)

    @Test
    fun `should map field nextKey`() {
        every { dtoData.after } returns "nextKey"

        map().nextKey shouldBeEqualTo "nextKey"
    }

    @Test
    fun `should map field prevKey`() {
        every { dtoData.before } returns "prevKey"

        map().prevKey shouldBeEqualTo "prevKey"
    }

    @Test
    fun `should map children to feed items`() {
        every { dtoData.children } returns listOf(mockk())

        map().data shouldContain feedItem
    }

    @Test
    fun `should not map children to feed items, if children contains null`() {
        every { dtoData.children } returns listOf(null)

        map().data.shouldBeEmpty()
        verify { feedMapper wasNot Called }
    }

    @Test
    fun `should not map children to feed items, if children array is empty`() {
        every { dtoData.children } returns emptyList()

        map().data.shouldBeEmpty()
        verify { feedMapper wasNot Called }
    }

    private fun map(): FeedPage = mapper.toDomain(dto)
}
