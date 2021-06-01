package com.example.weatherapptask.data.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WEATHER_INFO")
class WeatherInfoEntity(

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "COLUMN_LOCATION")
        val location: String,

        @ColumnInfo(name = "COLUMN_TEMP")
        val temperature: Float,

        @ColumnInfo(name = "COLUMN_WIND_SPEED")
        val windSpeed: Float,

        @ColumnInfo(name = "COLUMN_HUMIDITY")
        val humidity: Int,

        @ColumnInfo(name = "COLUMN_VISIBILITY")
        val visibility: String,

        @ColumnInfo(name = "COLUMN_SUNRISE")
        val sunrise: String,

        @ColumnInfo(name = "COLUMN_SUNSET")
        val sunset: String,

        @ColumnInfo(name = "COLUMN_COUNTRY_NAME")
        val country: String,

        @ColumnInfo(name = "COLUMN_UNIT")
        val unit: String
)