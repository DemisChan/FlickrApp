package com.hometask.flickrapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.hometask.flickrapp.PhotoListScreen
import com.hometask.flickrapp.domain.FlickrStateEvents
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