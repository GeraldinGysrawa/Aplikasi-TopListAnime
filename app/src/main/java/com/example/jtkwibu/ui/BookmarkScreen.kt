package com.example.jtkwibu.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jtkwibu.viewmodel.BookmarkViewModel

@Composable
fun BookmarkScreen(
    onAnimeClick: (Int) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val bookmarked = viewModel.bookmarkedAnime.collectAsState(initial = emptyList()).value

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(bookmarked) { anime ->
            NetflixAnimeItem(
                anime = anime,
                onClick = { onAnimeClick(anime.malId) },
                onBookmarkClick = { viewModel.toggleBookmark(anime.malId, anime.isBookmarked) }
            )
        }
    }
}

