package com.olderwold.reddit.data

import com.olderwold.reddit.data.dto.RedditHot
import com.olderwold.reddit.data.dto.RedditHotJsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditClient {
    suspend fun hotListing(
        limit: Int,
        after: String? = null
    ): List<String>

    companion object {
        operator fun invoke(
            retrofitBuilder: Retrofit.Builder.() -> Unit = {},
            clientBuilder: OkHttpClient.Builder.() -> Unit = {}
        ): RedditClient {
            val moshi = Moshi.Builder().build()
            val moshiWithAdapter = Moshi.Builder()
                .add(RedditHot::class.java, RedditHotJsonAdapter(moshi))
                .build()
            val moshiConverterFactory = MoshiConverterFactory.create(moshiWithAdapter)
            val api = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(moshiConverterFactory)
                .client(
                    OkHttpClient.Builder()
                        .apply(clientBuilder)
                        .build()
                )
                .apply(retrofitBuilder)
                .build()
                .create(Api::class.java)

            return Impl(
                api = api,
            )
        }
    }

    private class Impl(
        private val api: Api
    ) : RedditClient {
        override suspend fun hotListing(
            limit: Int,
            after: String?
        ): List<String> {
            return api.hotListing(limit = limit, after = after)
                .data?.children?.mapNotNull { it.kind }.orEmpty()
        }
    }

    interface Api {
        @GET("/r/Android/hot.json")
        suspend fun hotListing(
            @Query("limit") limit: Int,
            @Query("after") after: String? = null,
        ): RedditHot
    }
}
