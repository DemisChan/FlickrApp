package com.hometask.flickrapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hometask.flickrapp.repository.FlickrRepo
import kotlinx.coroutines.launch

class FlickrViewModel: ViewModel() {
    private val repository = FlickrRepo()

     fun getPhotos() {
        viewModelScope.launch {
            val response = repository.getPhotos()
            try {
                Log.d("ViewModel", "getPhotos: ${response.body()}")

            } catch (e: Exception) {
                Log.d("ViewModel", "error: ${e.message}")

            }
        }
    }

}

