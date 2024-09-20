package com.hometask.flickrapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hometask.flickrapp.domain.FlickrStateEvents
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
                    PhotoListScreen(
                        viewModel = viewModel,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun PhotoListScreen(
    viewModel: FlickrViewModel,
    innerPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(FlickrStateEvents.InitEvent)
    }

    // Display photos from uiState.photos
    val photos = uiState.value.photos
    Log.d("PhotoListScreen", "Photos: $photos")
    LazyVerticalGrid (
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxSize(),
        contentPadding = innerPadding
    ) {


    }
}

@Preview(showBackground = true)
@Composable
fun PhotoScreen() {
    FlickrAppTheme {
    }
}