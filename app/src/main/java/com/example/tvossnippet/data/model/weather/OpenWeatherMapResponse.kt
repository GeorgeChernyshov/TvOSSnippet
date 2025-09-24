package com.example.tvossnippet.data.model.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OpenWeatherMapResponse(
    @Json(name = "weather") val weather: List<WeatherDescription>,
    @Json(name = "main") val main: MainWeatherDetails,
    @Json(name = "name") val cityName: String,
    @Json(name = "dt") val timestamp: Long // Time of data calculation, Unix, UTC
)