package com.hometask.flickrapp.presentation

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hometask.flickrapp.domain.FlickrStateEvents
import com.hometask.flickrapp.domain.FlickrUiState
import com.hometask.flickrapp.model.Photo
import com.hometask.flickrapp.viewmodel.FlickrViewModel


@Composable
fun PhotoListContainer(
    viewModel: FlickrViewModel,
    modifier: Modifier = Modifier,
) {

    val uiState = viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.onEvent(FlickrStateEvents.InitEvent)
    }

    PhotoListScreen(
        modifier = modifier,
        state = uiState.value,
        onStateEvent = viewModel::onEvent
    )
}

@Composable
fun PhotoListScreen(
    modifier: Modifier = Modifier,
    state: FlickrUiState = FlickrUiState(),
    onStateEvent: (FlickrStateEvents) -> Unit,
) {
    // Display photos from uiState.photos
    val photos = state.photos
    Log.d("PhotoListScreen", "Photos: $photos")
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.padding(16.dp),
    ) {
        items(photos.size) {
            PhotoItem(photo = photos[it])
        }

    }
}

@Composable
fun PhotoItem(photo: Photo) {
    AsyncImage(
        modifier = Modifier
            .aspectRatio(9 / 16f)
            .width(100.dp)
            .border(2.dp, Color.Gray)
            .padding(4.dp),
        contentScale = ContentScale.Crop,
        model = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
        contentDescription = null,
    )

}