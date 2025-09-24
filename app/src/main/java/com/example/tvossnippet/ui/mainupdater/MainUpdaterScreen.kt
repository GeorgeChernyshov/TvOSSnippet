package com.example.tvossnippet.ui.mainupdater

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.tvossnippet.theme.TvOSSnippetTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MainUpdaterScreen(
    viewModel: MainUpdaterViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    val currentDateTime = remember { // Use remember to avoid recalculating unnecessarily
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm - E, MMM d"))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(48.dp) // Generous padding for TV UI
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "My TV Hub",
                style = MaterialTheme.typography.displaySmall, // Large text for TV
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = currentDateTime,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround // Distribute space between items
        ) {
            Column(
                modifier = Modifier
                    .weight(1f) // Takes half the width
                    .padding(end = 24.dp) // Space between sections
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.shapes.medium
                    )
                    .padding(24.dp)
            ) {
                Text(
                    text = "Weather",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(16.dp))

                WeatherContent(uiState.weather)
            }

            Column(
                modifier = Modifier
                    .weight(1f) // Takes the other half
                    .padding(start = 24.dp) // Space between sections
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.shapes.medium
                    )
                    .padding(24.dp)
            ) {
                Text(
                    text = "Latest News",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Fetching news headlines...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable fun WeatherContent(weatherState: WeatherState) {
    when (weatherState) {
        WeatherState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp), // Fixed height for loading state
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(40.dp))
            }

            Text(
                text = "Loading weather...",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        is WeatherState.Success -> {
            val weather = weatherState.weatherEntity
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(weather.iconUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = weather.description,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(80.dp) // Large icon
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "${weather.temperature}°C",
                        style = MaterialTheme.typography.displayMedium, // Prominent temperature
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = weather.description
                            .replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Text(
                        text = weather.cityName,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Humidity: ${weather.humidity}%",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            ) // Can add more details here: feelsLike, wind, etc.
        }

        is WeatherState.Error -> {
            Text(
                text = "Error: ${weatherState.message}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }

        WeatherState.Empty -> {
            Text(
                text = "No weather data available.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 1280,
    heightDp = 720
)
@Composable
fun WeatherContentPreview() {
    TvOSSnippetTheme {
//        WeatherContent(WeatherState.Loading)
    }
}

@Preview(
    showBackground = true,
    widthDp = 1280,
    heightDp = 720
)
@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
fun MainUpdaterScreenPreview() {
    TvOSSnippetTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MainUpdaterScreen()
        }
    }
}