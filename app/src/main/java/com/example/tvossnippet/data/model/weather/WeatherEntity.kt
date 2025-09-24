package com.example.tvossnippet.data.model.weather

data class WeatherEntity(
    val cityName: String,
    val description: String,
    val iconUrl: String, // We'll build this from the icon code
    val temperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val timestamp: Long
)