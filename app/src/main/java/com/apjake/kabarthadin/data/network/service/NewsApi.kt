package com.apjake.kabarthadin.data.network.service

import com.apjake.kabarthadin.data.network.dto.ArticleDto
import com.apjake.kabarthadin.data.network.dto.NewsResponseDto
import com.apjake.kabarthadin.domain.model.Article
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface NewsApi {
    companion object{
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "a1afac10fe2f436aad0a77f0cda0d8de"
    }

    @GET("v2/everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("apiKey") apiKey: String = API_KEY,
    ): NewsResponseDto

}