package com.hometask.flickrapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hometask.flickrapp.domain.FlickrStateEvents
import com.hometask.flickrapp.domain.FlickrUiState
import com.hometask.flickrapp.model.Photo
import com.hometask.flickrapp.viewmodel.FlickrViewModel


@Composable
fun UserPhotoListScreenContainer(
    viewModel: FlickrViewModel,
    userId: String,
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit = {},
) {
    LaunchedEffect(Unit) {
        viewModel.onEvent(FlickrStateEvents.LoadUserPhotosEvent(userId))
    }
    val uiState = viewModel.uiState.collectAsState()

    UserPhotoListScreen(
        modifier = modifier,
        onStateEvent = { event -> viewModel.onEvent(event) },
        onPhotoClick = { photo ->
            viewModel.onEvent(FlickrStateEvents.PhotoClickedEvent(photo))
            onNavigate("photoDetail/${photo.id}")
        },
        state = uiState.value,
    )
}


@Composable
fun UserPhotoListScreen(
    modifier: Modifier = Modifier,
    state: FlickrUiState = FlickrUiState(),
    onPhotoClick: (Photo) -> Unit,
    onStateEvent: (FlickrStateEvents) -> Unit,
) {
    val photos = state.userPhotos

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(16.dp),
    ) {
        items(photos.size) {
            PhotoItem(
                photo = photos[it],
                onPhotoClick = { onPhotoClick(it) },
            )
        }
    }
}