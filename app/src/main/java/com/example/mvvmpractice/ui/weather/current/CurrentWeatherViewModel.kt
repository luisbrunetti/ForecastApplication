package com.example.mvvmpractice.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.mvvmpractice.data.repository.ForecastRepository
import com.example.mvvmpractice.internal.UnitSystem
import com.example.mvvmpractice.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
): ViewModel() {
    //The view model doesn't know abourt the bview
    //    //The view model only exposes data to the v view
    //it's up to the view to fetch it soit absolutely loose coupling everywhere
    private val uniSystem = UnitSystem.METRIC//get from settings later

    val isMetric: Boolean get() = uniSystem == UnitSystem.METRIC

    val weather by lazyDeferred{
        forecastRepository.getCurrentWeather(isMetric)
    }
}