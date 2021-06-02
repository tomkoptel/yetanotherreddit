package com.olderwold.reddit.feature.feed.data

import android.app.Application
import com.olderwold.reddit.BuildConfig
import com.olderwold.reddit.lib.okhttp.NetworkInterceptor
import com.olderwold.reddit.lib.okhttp.OfflineCacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

@InstallIn(SingletonComponent::class)
@Module
internal class RedditApiModule {
    private companion object {
        const val cacheSize = 20 * 1024 * 1024 // 20 MiB
    }

    @Provides
    @Reusable
    fun redditApi(application: Application): RedditApi {
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        val cache = Cache(httpCacheDirectory, cacheSize.toLong())

        return RedditApi {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            cache(cache)
            addNetworkInterceptor(NetworkInterceptor())
            addInterceptor(OfflineCacheInterceptor(application))
        }
    }
}
