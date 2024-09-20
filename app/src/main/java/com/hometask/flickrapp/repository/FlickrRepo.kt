package com.hometask.flickrapp.repository

import com.hometask.flickrapp.model.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FlickrRepo {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val flickrApi = retrofit.create(NetworkService::class.java)

    suspend fun getListPhotos() = flickrApi.getResponse("flickr.interestingness.getList","501814c32f4dbaa6ae6c8b571063b6ee", "json", 1)

    suspend fun getPhotos() = flickrApi.getResponse("flickr.photos.recent","501814c32f4dbaa6ae6c8b571063b6ee", "json", 1)

    suspend fun getPhotosWithTags() = flickrApi.getResponse("flickr.photos.search","501814c32f4dbaa6ae6c8b571063b6ee","YBS", "json", 1)
}