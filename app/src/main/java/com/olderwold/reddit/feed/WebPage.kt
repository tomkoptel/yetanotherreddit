package com.olderwold.reddit.feed

import android.app.Activity
import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsServiceConnection
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
         * We do bind service to see if the device supports this capability.
         * We do not use [PackageManager.queryIntentServices] to avoid messing with package visibility on Android 11.
         * @see [package visibility](https://developer.android.com/about/versions/11/privacy/package-visibility)
         */
        private fun isChromeCustomTabsSupported(): Boolean {
            val serviceIntent = Intent("android.support.customtabs.action.CustomTabsService")
            serviceIntent.setPackage("com.android.chrome")

            val serviceConnection: CustomTabsServiceConnection =
                object : CustomTabsServiceConnection() {
                    override fun onCustomTabsServiceConnected(
                        componentName: ComponentName,
                        customTabsClient: CustomTabsClient
                    ) = Unit

                    override fun onServiceDisconnected(name: ComponentName?) = Unit
                }

            val customTabsSupported: Boolean = application.bindService(
                serviceIntent,
                serviceConnection,
                Context.BIND_AUTO_CREATE or Context.BIND_WAIVE_PRIORITY
            )
            application.unbindService(serviceConnection)

            return customTabsSupported
        }
    }
}
