package com.example.weatherapptask.data.repositories

import android.content.Context
import com.example.weatherapptask.data.network.WeatherServiceApi
import com.example.weatherapptask.data.network.mappers.WeatherInfoMapper
import com.example.weatherapptask.domain.weather.models.WeatherInfo
import java.lang.Exception

class WeatherRepository(
        private val api: WeatherServiceApi,
        private val context: Context
) {

    suspend fun getCurrentWeatherInfo(city: String): WeatherInfo? {
        return try {
            val response = api.getCurrentWeather(city)
            if (response.isSuccessful) {
                response.body()?.let { WeatherInfoMapper.weatherInfoFromResponse(it, context) }
            } else {
                null
            }
        } catch (ex: Exception) {
            null
        }
    }
}