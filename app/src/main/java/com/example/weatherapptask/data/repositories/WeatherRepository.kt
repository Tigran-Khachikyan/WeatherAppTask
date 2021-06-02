package com.example.weatherapptask.data.repositories

import android.content.Context
import android.util.Log
import com.example.weatherapptask.data.State
import com.example.weatherapptask.data.db.AppDatabase
import com.example.weatherapptask.data.db.mappers.WeatherInfoDbMapper
import com.example.weatherapptask.data.network.WeatherServiceApi
import com.example.weatherapptask.data.network.mappers.WeatherInfoMapper
import com.example.weatherapptask.domain.weather.models.WeatherInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class WeatherRepository(
        private val api: WeatherServiceApi,
        database: AppDatabase,
        private val context: Context,
) {

    private val dao = database.medCardDao

    private suspend fun saveCurrentWeatherInfo(weatherInfo: WeatherInfo) {
        dao.saveWeatherInfo(WeatherInfoDbMapper.toEntity(weatherInfo))
    }

    private suspend fun getCurrentWeatherFromCache(city: String): WeatherInfo? {
        return dao.getWeatherInfo(city)?.let { WeatherInfoDbMapper.fromEntity(it) }
    }

    suspend fun getCurrentWeatherInfo(city: String): Flow<State<WeatherInfo>> = flow {
        try {
            // delay(500)
            emit(State.Loading())
            val response = api.getCurrentWeather(city)
            if (response.isSuccessful) {
                val info = response.body()?.let { WeatherInfoMapper(context).weatherInfoFromResponse(it) }
                if (info != null) {
                    //  delay(500)
                    saveCurrentWeatherInfo(info)
                    emit(State.Success.FromNetwork(info))
                } else {
                    //  delay(500)
                    emit(State.Error<WeatherInfo>("Response is empty"))
                    getCurrentWeatherFromCache(city)
                            ?.let { emit(State.Success.FromCache(it)) }
                            ?: emit(State.Error<WeatherInfo>("Cache is empty"))
                }
            } else {
                // delay(500)
                emit(State.Error<WeatherInfo>("Response does not succeed"))
                getCurrentWeatherFromCache(city)
                        ?.let { emit(State.Success.FromCache(it)) }
                        ?: emit(State.Error<WeatherInfo>("Cache is empty"))
            }
        } catch (ex: Exception) {
            //  delay(500)
            emit(State.Error<WeatherInfo>("Check connection!"))
            getCurrentWeatherFromCache(city)
                    ?.let { emit(State.Success.FromCache(it)) }
                    ?: emit(State.Error<WeatherInfo>("Cache is empty"))
        }
    }
}