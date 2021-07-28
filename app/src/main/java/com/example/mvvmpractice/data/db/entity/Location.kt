package com.example.mvvmpractice.data.db.entity


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("country")
    val country: String, // United States of America
    @SerializedName("lat")
    val lat: String, // 40.714
    @SerializedName("localtime")
    val localtime: String, // 2021-07-25 17:41
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Int, // 1627234860
    @SerializedName("lon")
    val lon: String, // -74.006
    @SerializedName("name")
    val name: String, // New York
    @SerializedName("region")
    val region: String, // New York
    @SerializedName("timezone_id")
    val timezoneId: String, // America/New_York
    @SerializedName("utc_offset")
    val utcOffset: String // -4.0
)