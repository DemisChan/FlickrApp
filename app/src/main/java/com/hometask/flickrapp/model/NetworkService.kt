package com.hometask.flickrapp.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    @GET("/services/rest/")
    suspend fun getListPhotos(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallback: Int,
    ): Response<FlickerModelDto>
}

// add okhttp interceptor