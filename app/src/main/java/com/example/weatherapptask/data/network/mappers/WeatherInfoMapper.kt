package com.example.weatherapptask.data.network.mappers

import android.content.Context
import android.location.Geocoder
import com.example.weatherapptask.data.network.responses.weather.WeatherInfoResponse
import com.example.weatherapptask.domain.weather.models.Unit
import com.example.weatherapptask.domain.weather.models.WeatherInfo
import java.text.SimpleDateFormat
import java.util.*

const val UNKNOWN = "Unknown"
const val UNKNOWN_TEMP_SEED = -999999999F
const val UNKNOWN_HUMIDITY = -999999999

class WeatherInfoMapper(private val context: Context) {

    fun weatherInfoFromResponse(response: WeatherInfoResponse): WeatherInfo {
        return WeatherInfo(
                location = response.name ?: UNKNOWN,
                temperature = response.main?.temp?.toFloat() ?: UNKNOWN_TEMP_SEED,
                windSpeed = response.wind?.speed?.toFloat() ?: UNKNOWN_TEMP_SEED,
                humidity = response.main?.humidity ?: UNKNOWN_HUMIDITY,
                visibility = response.weather?.get(0)?.main ?: UNKNOWN,
                sunrise = response.sys?.sunrise?.getDateTime() ?: UNKNOWN,
                sunset = response.sys?.sunset?.getDateTime() ?: UNKNOWN,
                country = getCountryName(response.coord?.lat, response.coord?.lon),
                unit = Unit.IMPERIAL
        )
    }

    private fun Long.getDateTime(): String {
        val date = Date(this)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
        return format.format(date)
    }

    private fun getCountryName(lat: Double?, lon: Double?): String {
        return if (lat != null && lon != null) {
            try {
                val addresses = Geocoder(context).getFromLocation(lat, lon, 1)
                addresses[0].countryName
            } catch (ex: Exception) {
                UNKNOWN
            }
        } else {
            UNKNOWN
        }
    }
}