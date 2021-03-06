package com.example.weatherapptask.domain.weather.models

import java.util.*

enum class Unit(val tempUnit: String, val speedUnit: String) {

    METRIC("C", "m/s"),
    IMPERIAL("F", "mph");

    fun getServerName(): String = this.name.toLowerCase(Locale.US)

 /*   sealed class Converter {

        object Temperature : Converter() {
            fun celsiusFromFahrenheit(temp: Float): Float = (temp - 32) * 5 / 9
            fun fahrenheitFromCelsius(temp: Float): Float = temp * 9 / 5 + 32
        }

        object Speed : Converter() {
            fun meterPerSecondFromMilesPerHour(speed: Float): Float = speed / 2.23694f
            fun milesPerHourFromMeterPerSecond(speed: Float): Float = speed * 2.23694f
        }
    }*/
}