package com.hometask.flickrapp.domain

import com.hometask.flickrapp.model.Photo

data class FlickrUiState(
    val photos: List<Photo> = emptyList(),
)