package com.example.mvvmpractice.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class Current(
    @SerializedName("cloudcover")
    val cloudcover: Int, // 75
    @SerializedName("feelslike")
    val feelslike: Int, // 28
    @SerializedName("humidity")
    val humidity: Int, // 70
    @SerializedName("is_day")
    val isDay: String, // yes
    @SerializedName("observation_time")
    val observationTime: String, // 09:41 PM
    @SerializedName("precip")
    val precip: Double, // 0.3
    @SerializedName("temperature")
    val temperature: Int, // 28
    @SerializedName("uv_index")
    val uvIndex: Int, // 7
    @SerializedName("visibility")
    val visibility: Int, // 13
    @SerializedName("weather_code")
    val weatherCode: Int, // 116
    //@SerializedName("weather_descriptions")
    //val weatherDescriptions: List<String>,
    //@SerializedName("weather_icons")
    //val weatherIcons: List<String>,
    @SerializedName("wind_degree")
    val windDegree: Int, // 226
    @SerializedName("wind_dir")
    val windDir: String, // SW
    @SerializedName("wind_speed")
    val wind_speed: Int // 0
){
    @PrimaryKey(autoGenerate = false)
    var id : Int = CURRENT_WEATHER_ID
}