package com.example.mvvmpractice.data.db.unitlocalized

interface UnitSpecificCurrentWatherEntry {
    val temperature: Int
    //val conditionIconUrl: List<String>
    val wind_speed: Int
    val precip: Double
    val feelsLikeTemperature:Double
}