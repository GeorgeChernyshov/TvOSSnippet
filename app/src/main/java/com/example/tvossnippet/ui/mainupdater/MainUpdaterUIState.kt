package com.example.tvossnippet.ui.mainupdater

data class MainUpdaterUiState(
    val weather: WeatherState = WeatherState.Loading,
    val news: NewsState = NewsState.Loading
)