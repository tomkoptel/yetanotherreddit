package com.olderwold.reddit.feature.web

import android.app.Activity
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
internal class WebPageModule {
    @Provides
    @Reusable
    fun webPage(
        factory: WebPage.Factory,
        activity: Activity
    ): WebPage = factory.create(activity)
}
