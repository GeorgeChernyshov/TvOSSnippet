package com.example.tvossnippet.data.remote

import com.example.tvossnippet.data.model.weather.OpenWeatherMapResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService {

    @GET("data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("q") city: String,          // e.g., "London"
        @Query("appid") apiKey: String,    // Your API key
        @Query("units") units: String = "metric" // "metric" for Celsius, "imperial" for Fahrenheit
    ): OpenWeatherMapResponse

    @GET("data/2.5/weather")
    suspend fun getCurrentWeatherByCoordinates(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): OpenWeatherMapResponse
}