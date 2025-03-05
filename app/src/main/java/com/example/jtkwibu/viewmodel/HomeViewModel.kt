package com.example.jtkwibu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jtkwibu.data.AnimeEntity
import com.example.jtkwibu.data.AnimeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AnimeRepository
) : ViewModel() {

    private val originalAnimeList = repository.getTopAnime().cachedIn(viewModelScope)

    // Memodifikasi animeList agar status bookmark ikut berubah setelah klik
    val animeList: Flow<PagingData<AnimeEntity>> = originalAnimeList

    fun toggleBookmark(animeId: Int, isBookmarked: Boolean) {
        viewModelScope.launch {
            repository.setBookmark(animeId, !isBookmarked)
        }
    }
}

