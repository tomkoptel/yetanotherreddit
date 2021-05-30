package com.olderwold.reddit.ui.element

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.olderwold.reddit.MainViewModel

@Composable
fun RedditHotList(mainViewModel: MainViewModel) {
    val feed: State<MainViewModel.Feed> = mainViewModel.feedState.collectAsState()
    val items = feed.value.items
    val lastIndex = items.lastIndex
    LazyColumn {
        itemsIndexed(items) { index, item ->
            Text(item)

            if (lastIndex == index) {
                LaunchedEffect(Unit) {
                    mainViewModel.loadMore()
                }
            }
        }
    }
}
