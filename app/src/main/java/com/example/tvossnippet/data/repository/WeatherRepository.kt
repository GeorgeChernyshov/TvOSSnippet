package com.example.tvossnippet.data.repository

import com.example.tvossnippet.BuildConfig
import com.example.tvossnippet.data.model.weather.WeatherEntity
import com.example.tvossnippet.data.remote.OpenWeatherMapService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: OpenWeatherMapService
) {

    private val _state = MutableStateFlow<WeatherEntity?>(null)

    fun getLatestWeather(): Flow<WeatherEntity?> {
        return _state.asStateFlow()
    }

    suspend fun refreshWeather(city: String) {
        try {
            val response = weatherService.getCurrentWeather(city, BuildConfig.OPEN_WEATHER_API_KEY)
            val weatherEntity = WeatherEntity(
                cityName = response.cityName,
                description = response.weather
                    .firstOrNull()
                    ?.description
                    ?: "N/A",
                iconUrl = "https://openweathermap.org/img/wn/${response.weather.firstOrNull()?.icon}@4x.png", // High-res icon
                temperature = response.main.temperature,
                feelsLike = response.main.feelsLike,
                humidity = response.main.humidity,
                timestamp = response.timestamp
            )

            _state.emit(weatherEntity)
        } catch (e: Exception) {
            // Handle error, log it, or propagate
            throw e
        }
    }
}