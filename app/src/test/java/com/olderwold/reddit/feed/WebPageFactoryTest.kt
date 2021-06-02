package com.olderwold.reddit.feed

import android.app.Activity
import android.app.Application
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class WebPageFactoryTest {
    private val application = mockk<Application>(relaxed = true)
    private val activity = mockk<Activity>()
    private val webPageFactory = WebPage.Factory(application)

    @Test
    fun `when chrome tabs supported`() {
        givenChromeTabs(supported = true)

        webPageFactory.create(activity) shouldBeInstanceOf ChromeTabWebPage::class
    }

    @Test
    fun `when no chrome tabs supported`() {
        givenChromeTabs(supported = false)

        webPageFactory.create(activity) shouldBeInstanceOf WebViewPage::class
    }

    private fun givenChromeTabs(supported: Boolean) {
        every { application.bindService(any(), any(), any()) } returns supported
    }
}
