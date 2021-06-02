package com.example.weatherapptask.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapptask.data.repositories.State
import com.example.weatherapptask.domain.weather.WeatherInteractor
import com.example.weatherapptask.domain.weather.models.WeatherInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class MainViewModel : ViewModel(), KoinComponent {

    private val _info = MutableLiveData<State<WeatherInfo>>()
    private val weatherInteractor: WeatherInteractor by inject()

    fun act(action: Action) {
        when (action) {
            is Action.GetWeatherInfo -> getCurrentWeather(action.cityName)
            // Other actions of the screen
        }
    }

    private fun getCurrentWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherInteractor.getCurrentWeatherInfo(city).collect { state ->
                withContext(Dispatchers.Main) {
                    _info.value = state
                }
            }
        }
    }

    fun observeCurrentWeather(): LiveData<State<WeatherInfo>> = _info

    sealed class Action {
        data class GetWeatherInfo(val cityName: String) : Action()
        // Other actions of the screen
    }
}