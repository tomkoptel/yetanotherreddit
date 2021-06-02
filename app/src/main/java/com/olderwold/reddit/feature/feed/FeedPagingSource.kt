package com.olderwold.reddit.feature.feed

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.olderwold.reddit.feature.feed.domain.FeedItem
import com.olderwold.reddit.feature.feed.domain.GetFeedPage
import dagger.Reusable
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@Reusable
internal class FeedPagingSource @Inject constructor(
    private val getFeedPage: GetFeedPage,
) : PagingSource<String, FeedItem>() {
    override fun getRefreshKey(state: PagingState<String, FeedItem>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
                ?: state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, FeedItem> {
        return try {
            val feedPage = getFeedPage(params.loadSize - 1, params.key)
            LoadResult.Page(
                data = feedPage.data,
                prevKey = feedPage.prevKey,
                nextKey = feedPage.nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}
