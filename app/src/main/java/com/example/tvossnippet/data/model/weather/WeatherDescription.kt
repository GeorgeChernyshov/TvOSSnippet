package com.example.tvossnippet.data.model.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherDescription(
    @Json(name = "id") val id: Int,
    @Json(name = "main") val main: String, // E.g., "Clouds", "Rain"
    @Json(name = "description") val description: String, // E.g., "overcast clouds"
    @Json(name = "icon") val icon: String // E.g., "04d"
)