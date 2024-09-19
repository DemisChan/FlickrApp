package com.hometask.flickrapp.domain

sealed class FlickrStateEvents {
    // respects class hierarchy
    data object InitEvent : FlickrStateEvents()
//    data class PhotoClickedEvent(val photo: Photo) : FlickrStateEvents()
}