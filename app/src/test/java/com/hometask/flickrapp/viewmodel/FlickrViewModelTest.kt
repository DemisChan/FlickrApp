package com.hometask.flickrapp.viewmodel

import com.hometask.flickrapp.domain.FlickrUiState
import com.hometask.flickrapp.model.Description
import com.hometask.flickrapp.model.Photo
import com.hometask.flickrapp.repository.FlickrRepo
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.unmockkAll
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
        val photos = listOf(
            Photo(
                id = "1",
                owner = "owner1",
                secret = "secret1",
                server = "server1",
                farm = 1,
                title = "title1",
                ispublic = 1,
                isfriend = 0,
                isfamily = 0,
                ownername = "ownername1",
                datetaken = "datetaken1",
                description = Description(content = "content1"),
                iconserver = "iconserver1",
                iconfarm = 1
            ),
            Photo(
                id = "2",
                owner = "owner2",
                secret = "secret1",
                server = "server1",
                farm = 1,
                title = "title1",
                ispublic = 1,
                isfriend = 0,
                isfamily = 0,
                ownername = "ownername1",
                datetaken = "datetaken1",
                description = Description(content = "content1"),
                iconserver = "iconserver1",
                iconfarm = 1
            )
        )
        val response = Response.success(FlickrUiState(photos = photos).photos)
        coEvery { repository.getPhotosWithTags().body()?.photos?.photo } returns response.body()

        viewModel.getPhotos()
        assert(viewModel.uiState.value.photos == photos)

    }
}

//    @Test
//    fun onEvent() {
//    }
//
//    @Test
//    fun getPhotos() {
//    }
