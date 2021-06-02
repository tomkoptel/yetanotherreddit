package com.olderwold.reddit.feature.web

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert.fail
import org.junit.Test

class WebViewPageTest {
    private val activity = mockk<Activity>(relaxed = true)
    private val mockIntent = mockk<Intent>()
    private val intentFactory = spyk<(String) -> Intent>({ mockIntent })
    private val webViewPage = WebViewPage(activity).apply {
        actionIntent = intentFactory
    }

    @Test
    fun `there is an app that can handle intent`() {
        webViewPage.open("https://cutepuppies")

        verify { intentFactory.invoke("https://cutepuppies") }
        verify { activity.startActivity(mockIntent) }
    }

    @Test
    fun `there is no app that can handle intent`() {
        every { activity.startActivity(any()) } throws ActivityNotFoundException()

        try {
            webViewPage.open("https://cutepuppies")
        } catch (ex: Exception) {
            fail("Should not throw $ex")
        }
    }
}
