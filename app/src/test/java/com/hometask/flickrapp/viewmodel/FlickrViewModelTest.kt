package com.hometask.flickrapp.viewmodel

import com.hometask.flickrapp.model.FlickrModelDto
import com.hometask.flickrapp.model.MainPhotoDto
import com.hometask.flickrapp.model.Photo
import com.hometask.flickrapp.repository.FlickrRepo
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FlickrViewModelTest {


    private lateinit var repository: FlickrRepo
    private lateinit var viewModel: FlickrViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        mockkConstructor(FlickrRepo::class)
        every { anyConstructed<FlickrRepo>() } returns repository
        viewModel = FlickrViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `test getPhotos with successful response`() = runTest {
        // Arrange
        val mockResponse = mockk<Response<MainPhotoDto>>()
        val mockFlickrResponse = mockk<MainPhotoDto>()
        val mockPhotos = mockk<FlickrModelDto>()
        val mockPhotoList = listOf(
            Photo(
                id = "1",
                owner = "2",
                secret = "3",
                server = "4",
                farm = 5,
                title = "6",
                ispublic = 7,
                isfriend = 8,
                isfamily = 9,
                iconserver = "10",
                iconfarm = 11,
                ownername = "12",
                datetaken = "13",
                description = mockk(),
            )
        )

        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns mockFlickrResponse
        every { mockFlickrResponse.photos } returns mockPhotos
        every { mockPhotos.photo } returns mockPhotoList
        coEvery { repository.getPhotosWithTags() } returns mockResponse

        // Act
        viewModel.getPhotos()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(mockPhotoList, viewModel.uiState.value.photos)
        coVerify { repository.getPhotosWithTags() }
    }
}

//    @Test
//    fun onEvent() {
//    }
//
//    @Test
//    fun getPhotos() {
//    }
