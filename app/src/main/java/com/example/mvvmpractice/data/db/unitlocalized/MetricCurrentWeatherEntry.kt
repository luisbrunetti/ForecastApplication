package com.example.mvvmpractice.data.db.unitlocalized

import androidx.room.ColumnInfo

data class MetricCurrentWeatherEntry(
    @ColumnInfo(name = "tempC")
    override val temperature: Double,
    @ColumnInfo(name= "condition_icon")
    override val conditionIconUrl: List<String>,
    @ColumnInfo(name= "windKph")
    override val windSpeed: Double,
    @ColumnInfo(name= "precipMm")
    override val precip: Double,
    @ColumnInfo(name= "feelslikeC")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name= "visKm")
    override val visibilityDistance: Double
): UnitSpecificCurrentWatherEntry