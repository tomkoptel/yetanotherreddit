package com.olderwold.reddit.data

import okreplay.OkReplayConfig
import okreplay.OkReplayInterceptor
import okreplay.RecorderRule
import okreplay.TapeMode
import org.junit.Rule
import org.junit.rules.TestRule

fun tape(builder: OkReplayConfig.Builder.() -> Unit = {}): TapeRule {
    val okReplayInterceptor = OkReplayInterceptor()
    val configuration = OkReplayConfig.Builder()
        .defaultMode(TapeMode.READ_ONLY)
        .sslEnabled(true)
        .interceptor(okReplayInterceptor)
        .apply(builder)
        .build()

    return TapeRule(
        delegate = RecorderRule(configuration),
        api = RedditClient { addInterceptor(okReplayInterceptor) }
    )
}

class TapeRule(
    private val delegate: TestRule,
    val api: RedditClient,
) : TestRule by delegate
