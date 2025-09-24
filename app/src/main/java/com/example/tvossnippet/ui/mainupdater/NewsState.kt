package com.example.tvossnippet.ui.mainupdater

import com.example.tvossnippet.data.model.news.NewsArticleEntity

sealed class NewsState {
    object Loading : NewsState()
    data class Success(val articles: List<NewsArticleEntity>) : NewsState()
    data class Error(val message: String) : NewsState()
    object Empty : NewsState() // No data available
}