package com.olderwold.reddit.feature.feed.data

import com.olderwold.reddit.feature.feed.data.dto.RedditHot
import com.olderwold.reddit.feature.feed.data.dto.RedditHotJsonAdapter
import com.olderwold.reddit.lib.okhttp.Cacheable
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

internal interface RedditApi {
    companion object {
        operator fun invoke(
            retrofitBuilder: Retrofit.Builder.() -> Unit = {},
            clientBuilder: OkHttpClient.Builder.() -> Unit = {}
        ): RedditApi {
            val moshi = Moshi.Builder().build()
            val moshiWithAdapter = Moshi.Builder()
                .add(RedditHot::class.java, RedditHotJsonAdapter(moshi))
                .build()
            val moshiConverterFactory = MoshiConverterFactory.create(moshiWithAdapter)
            return Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(moshiConverterFactory)
                .client(
                    OkHttpClient.Builder()
                        .apply(clientBuilder)
                        .build()
                )
                .apply(retrofitBuilder)
                .build()
                .create(RedditApi::class.java)
        }
    }

    @Cacheable(until = 5, unit = TimeUnit.MINUTES)
    @GET("/r/Android/hot.json")
    suspend fun hotListing(
        @Query("limit") limit: Int,
        @Query("after") after: String? = null,
    ): RedditHot
}
