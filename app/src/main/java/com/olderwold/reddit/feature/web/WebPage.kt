package com.olderwold.reddit.feature.web

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.ResolveInfo
import android.util.Log
import dagger.Reusable
import javax.inject.Inject

internal interface WebPage {
    fun open(url: String)

    @Reusable
    class Factory @Inject constructor(
        private val application: Application,
    ) {
        fun create(activity: Activity): WebPage {
            return if (isChromeCustomTabsSupported()) {
                Log.i("WebPage.Factory", "Chrome tabs supported on this device")
                ChromeTabWebPage(activity)
            } else {
                Log.i("WebPage.Factory", "Chrome tabs not supported on this device")
                WebViewPage(activity)
            }
        }

        /**
         * Checks if Custom Tabs supported.
         */
        private fun isChromeCustomTabsSupported(): Boolean {
            val serviceIntent = Intent("android.support.customtabs.action.CustomTabsService")
            serviceIntent.setPackage("com.android.chrome")
            val resolveInfos: MutableList<ResolveInfo> = application.packageManager.queryIntentServices(serviceIntent, 0)
            return resolveInfos.isNotEmpty()
        }
    }
}
