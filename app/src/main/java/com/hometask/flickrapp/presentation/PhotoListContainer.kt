package com.hometask.flickrapp.presentation

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(16.dp),
    ) {
        items(photos.size) {
            PhotoItem(photo = photos[it])
        }

    }
}

@Composable
fun PhotoItem(photo: Photo) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Gray)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = "https://farm${photo.farm}.staticflickr.com/${photo.server}/buddyicons/${photo.owner}.jpg",
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = photo.ownername, style = MaterialTheme.typography.bodyMedium)
        }

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
}