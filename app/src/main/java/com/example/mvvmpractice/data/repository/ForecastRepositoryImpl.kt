package com.example.mvvmpractice.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import com.example.mvvmpractice.data.db.dao.CurrentWeatherDao
import com.example.mvvmpractice.data.db.unitlocalized.UnitSpecificCurrentWatherEntry
import com.example.mvvmpractice.data.network.WeatherNetworkDataSource
import com.example.mvvmpractice.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class ForecastRepositoryImpl (
    private val currentWeatherDao : CurrentWeatherDao,
    private val weatherNetowrkDataSource: WeatherNetworkDataSource
) : ForecastRepository {
    //Lifecycles doesn't matter inside of repository
    init{
        //Repository Doesnt have anny life cuycle y dont need to worry about this repository being destroyed
        //Whn it's destroyed all the app is destryec so life cycle doesn't play a role in this observer
        weatherNetowrkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            // persist
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWatherEntry> {
        return withContext(Dispatchers.IO){ //returns a value instead of Launch
            initWeatherData()
            return@withContext if(metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        // Launche Coroutine
        // Globa Scope is not the best way to launmch corroutines
        // If the fragment get destroyed if u will get an exception
        //We can use Global in Respotory becuse it doesnt have a lifecycle
        GlobalScope.launch (Dispatchers.IO){ // Returns a job
            currentWeatherDao.upsert(fetchedWeather.current)
        }
    }
    private suspend fun fetchCurrentWeather(){
        //There is going to fetch the weather and then i will update into the current weather
        weatherNetowrkDataSource.fetchCurrentWeather(
            "New York"
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun initWeatherData(){
        if(isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean{
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}