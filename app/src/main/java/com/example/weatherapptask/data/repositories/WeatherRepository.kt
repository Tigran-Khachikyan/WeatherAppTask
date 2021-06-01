package com.example.weatherapptask.data.repositories

import android.content.Context
import android.util.Log
import com.example.weatherapptask.data.db.AppDatabase
import com.example.weatherapptask.data.db.mappers.WeatherInfoDbMapper
import com.example.weatherapptask.data.network.WeatherServiceApi
import com.example.weatherapptask.data.network.mappers.WeatherInfoMapper
import com.example.weatherapptask.domain.weather.models.WeatherInfo
import java.lang.Exception

class WeatherRepository(
        private val api: WeatherServiceApi,
        database: AppDatabase,
        private val context: Context
) {

    private val dao = database.medCardDao

    private suspend fun saveCurrentWeatherInfo(weatherInfo: WeatherInfo) {
        dao.saveWeatherInfo(WeatherInfoDbMapper.toEntity(weatherInfo))
    }

    private suspend fun getCurrentWeatherFromCache(city: String): WeatherInfo? {
        return dao.getWeatherInfo(city)?.let { WeatherInfoDbMapper.fromEntity(it) }
    }

    suspend fun getCurrentWeatherInfo(city: String): WeatherInfo? {
        return try {
            val response = api.getCurrentWeather(city)
            if (response.isSuccessful) {
                val info = response.body()?.let { WeatherInfoMapper.weatherInfoFromResponse(it, context) }
                if (info != null) {
                    Log.d("hhh555","info != null info: "+ info.country)
                    saveCurrentWeatherInfo(info)
                    info
                } else {
                    Log.d("hhh555"," if (info == null) {")
                    getCurrentWeatherFromCache(city)
                }
            } else {
                Log.d("hhh555","!response.isSuccessful!")
                getCurrentWeatherFromCache(city)
            }
        } catch (ex: Exception) {
            Log.d("hhh555","catch (ex: Exception)")
            Log.d("hhh555","catch (ex: Exception): ex: "+ ex.message)
            getCurrentWeatherFromCache(city)
        }
    }
}