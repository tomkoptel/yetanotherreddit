package com.olderwold.reddit.feed

import android.app.Activity
import android.content.Intent
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Test

class WebViewPageTest {
    private val activity = mockk<Activity>(relaxed = true)
    private val mockIntent = mockk<Intent>()
    private val intentFactory = spyk<(String) -> Intent>({ mockIntent })
    private val webViewPage = WebViewPage(activity).apply {
        actionIntent = intentFactory
    }

    @Test
    fun open() {
        webViewPage.open("https://cutepuppies")

        verify { intentFactory.invoke("https://cutepuppies") }
        verify { activity.startActivity(mockIntent) }
    }
}
