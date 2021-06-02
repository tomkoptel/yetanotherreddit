package com.olderwold.reddit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.olderwold.reddit.feed.ChromeTab
import com.olderwold.reddit.ui.element.RedditHotList
import com.olderwold.reddit.ui.theme.YetanotherredditTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    internal lateinit var chromeTab: ChromeTab

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YetanotherredditTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RedditHotList(mainViewModel, chromeTab)
                }
            }
        }
    }
}
