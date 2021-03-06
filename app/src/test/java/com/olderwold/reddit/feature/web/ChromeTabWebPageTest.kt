package com.olderwold.reddit.feature.web

import android.app.Activity
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.Test

class ChromeTabWebPageTest {
    private val activity = mockk<Activity>()
    private val mockCustomTabsIntent = mockk<CustomTabsIntent>(relaxed = true)
    private val mockUri = mockk<Uri>()
    private val uriParser = spyk<(String) -> Uri>({ mockUri })
    private val chromeTab = ChromeTabWebPage(activity)
        .apply {
            customTabsIntent = { mockCustomTabsIntent }
            uri = uriParser
        }

    @Test
    fun open() {
        chromeTab.open("https://ilovecats")

        verify { uriParser.invoke("https://ilovecats") }
        verify { mockCustomTabsIntent.launchUrl(activity, mockUri) }
    }
}
