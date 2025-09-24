package com.example.tvossnippet.ui.mainupdater

import androidx.activity.result.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvossnippet.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainUpdaterViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUpdaterUiState())
    val uiState = _uiState.asStateFlow()

    init {
        observeWeather()
        refreshAllData() // Initial data fetch
    }

    private fun observeWeather() = viewModelScope.launch {
        weatherRepository.getLatestWeather()
            .collectLatest { weatherEntity ->
                _uiState.value = _uiState.value.copy(
                    weather = if (weatherEntity != null) {
                        WeatherState.Success(weatherEntity)
                    } else {
                        WeatherState.Empty
                    }
                )
            }
    }

    private fun refreshAllData() {
        refreshWeather()
    }

    private fun refreshWeather() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(weather = WeatherState.Loading) // Set loading state

        try {
            weatherRepository.refreshWeather(DEFAULT_CITY)
        } catch (e: Exception) {
            _uiState.value = _uiState.value.copy(
                weather = WeatherState.Error("Failed to load weather: ${e.localizedMessage}")
            )
        }
    }

    companion object {
        private const val DEFAULT_CITY = "London"
        private const val DEFAULT_NEWS_COUNTRY = "us"
        private const val DEFAULT_NEWS_CATEGORY = "general"
        private const val NEWS_ARTICLES_LIMIT = 5
    }
}