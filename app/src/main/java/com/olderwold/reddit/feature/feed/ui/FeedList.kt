package com.olderwold.reddit.feature.feed.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.olderwold.reddit.feature.feed.domain.FeedItem
import com.olderwold.reddit.feature.web.WebPage

@Composable
internal fun RedditHotList(
    feedViewModel: FeedViewModel,
    webPage: WebPage
) {
    val pager = remember { feedViewModel.pager }
    val lazyPagingItems: LazyPagingItems<FeedItem> = pager.flow.collectAsLazyPagingItems()

    LazyColumn {
        if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
            item { Waiting() }
        }

        itemsIndexed(lazyPagingItems) { index, item: FeedItem? ->
            if (item == null) {
                Waiting()
            } else {
                FeedItem(
                    item,
                    onItemClicked = {
                        item.url?.let(webPage::open)
                    }
                )
            }
        }

        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
internal fun FeedItem(
    @PreviewParameter(provider = FeedItemProvider::class) item: FeedItem,
    onItemClicked: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClicked() },
        elevation = 8.dp,

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(item.id, fontSize = 20.sp)
            if (!item.author.isNullOrEmpty()) {
                Text("${item.author}", fontSize = 20.sp)
            }
            if (!item.title.isNullOrEmpty()) {
                Text("${item.title}", fontSize = 15.sp)
            }
        }
    }
}

@Composable
internal fun Waiting() {
    Text(
        text = "Waiting for items to load from the backend",
        modifier = Modifier
            .fillMaxWidth(fraction = 1f)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

internal class FeedItemProvider : PreviewParameterProvider<FeedItem> {
    override val values: Sequence<FeedItem> = sequenceOf(
        FeedItem(
            id = "id",
            author = "Frodo",
            title = "My Precious",
            url = "https://nebuchadnezzar.com/"
        )
    )
}
