package com.olderwold.reddit.feature.web

import android.app.Activity
import android.net.Uri
import androidx.annotation.RestrictTo
import androidx.browser.customtabs.CustomTabsIntent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
internal class ChromeTabWebPage@Inject constructor(
    private val activity: Activity,
) : WebPage {
    @set:RestrictTo(RestrictTo.Scope.TESTS)
    var customTabsIntent: () -> CustomTabsIntent = {
        CustomTabsIntent.Builder().build()
    }

    @set:RestrictTo(RestrictTo.Scope.TESTS)
    var uri: (String) -> Uri = Uri::parse

    override fun open(url: String) {
        customTabsIntent().launchUrl(activity, uri(url))
    }
}
