package com.example.tvossnippet.data.model.news

data class NewsArticleEntity(
    val sourceName: String,
    val author: String?,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val publishedAt: String,
    val url: String
)