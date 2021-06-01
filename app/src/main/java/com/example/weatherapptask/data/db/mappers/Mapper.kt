package com.example.weatherapptask.data.db.mappers

interface Mapper<MODEL, ENTITY> {

    fun toEntity(model: MODEL): ENTITY

    fun fromEntity(entity: ENTITY): MODEL
}