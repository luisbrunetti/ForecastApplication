package com.example.mvvmpractice.ui.weather.current

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.mvvmpractice.ApixuWeatherApiService
import com.example.mvvmpractice.R
import com.example.mvvmpractice.data.network.ConnectivityInterceptorImpl
import com.example.mvvmpractice.data.network.WeatherNetworkDataSource
import com.example.mvvmpractice.data.network.WeatherNetworkDataSourceImpl
import com.example.mvvmpractice.databinding.FragmentCurrentWeatherBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {
    private var _binding: FragmentCurrentWeatherBinding? = null
    private val binding get() = _binding!!
    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentWeatherBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel
        val apiService = ApixuWeatherApiService(ConnectivityInterceptorImpl(requireContext()))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        weatherNetworkDataSource.downloadedCurrentWeather.observe(viewLifecycleOwner, Observer {
            binding.tvTitleFragmentCurrentWeather.text = it.toString()
        })

        //Mala practica
        //It's not good practice use a GlobalScope inside of a fragment
        GlobalScope.launch(Dispatchers.Main) {
            //Only u can update STATE of UI from the Main Thread
            /*
            val currentWeatherResponse = apiService.getCurrentWeather("New York").await()
            Log.d("test",currentWeatherResponse.toString())
            binding.tvTitleFragmentCurrentWeather.text = currentWeatherResponse.toString()
             */
            weatherNetworkDataSource.fetchCurrentWeather("New York")
        }
    }

}