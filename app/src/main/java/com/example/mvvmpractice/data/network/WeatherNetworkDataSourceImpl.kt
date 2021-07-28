package com.example.mvvmpractice.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmpractice.ApixuWeatherApiService
import com.example.mvvmpractice.data.network.response.CurrentWeatherResponse
import com.example.mvvmpractice.internal.NoConnectivityException

//Encapsulation Principe
class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSource {

    private val _donwloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    //Automatically cast the mutableLive Data to be online Live Data
    //So the client code requesting downloaded current weather will not be able to change it
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _donwloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try{
            val fetchedCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location)
                .await()
            //DOWNLOADED CURRENT WEATHER IS LIVEDATE AND
            //LIVE DATA CAN NOT BE CHANGED
            _donwloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }catch (e : NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}