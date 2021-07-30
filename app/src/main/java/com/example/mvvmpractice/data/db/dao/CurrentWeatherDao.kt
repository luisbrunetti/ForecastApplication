package com.example.mvvmpractice.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmpractice.data.db.entity.CURRENT_WEATHER_ID
import com.example.mvvmpractice.data.db.entity.Current
import com.example.mvvmpractice.data.db.unitlocalized.ImperialCurrentWeatherEntry
import com.example.mvvmpractice.data.db.unitlocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    //Update and Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE) //ALWAYS CONFLICT BY ID- Throw instance with same ID//Cada vez se que corra el programa se tendra un proelbma por presentar el imsmo ID por ello, se pone REPLACE
    fun upsert(weatherEntry:Current)

    @Query("select * from current_weather where id =$CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry> //Observe by changes so whenever the current weather changes in the database the live data will change to

    @Query("select * from current_weather where id =$CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry> //Observe by changes so wheenver the current weather changes in the database the live data will change to


}