package com.olderwold.reddit.feed

import android.app.Activity
import android.net.Uri
import androidx.annotation.RestrictTo
import androidx.browser.customtabs.CustomTabsIntent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
internal class ChromeTab @Inject constructor(
    private val activity: Activity,
){
    @set:RestrictTo(RestrictTo.Scope.TESTS)
    var customTabsIntent: () -> CustomTabsIntent = {
        CustomTabsIntent.Builder().build()
    }
    @set:RestrictTo(RestrictTo.Scope.TESTS)
    var uri: (String) -> Uri = Uri::parse

    fun open(url: String) {
        customTabsIntent().launchUrl(activity, uri(url))
    }
}
