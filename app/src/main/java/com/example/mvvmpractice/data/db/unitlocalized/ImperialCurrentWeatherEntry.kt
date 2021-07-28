package com.example.mvvmpractice.data.db.unitlocalized

import androidx.room.ColumnInfo

data class ImperialCurrentWeatherEntry (
    @ColumnInfo(name = "tempF")
    override val temperature: Double,
    @ColumnInfo(name= "condition_icon")
    override val conditionIconUrl: List<String>,
    @ColumnInfo(name= "windMph")
    override val windSpeed: Double,
    @ColumnInfo(name= "precipIn")
    override val precip: Double,
    @ColumnInfo(name= "feelslikeF")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name= "visMiles")
    override val visibilityDistance: Double
): UnitSpecificCurrentWatherEntry