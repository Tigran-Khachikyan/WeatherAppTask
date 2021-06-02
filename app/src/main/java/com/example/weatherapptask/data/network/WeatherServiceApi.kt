package com.example.weatherapptask.data.network

import com.example.weatherapptask.data.network.responses.weather.WeatherInfoResponse
import com.example.weatherapptask.domain.weather.models.Unit
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherServiceApi {

    // api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    // api.openweathermap.org/data/2.5/find?q=London&units=metric

    @GET("weather")
    suspend fun getCurrentWeather(
            @Query("q") city: String,
            @Query("units") unit: String = Unit.IMPERIAL.getServerName())
            : Response<WeatherInfoResponse>

}
