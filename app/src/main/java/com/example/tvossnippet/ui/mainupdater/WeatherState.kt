package com.example.tvossnippet.ui.mainupdater

import com.example.tvossnippet.data.model.weather.WeatherEntity

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val weatherEntity: WeatherEntity) : WeatherState()
    data class Error(val message: String) : WeatherState()
    object Empty : WeatherState() // No data available
}