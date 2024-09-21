package com.hometask.flickrapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hometask.flickrapp.domain.FlickrStateEvents
import com.hometask.flickrapp.domain.FlickrUiState
import com.hometask.flickrapp.model.Photo
import com.hometask.flickrapp.presentation.PhotoListContainer
import com.hometask.flickrapp.ui.theme.FlickrAppTheme
import com.hometask.flickrapp.viewmodel.FlickrViewModel

class MainActivity : ComponentActivity() {
    val viewModel: FlickrViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlickrAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PhotoListContainer(viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
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
        modifier = Modifier.padding(16.dp),
    ) {
        items(photos.size) {
            PhotoItem(photo = photos[it])
        }

    }
}

@Composable
fun PhotoItem(photo:Photo) {
    AsyncImage(
        modifier = Modifier
            .aspectRatio(9/16f)
            .width(100.dp),
        contentScale = ContentScale.Crop,
        model = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
        contentDescription = null,
    )

}

@Preview(showBackground = true)
@Composable
fun PhotoScreen() {
    FlickrAppTheme {
    }
}