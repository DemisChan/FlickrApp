package com.hometask.flickrapp.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NetworkService {

    @GET("services/rest/")
    suspend fun getResponse(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("format") format: String,
        @Query("tags") tags: String?,
        @Query("safe_search") safeSearch: Int?,
        @Query("extras") extras: String?,
        @Query("nojsoncallback") noJsonCallback: Int,
    ): Response<MainPhotoDto>
}