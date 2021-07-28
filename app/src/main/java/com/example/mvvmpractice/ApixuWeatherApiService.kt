package com.example.mvvmpractice

import android.util.Log

import com.example.mvvmpractice.data.network.response.CurrentWeatherResponse
import com.example.mvvmpractice.data.network.ConnectivityInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "b5a76d599e82417b3c586c972944c860"

//http://api.weatherstack.com/current?access_key=b5a76d599e82417b3c586c972944c860&query=New%20York
interface ApixuWeatherApiService {

    @GET("current")
    fun getCurrentWeather( @Query("query") location: String) : Deferred<CurrentWeatherResponse>

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): ApixuWeatherApiService{
            //Pasar el request en cada request que se hace
            val requestInterceptor = Interceptor{ chain ->
                val url = chain.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("access_key", API_KEY)
                    .build()
                val request = chain
                    .request()
                    .newBuilder()
                    .url(url)
                    .build()
                Log.d("request",request.toString())
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor) //use Context IS NOT A GOOD PRACTICE
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)

        }
    }
}