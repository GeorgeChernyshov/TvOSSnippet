package com.example.tvossnippet.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import com.example.tvossnippet.theme.TvOSSnippetTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.text.format

@Composable
fun MainUpdaterScreen() {
    val currentDateTime = remember { // Use remember to avoid recalculating unnecessarily
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm - E, MMM d"))
    }

    val theme = MaterialTheme
    val colorScheme = theme.colorScheme

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

                Text(
                    text = "Loading weather data...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
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