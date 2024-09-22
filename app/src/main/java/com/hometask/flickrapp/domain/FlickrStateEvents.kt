package com.hometask.flickrapp.domain

import com.hometask.flickrapp.model.Photo

sealed class FlickrStateEvents {
    // respects class hierarchy
    data object InitEvent : FlickrStateEvents()
    data class PhotoClickedEvent(val photo: Photo) : FlickrStateEvents()
}