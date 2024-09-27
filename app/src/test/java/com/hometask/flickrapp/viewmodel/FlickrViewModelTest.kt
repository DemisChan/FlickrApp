package com.hometask.flickrapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hometask.flickrapp.domain.FlickrStateEvents
import com.hometask.flickrapp.domain.FlickrUiState
import com.hometask.flickrapp.model.Description
import com.hometask.flickrapp.model.FlickrModelDto
import com.hometask.flickrapp.model.MainPhotoDto
import com.hometask.flickrapp.model.Photo
import com.hometask.flickrapp.repository.FlickrRepo
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.runs
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FlickrViewModelTest {

//    @get:Rule
//    val rule = InstantTaskExecutorRule()


    private lateinit var viewModel: FlickrViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun getPhotos_shouldBeCalled_onInitEvent() = runTest {
        mockkConstructor(FlickrViewModel::class)
        every { anyConstructed<FlickrViewModel>().getPhotos() } just runs

        val viewModel = FlickrViewModel()
        viewModel.onEvent(FlickrStateEvents.InitEvent)
        verify { viewModel.getPhotos() }
    }

    @Test
    fun getUserPhotos_shouldBeCalled_onLoadUserPhotosEvent() = runTest {
        mockkConstructor(FlickrViewModel::class)
        every { anyConstructed<FlickrViewModel>().getUserPhotos(any()) } just runs

        val viewModel = FlickrViewModel()
        viewModel.onEvent(FlickrStateEvents.LoadUserPhotosEvent("123"))
        verify { viewModel.getUserPhotos(any()) }
    }

    @Test
    fun getPhotos_shouldUpdateUiState_onSuccessfulResponse() = runTest {
        val mockRepository = mockk<FlickrRepo>()
        val mockPhotos = listOf(
            Photo(
                id = "1",
                owner = "owner1",
                farm = 7710,
                isfriend = 9864,
                ispublic = 3107,
                secret = "tota",
                server = "nibh",
                iconserver = "donec",
                iconfarm = 2159,
                ownername = "Abigail McCormick",
                datetaken = "eleifend",
                description = Description(content = "errem"),
                title = "eu",
                isfamily = 1,
            ),
            Photo(
                id = "2",
                owner = "owner2",
                farm = 7710,
                isfriend = 9864,
                ispublic = 3107,
                secret = "tota",
                server = "nibh",
                iconserver = "donec",
                iconfarm = 2159,
                ownername = "Abigail McCormick",
                datetaken = "eleifend",
                description = Description(content = "erdem"),
                title = "eu",
                isfamily = 1,
            )
        )
        val mockResponse = Response.success(
            MainPhotoDto(
                FlickrModelDto(
                    1,
                    1,
                    1,
                    mockPhotos,
                    1
                )
            )
        )
        every { mockRepository.getPhotosWithTags() } returns mockResponse

    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }
}
