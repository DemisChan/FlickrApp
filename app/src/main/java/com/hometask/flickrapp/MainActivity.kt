package com.hometask.flickrapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.hometask.flickrapp.presentation.PhotoListContainer
import com.hometask.flickrapp.ui.theme.FlickrAppTheme
import com.hometask.flickrapp.viewmodel.FlickrViewModel

class MainActivity : ComponentActivity() {
    val viewModel: FlickrViewModel by viewModels()


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlickrAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "FlickrApp",
                                    textAlign = TextAlign.Center
                                )
                            }
                        )
                    }
                ) { innerPadding ->
                    PhotoListContainer(viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
