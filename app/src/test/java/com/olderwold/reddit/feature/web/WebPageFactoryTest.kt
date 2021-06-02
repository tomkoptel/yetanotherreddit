package com.olderwold.reddit.feature.web

import android.app.Activity
import android.app.Application
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class WebPageFactoryTest {
    private val mockPackageManager = mockk<PackageManager>()
    private val application = mockk<Application>() {
        every { packageManager } returns mockPackageManager
    }
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
        val resolvedInfo = if (supported) {
            mutableListOf(mockk<ResolveInfo>())
        } else {
            mutableListOf()
        }
        every { mockPackageManager.queryIntentServices(any(), any()) } returns resolvedInfo
    }
}
