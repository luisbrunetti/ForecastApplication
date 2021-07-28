package com.example.mvvmpractice.data.repository

import androidx.lifecycle.LiveData
import com.example.mvvmpractice.data.db.unitlocalized.UnitSpecificCurrentWatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWatherEntry>
}