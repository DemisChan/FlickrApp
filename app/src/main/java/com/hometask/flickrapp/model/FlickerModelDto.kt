package com.hometask.flickrapp.model

import com.google.gson.annotations.SerializedName

data class FlickerModelDto (
    @SerializedName("extra")
    val extra: Extra,
    @SerializedName("photos")
    val photos: Photos,
    @SerializedName("stat")
    val stat: String
)