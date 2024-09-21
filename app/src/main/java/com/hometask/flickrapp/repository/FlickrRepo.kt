package com.hometask.flickrapp.repository

import com.hometask.flickrapp.model.NetworkService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FlickrRepo {

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.flickr.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val flickrApi = retrofit.create(NetworkService::class.java)

    suspend fun getListPhotos() = flickrApi.getResponse(
        method = "flickr.interestingness.getList",
        apiKey = "501814c32f4dbaa6ae6c8b571063b6ee",
        format = "json",
        tags = null,
        safeSearch = null,
        extras = null,
        noJsonCallback = 1
    )

    suspend fun getPhotos() = flickrApi.getResponse(
        method = "flickr.photos.getRecent",
        apiKey = "501814c32f4dbaa6ae6c8b571063b6ee",
        format = "json",
        tags = null,
        safeSearch = null,
        extras = null,
        noJsonCallback = 1
    )

    suspend fun getPhotosWithTags() = flickrApi.getResponse(
        method = "flickr.photos.search",
        apiKey = "501814c32f4dbaa6ae6c8b571063b6ee",
        tags = "Yorkshire",
        format = "json",
        safeSearch = 1,
        extras = "icon_server,owner_name",
        noJsonCallback = 1
    )
}