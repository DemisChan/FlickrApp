package com.hometask.flickrapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hometask.flickrapp.domain.FlickrStateEvents
import com.hometask.flickrapp.domain.FlickrUiState
import com.hometask.flickrapp.model.Photo
import com.hometask.flickrapp.repository.FlickrRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlickrViewModel : ViewModel() {
    private val repository = FlickrRepo()

    private val _uiState = MutableStateFlow(FlickrUiState())
    val uiState: StateFlow<FlickrUiState> = _uiState.asStateFlow()

    fun onEvent(event: FlickrStateEvents) {
        when (event) {
            is FlickrStateEvents.InitEvent -> {
                getPhotos()
            }
            is FlickrStateEvents.PhotoClickedEvent -> {
                handlePhotoClick(event.photo)
            }

        }

    }

    private fun getPhotos() {
        viewModelScope.launch {
            try {
                val response = repository.getPhotosWithTags()

                if (response.isSuccessful) {
                    Log.d("FlickrViewModel", "Response: ${response.body()}")
                    _uiState.update {
                        it.copy(
                            photos = response.body()?.photos?.photo ?: emptyList()
                        )
                    }

                } else {
                    // Handle error
                    Log.e(
                        "FlickrViewModel",
                        "Error fetching photos: ${response.errorBody()}"
                    )
                    // Update uiState with error state if necessary
                }
            } catch (e: Exception) {
                // Handle exception
                Log.e("FlickrViewModel", "Exception fetching photos", e)
                e.message
                // Update uiState with error state if necessary

            }
        }
    }
    private fun handlePhotoClick(photo: Photo) {
        // Handle the photo click event
        Log.d("FlickrViewModel", "Photo clicked: $photo")
        // You can add more logic here as needed
    }
}

