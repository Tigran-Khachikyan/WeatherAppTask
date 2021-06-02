package com.example.weatherapptask.di

import com.example.weatherapptask.data.repositories.WeatherRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    single {
        WeatherRepository(
                api = get(),
                database = get(),
                context = androidContext()
        )
    }
}