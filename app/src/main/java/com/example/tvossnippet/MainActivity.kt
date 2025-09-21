package com.example.tvossnippet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.example.tvossnippet.screen.MainUpdaterScreen
import com.example.tvossnippet.theme.TvOSSnippetTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TvOSSnippetTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainUpdaterScreen()
                }
            }
        }
    }
}