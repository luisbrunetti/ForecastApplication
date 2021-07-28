package com.example.mvvmpractice.data.network

import androidx.lifecycle.LiveData
import com.example.mvvmpractice.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String
    )
}