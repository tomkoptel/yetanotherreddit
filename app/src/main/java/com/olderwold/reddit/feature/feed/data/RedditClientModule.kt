package com.olderwold.reddit.feature.feed.data

import com.olderwold.reddit.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor

@InstallIn(SingletonComponent::class)
@Module
internal class RedditClientModule {
    @Provides
    @Reusable
    fun redditApi(): RedditApi {
        return RedditApi {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }
    }
}
