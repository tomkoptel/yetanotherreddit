package com.olderwold.reddit.data

import com.olderwold.reddit.data.dto.RedditHot
import com.olderwold.reddit.data.dto.RedditHotJsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

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

    @GET("/r/Android/hot.json")
    suspend fun hotListing(
        @Query("limit") limit: Int,
        @Query("after") after: String? = null,
    ): RedditHot
}
