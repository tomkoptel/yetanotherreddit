package com.olderwold.reddit.data.dto

import com.olderwold.reddit.domain.FeedItem
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.junit.Test

class ChildrenMapperTest {
    private val dataX = mockk<DataX>(relaxed = true) {
        every { id } returns "??"
    }
    private val dto = Children(data = dataX)
    private val mapper = Children.Mapper()

    @Test
    fun `should not map if id is missing`() {
        every { dataX.id } returns null

        mapper.toDomain(dto).shouldBeNull()
    }

    @Test
    fun `should map if id is available`() {
        every { dataX.id } returns "you shall pass"

        requireMap().id shouldBeEqualTo "you shall pass"
    }

    @Test
    fun `should map title`() {
        every { dataX.title } returns "Gandalf escapes Moria"

        requireMap().title shouldBeEqualTo "Gandalf escapes Moria"
    }

    @Test
    fun `should map author`() {
        every { dataX.author } returns "J. R. R. Tolkien"

        requireMap().author shouldBeEqualTo "J. R. R. Tolkien"
    }

    @Test
    fun `should map url`() {
        every { dataX.url } returns "https://en.wikipedia.org/wiki/The_Lord_of_the_Rings"

        requireMap().url shouldBeEqualTo "https://en.wikipedia.org/wiki/The_Lord_of_the_Rings"
    }

    private fun requireMap(): FeedItem = mapper.toDomain(dto)!!
}
