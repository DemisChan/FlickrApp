package com.hometask.flickrapp

import com.hometask.flickrapp.domain.FlickrStateEvents
import com.hometask.flickrapp.viewmodel.FlickrViewModel
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class FlickrViewModelTest {

    @Test
    fun replicate_original_test_function_with_mockk() {
        val viewModel = spyk(FlickrViewModel())
        val event = FlickrStateEvents.InitEvent

        viewModel.onEvent(event)

        verify { viewModel.getPhotos() }
    }
}