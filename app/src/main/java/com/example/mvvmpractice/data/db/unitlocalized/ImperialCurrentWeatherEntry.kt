package com.example.mvvmpractice.data.db.unitlocalized

import androidx.room.ColumnInfo

data class ImperialCurrentWeatherEntry (
    @ColumnInfo(name = "temperature")
    override val temperature: Int,
    @ColumnInfo(name= "precip")
    override val precip: Double,
    @ColumnInfo(name= "feelslike")
    override val feelsLikeTemperature: Double,
    @ColumnInfo(name= "wind_speed")
    override val wind_speed: Int,
    /*

    @ColumnInfo(name= "feelslike")
    override val feelsLikeTemperature: Double,

     */
): UnitSpecificCurrentWatherEntry