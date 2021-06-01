package com.example.weatherapptask.domain.weather

import com.example.weatherapptask.data.repositories.WeatherRepository
import com.example.weatherapptask.domain.weather.models.WeatherInfo

class WeatherInteractor(
        private val repo: WeatherRepository,
) {

    suspend fun getCurrentWeatherInfo(city: String): WeatherInfo? {
        return repo.getCurrentWeatherInfo(city)
    }
}