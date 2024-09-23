package com.hometask.flickrapp.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
fun PhotoListScreenContainer(
    viewModel: FlickrViewModel,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {},
) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(FlickrStateEvents.InitEvent)
    }
    val uiState = viewModel.uiState.collectAsState()
    PhotoListScreen(
        modifier = modifier,
        onStateEvent = { event -> viewModel.onEvent(event) },
        state = uiState.value,
        // this should be in handleCLick in viewmodel
        onPhotoClick = { photo ->
            viewModel.onEvent(FlickrStateEvents.PhotoClickedEvent(photo))
            onNavigate("photoDetail/${photo.id}")
        },
        onUserClick = { userId ->
            onNavigate("userPhotos/$userId")
        }
    )
}


@Composable
fun PhotoListScreen(
    state: FlickrUiState = FlickrUiState(),
    modifier: Modifier = Modifier,
    onStateEvent: (FlickrStateEvents) -> Unit,
    onPhotoClick: (Photo) -> Unit,
    onUserClick: (String) -> Unit,
) {
    val photos = state.photos

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(16.dp),
    ) {
        items(photos.size) {
            PhotoItem(
                photo = photos[it],
                onPhotoClick = onPhotoClick,
                onUserClick = onUserClick
            )
        }
    }
}

@Composable
fun PhotoItem(
    photo: Photo,
    onPhotoClick: (Photo) -> Unit,
    onUserClick: (String) -> Unit = { },
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Gray)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp),
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
            Text(
                text = photo.ownername,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .clickable {
                        onUserClick(photo.owner)
                    }
            )
        }

        AsyncImage(
            modifier = Modifier
                .aspectRatio(9 / 16f)
                .width(100.dp)
                .border(2.dp, Color.Gray)
                .padding(4.dp)
                .clickable {
                    onPhotoClick(photo)
                },
            contentScale = ContentScale.Crop,
            model = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
            contentDescription = null,
        )
    }
}