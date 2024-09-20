package com.hometask.flickrapp.model


import com.google.gson.annotations.SerializedName

data class FlickrModelDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("perpage")
    val perpage: Int,
    @SerializedName("photo")
    val photo: List<Photo>,
    @SerializedName("total")
    val total: Int
)