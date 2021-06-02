package com.olderwold.reddit.lib.okhttp

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import java.util.concurrent.TimeUnit

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val invocation: Invocation? = request.tag(Invocation::class.java)
        val annotation: Cacheable? = invocation?.method()?.getAnnotation(Cacheable::class.java)

        val response = chain.proceed(chain.request())
        return if (annotation == null) {
            response
        } else {
            val cacheControl = CacheControl.Builder()
                .maxAge(annotation.until, annotation.unit)
                .build()

            return response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }
}
