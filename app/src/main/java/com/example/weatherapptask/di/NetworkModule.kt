package com.example.weatherapptask.di

import com.example.weatherapptask.data.network.ApiProvider
import com.example.weatherapptask.data.network.interceptors.BasicAuthInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val networkModule = module {

    single { BasicAuthInterceptor() }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<Gson> { GsonBuilder().create() }

    single {
        ApiProvider(
                listOf(get<BasicAuthInterceptor>(), get<HttpLoggingInterceptor>()),
                gson = get()
        )
    }

    single { get<ApiProvider>().getMyServiceApi() }
}