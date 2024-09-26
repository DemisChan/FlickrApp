package com.hometask.flickrapp.viewmodel

import com.hometask.flickrapp.repository.FlickrRepo
import io.mockk.mockkConstructor
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FlickrViewModelTest {



    private lateinit var repository: FlickrRepo
    private lateinit var viewModel: FlickrViewModel
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        repository = FlickrRepo()
        mockkConstructor(FlickrRepo::class)
        viewModel = FlickrViewModel()
    }

    @Test
    fun `test getPhotos with successful response`()  {
        Dispatchers.setMain(dispatcher)
        viewModel.getPhotos()
        dispatcher.scheduler.runCurrent()

        assertTrue(viewModel.uiState.value.photos.isNotEmpty())
        Dispatchers.resetMain()
    }

    @Test
    fun onEvent() {
    }

    @Test
    fun getPhotos() {
    }
}