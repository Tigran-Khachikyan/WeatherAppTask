package com.example.weatherapptask.domain.weather

import com.example.weatherapptask.data.State
import com.example.weatherapptask.data.repositories.WeatherRepository
import com.example.weatherapptask.domain.weather.models.WeatherInfo
import kotlinx.coroutines.flow.Flow

class WeatherInteractor(
        private val repo: WeatherRepository,
) {

    suspend fun getCurrentWeatherInfo(city: String): Flow<State<WeatherInfo>> {
        return repo.getCurrentWeatherInfo(city)
    }
}