package com.hometask.flickrapp.model


import com.google.gson.annotations.SerializedName

data class Extra(
    @SerializedName("explore_date")
    val exploreDate: String,
    @SerializedName("next_prelude_interval")
    val nextPreludeInterval: Int
)