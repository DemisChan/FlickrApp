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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.hometask.flickrapp.model.Description
import com.hometask.flickrapp.model.Photo


@Composable
fun PhotoDetailScreen(photo: Photo, modifier: Modifier = Modifier) {
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
//        AnnotatedText(
//            description = photo.description.content,
//            title = "Description",
//            style = MaterialTheme.typography.bodyLarge
//        )
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


@Preview
@Composable
fun PhotoDetailScreenPreview() {
    val photo = Photo(
        id = "123",
        farm = 1,
        isfamily = 0,
        isfriend = 0,
        ispublic = 1,
        owner = "Demis",
        secret = "abc123",
        server = "4567",
        iconserver = "1234",
        iconfarm = 1,
        ownername = "John Doe",
        datetaken = "2023-01-01",
        description = Description(content = "This is a sample description."),
        title = "Sample Photo"
    )
    PhotoDetailScreen(photo = photo)
}