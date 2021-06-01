package com.example.weatherapptask.domain.weather.models

import java.util.*

enum class Unit {

    STANDARD,
    METRIC,
    IMPERIAL;

    fun getName(): String = this.name.toLowerCase(Locale.US)

    sealed class Converter {

        object Temperature : Converter() {
            fun celsiusFromFahrenheit(temp: Float): Float = (temp - 32) * 5 / 9
            fun celsiusFromKelvin(temp: Float): Float = temp - 273.15f
            fun fahrenheitFromCelsius(temp: Float): Float = temp * 9 / 5 + 32
            fun fahrenheitFromKelvin(temp: Float): Float = (temp - 273.15f) * 9 / 5 + 32
        }

        object Speed : Converter() {
            fun meterPerSecondFromMilesPerHour(speed: Float): Float = speed / 2.23694f
            fun milesPerHourFromMeterPerSecond(speed: Float): Float = speed * 2.23694f
        }
    }
}