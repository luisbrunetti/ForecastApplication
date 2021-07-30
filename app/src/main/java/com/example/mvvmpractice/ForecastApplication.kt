package com.example.mvvmpractice

import android.app.Application
import android.util.Log
import com.example.mvvmpractice.data.db.ForecastDatabase
import com.example.mvvmpractice.data.network.ConnectivityInterceptor
import com.example.mvvmpractice.data.network.ConnectivityInterceptorImpl
import com.example.mvvmpractice.data.network.WeatherNetworkDataSource
import com.example.mvvmpractice.data.network.WeatherNetworkDataSourceImpl
import com.example.mvvmpractice.data.repository.ForecastRepository
import com.example.mvvmpractice.data.repository.ForecastRepositoryImpl
import com.example.mvvmpractice.ui.weather.current.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class ForecastApplication: Application(), KodeinAware {
    companion object val TAG = "ForecastApplication"
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))
        bind() from singleton { ForecastDatabase(instance()) } //This instance will be returned  fro mAndroid X Moduelo Also i will be la aplication Context
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        //Specify interace Connectivity Interceptor
        //Bind this interface with singleton will return ConnectivityInterceptorImplemetation
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton {ApixuWeatherApiService(instance())}
        Log.d(TAG,"OLI")
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }

        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(),instance()) }
        bind() from provider{ CurrentWeatherViewModelFactory(instance())}
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}