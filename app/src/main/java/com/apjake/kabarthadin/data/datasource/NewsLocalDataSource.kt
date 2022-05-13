package com.apjake.kabarthadin.data.datasource

import com.apjake.kabarthadin.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    fun getAllArticles(): Flow<List<Article>>
    suspend fun insertArticles(articles: List<Article>)
    suspend fun clearArticles()
}