package com.olderwold.reddit

import com.olderwold.reddit.data.RedditApi
import okreplay.OkReplayConfig
import okreplay.OkReplayInterceptor
import okreplay.RecorderRule
import okreplay.TapeMode
import org.junit.rules.TestRule

internal fun tape(builder: OkReplayConfig.Builder.() -> Unit = {}): TapeRule {
    val okReplayInterceptor = OkReplayInterceptor()
    val configuration = OkReplayConfig.Builder()
        .defaultMode(TapeMode.READ_ONLY)
        .sslEnabled(true)
        .interceptor(okReplayInterceptor)
        .apply(builder)
        .build()

    return TapeRule(
        delegate = RecorderRule(configuration),
        api = RedditApi { addInterceptor(okReplayInterceptor) }
    )
}

internal class TapeRule(
    private val delegate: TestRule,
    val api: RedditApi,
) : TestRule by delegate
