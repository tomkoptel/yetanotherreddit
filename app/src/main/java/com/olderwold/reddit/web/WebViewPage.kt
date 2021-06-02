package com.olderwold.reddit.web

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.annotation.RestrictTo
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
internal class WebViewPage @Inject constructor(
    private val activity: Activity,
) : WebPage {
    @set:RestrictTo(RestrictTo.Scope.TESTS)
    var actionIntent: (url: String) -> Intent = { url ->
        Intent(Intent.ACTION_VIEW).apply { Uri.parse(url) }
    }

    override fun open(url: String) {
        try {
            activity.startActivity(actionIntent(url))
        } catch (ex: ActivityNotFoundException) {
            Log.i("WebViewPage", "Can not resolved activity for url=$url")
        }
    }
}
