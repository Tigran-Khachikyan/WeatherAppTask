package com.example.weatherapptask.data.db.mappers

import com.example.weatherapptask.data.db.models.WeatherInfoEntity
import com.example.weatherapptask.domain.weather.models.Unit
import com.example.weatherapptask.domain.weather.models.WeatherInfo

object WeatherInfoDbMapper : Mapper<WeatherInfo, WeatherInfoEntity> {

    override fun toEntity(model: WeatherInfo): WeatherInfoEntity {
        return WeatherInfoEntity(location = model.location,
                temperature = model.temperature,
                windSpeed = model.windSpeed,
                humidity = model.humidity,
                visibility = model.visibility,
                sunrise = model.sunrise,
                sunset = model.sunset,
                country = model.country,
                unit = model.unit.name)
    }

    override fun fromEntity(entity: WeatherInfoEntity): WeatherInfo {
        return WeatherInfo(entity.location,
                entity.temperature,
                entity.windSpeed,
                entity.humidity,
                entity.visibility,
                entity.sunrise,
                entity.sunset,
                entity.country,
                Unit.valueOf(entity.unit)
        )
    }
}