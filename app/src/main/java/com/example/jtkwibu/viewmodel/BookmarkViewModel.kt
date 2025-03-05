package com.example.jtkwibu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jtkwibu.data.AnimeEntity
import com.example.jtkwibu.data.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {
    val bookmarkedAnime: Flow<List<AnimeEntity>> = repository.getBookmarks()

    fun toggleBookmark(animeId: Int, isBookmarked: Boolean) {
        viewModelScope.launch {
            repository.setBookmark(animeId, !isBookmarked)
        }
    }
}
