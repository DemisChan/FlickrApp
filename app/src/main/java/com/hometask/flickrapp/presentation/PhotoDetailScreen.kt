package com.hometask.flickrapp.presentation

import android.widget.TextView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.hometask.flickrapp.domain.FlickrStateEvents
import com.hometask.flickrapp.domain.FlickrUiState
import com.hometask.flickrapp.model.Photo
import com.hometask.flickrapp.viewmodel.FlickrViewModel


@Composable
fun PhotoDetailScreenContainer(
    viewModel: FlickrViewModel,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()
    PhotoDetailScreen(
        modifier = modifier,
        onStateEvent = { event -> viewModel.onEvent(event) },
        state = uiState.value
    )
}

@Composable
fun PhotoDetailScreen(
    state: FlickrUiState = FlickrUiState(),
    modifier: Modifier = Modifier,
    onStateEvent: (FlickrStateEvents) -> Unit,
) {
    val photo = state.selectedPhoto ?: return

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        AsyncImage(
            model = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
            contentDescription = "Photo titled: ${photo.title}",
            modifier = Modifier
                .fillMaxSize()
                .border(2.dp, Color.Gray)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Title: ${photo.title}", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        AnnotatedText(
            description = photo.datetaken,
            title = "Date Taken",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        HandleDescription(photo)

    }
}

@Composable
fun AnnotatedText(description: String, title: String, style: TextStyle) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("$title: ")
            }
            withStyle(
                style = SpanStyle(
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                )
            ) {
                append(description)
            }
        },
        style = style
    )
}


@Composable
fun HandleDescription(photo: Photo) {
    if (photo.description.content.contains(Regex("<[^>]*>"))) {
        HtmlText(
            "Description: ${photo.description.content}",
            modifier = Modifier.padding(8.dp)
        )
    } else {
        AnnotatedText(
            description = photo.description.content,
            title = "Description",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// found this in StackOverflow
@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}