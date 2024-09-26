package com.hometask.flickrapp.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FlickrViewModelTest {

    private lateinit var viewModel: FlickrViewModel
    private val dispatcher = StandardTestDispatcher()
    @Before
    fun setUp() {
    }

    @Test
    fun getUiState() {
    }

    @Test
    fun onEvent() {
    }

    @Test
    fun getPhotos() {
    }
}