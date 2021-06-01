package com.example.weatherapptask

import android.app.Application
import com.example.weatherapptask.data.network.ApiProvider
import com.example.weatherapptask.di.appModule
import com.example.weatherapptask.di.domainModule
import com.example.weatherapptask.di.networkModule
import com.example.weatherapptask.di.repositoryModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        (get<ApiProvider>()).init()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                    listOf(appModule,
                            networkModule,
                            repositoryModule,
                            domainModule)
            )
        }
    }
}