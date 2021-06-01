package com.example.weatherapptask.domain.weather.models

import java.util.*

data class WeatherInfo(
        val location: String,
        val temperature: Float,
        val windSpeed: Float,
        val humidity: Int,
        val visibility: String,
        val sunrise: String,
        val sunset: String,
        val country: String,
        val unit: Unit = Unit.IMPERIAL
)