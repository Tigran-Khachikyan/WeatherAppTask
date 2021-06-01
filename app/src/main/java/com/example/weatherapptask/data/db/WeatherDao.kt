package com.example.weatherapptask.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapptask.data.db.models.WeatherInfoEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WEATHER_INFO WHERE COLUMN_LOCATION == :city")
    suspend fun getWeatherInfo(city: String): WeatherInfoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWeatherInfo(weatherInfoEntity: WeatherInfoEntity)
}