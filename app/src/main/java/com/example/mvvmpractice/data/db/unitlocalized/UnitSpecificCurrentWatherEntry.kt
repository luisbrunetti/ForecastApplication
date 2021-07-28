package com.example.mvvmpractice.data.db.unitlocalized

interface UnitSpecificCurrentWatherEntry {
    val temperature: Double
    val conditionIconUrl: List<String>
    val windSpeed: Double
    val precip: Double
    val feelsLikeTemperature:Double
    val visibilityDistance: Double
}