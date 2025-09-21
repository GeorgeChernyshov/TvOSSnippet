package com.example.tvossnippet.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TvOSSnippetTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = darkColorScheme(
        primary = TvPurple80,
        onPrimary = TvPurple20,
        primaryContainer = TvPurple30,
        onPrimaryContainer = TvPurple90,
        secondary = TvOrange80,
        onSecondary = TvOrange20,
        secondaryContainer = TvOrange30,
        onSecondaryContainer = TvOrange90,
        tertiary = TvBlue80,
        onTertiary = TvBlue20,
        tertiaryContainer = TvBlue30,
        onTertiaryContainer = TvBlue90,
        background = TvGrey10,
        onBackground = TvGrey90,
        surface = TvGrey20,
        onSurface = TvGrey80,
        surfaceVariant = TvGrey30,
        onSurfaceVariant = TvGrey80,
        inverseSurface = TvGrey80,
        inverseOnSurface = TvGrey20,
        error = TvRed80,
        onError = TvRed20,
        errorContainer = TvRed30,
        onErrorContainer = TvRed90,
        scrim = Color(0xFF000000),
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}