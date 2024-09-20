package com.hometask.flickrapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hometask.flickrapp.domain.FlickrStateEvents
import com.hometask.flickrapp.domain.FlickrUiState
import com.hometask.flickrapp.repository.FlickrRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlickrViewModel : ViewModel() {
    private val repository = FlickrRepo()

    private val _uiState = MutableStateFlow(FlickrUiState())
    val uiState: StateFlow<FlickrUiState> = _uiState.asStateFlow()

    fun onEvent(event: FlickrStateEvents) {
        when (event) {
            is FlickrStateEvents.InitEvent -> {
                viewModelScope.launch {
                    try {
                        val response = repository.getPhotos()

                        if (response.isSuccessful) {
                            _uiState.value = FlickrUiState(
                                photos = response.body()?.photo ?: emptyList()
                            )
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
                        // Update uiState with error state if necessary
                    }
                }
            }

        }

    }
}

