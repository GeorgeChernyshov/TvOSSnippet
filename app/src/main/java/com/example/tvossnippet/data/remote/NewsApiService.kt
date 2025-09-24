package com.example.tvossnippet.data.remote

import com.example.tvossnippet.data.model.news.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us", // e.g., "us", "gb", "in"
        @Query("category") category: String = "general", // e.g., "business", "technology", "entertainment"
        @Query("apiKey") apiKey: String // Your API key
    ): NewsApiResponse

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String
    ): NewsApiResponse
}