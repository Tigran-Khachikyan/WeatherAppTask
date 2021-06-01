package com.example.weatherapptask.di

import com.example.weatherapptask.domain.weather.WeatherInteractor
import org.koin.dsl.module

val domainModule = module {
    factory { WeatherInteractor(repo = get()) }
}