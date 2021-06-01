package com.example.weatherapptask.data.network

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiProvider(
        private val interceptors: List<Interceptor> = emptyList(),
        private val gson: Gson
) {

    private lateinit var myServiceApi: WeatherServiceApi

    companion object {
        private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    }

    fun init() {
        initMyServiceApi()
    }

    fun getMyServiceApi(): WeatherServiceApi = myServiceApi

    private fun initMyServiceApi() {
        myServiceApi = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(
                        OkHttpClient.Builder()
                                .apply {
                                    connectTimeout(60, TimeUnit.SECONDS)
                                    callTimeout(60, TimeUnit.SECONDS)
                                    readTimeout(60, TimeUnit.SECONDS)
                                    writeTimeout(60, TimeUnit.SECONDS)
                                    interceptors.forEach { addInterceptor(it) }
                                }
                                .build()
                )
                .build()
                .create(WeatherServiceApi::class.java)
    }
}