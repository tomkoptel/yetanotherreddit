package com.olderwold.reddit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olderwold.reddit.data.RedditClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val redditClient: RedditClient,
): ViewModel() {
    private val _feedState = MutableStateFlow(Feed(emptyList(), ""))
    val feedState: StateFlow<Feed> get() = _feedState

    init {
        initialFetch()
    }

    fun loadMore() {
        initialFetch()
    }

    private fun initialFetch() {
        viewModelScope.launch {
            try {
                _feedState.value = Feed(redditClient.hotListing(limit = 25), "")
            } catch (ex: Exception) {
                error(ex)
            }
        }
    }

    data class Feed(
        val items: List<String>,
        val next: String,
    )
}
