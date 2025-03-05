package com.example.jtkwibu.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.AsyncImage
import com.example.jtkwibu.data.AnimeEntity
import com.example.jtkwibu.viewmodel.HomeViewModel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.hilt.navigation.compose.hiltViewModel




@Composable
fun HomeScreen(
    onAnimeClick: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val animeList = viewModel.animeList.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(animeList.itemCount) { index ->
            animeList[index]?.let { anime ->  // Gunakan get(index) yang lebih aman
                NetflixAnimeItem(
                    anime = anime,
                    onClick = { onAnimeClick(anime.malId) },
                    onBookmarkClick = {
                        viewModel.toggleBookmark(anime.malId, anime.isBookmarked)
                    }
                )
            }
        }
    }
}

@Composable
fun NetflixAnimeItem(anime: AnimeEntity, onClick: () -> Unit, onBookmarkClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = MaterialTheme.shapes.medium,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = anime.imageUrl,
                contentDescription = anime.title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black)
                        )
                    )
            )
            Text(
                text = anime.title,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(4.dp),
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1
            )
            IconButton(
                onClick = onBookmarkClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            ) {
                Icon(
                    imageVector = if (anime.isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Bookmark",
                    tint = if (anime.isBookmarked) Color.Red else Color.White
                )
            }
        }
    }
}

