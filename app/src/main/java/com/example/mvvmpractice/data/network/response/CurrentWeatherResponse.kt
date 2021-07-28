package com.example.mvvmpractice.data.network.response


import com.example.mvvmpractice.data.db.entity.Request
import com.example.mvvmpractice.data.db.entity.Current
import com.example.mvvmpractice.data.db.entity.Location
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current")
    val current: Current,
    @SerializedName("location")
    val location: Location,
    @SerializedName("request")
    val request: Request
)