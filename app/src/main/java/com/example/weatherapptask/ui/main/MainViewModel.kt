package com.example.weatherapptask.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapptask.domain.weather.WeatherInteractor
import com.example.weatherapptask.domain.weather.models.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MainViewModel : ViewModel(), KoinComponent {

    private val _info = MutableLiveData<WeatherInfo?>()
    private val weatherInteractor: WeatherInteractor by inject()

    fun getCurrentWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val info = weatherInteractor.getCurrentWeatherInfo(city)
            withContext(Dispatchers.Main) {
                _info.value = info
                Log.d("hhh555","info: "+ info?.country)
            }
        }
    }

    fun observeCurrentWeather(): LiveData<WeatherInfo?> = _info
}