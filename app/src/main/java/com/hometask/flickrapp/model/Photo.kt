package com.hometask.flickrapp.model


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("farm")
    val farm: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("isfamily")
    val isfamily: Int,
    @SerializedName("isfriend")
    val isfriend: Int,
    @SerializedName("ispublic")
    val ispublic: Int,
    @SerializedName("owner")
    val owner: String,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("server")
    val server: String,
    @SerializedName("iconserver")
    val iconserver: String,
    @SerializedName("iconfarm")
    val iconfarm: Int,
    @SerializedName("ownername")
    val ownername: String,
    @SerializedName("title")
    val title: String
)