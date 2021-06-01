package com.example.weatherapptask.di

import com.example.weatherapptask.data.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.create(androidContext()) }
}