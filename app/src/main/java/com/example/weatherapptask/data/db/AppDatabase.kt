package com.example.weatherapptask.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherapptask.data.db.models.WeatherInfoEntity

@Database(entities = [WeatherInfoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val medCardDao: WeatherDao

    companion object {
        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "weather-app-database")
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}
