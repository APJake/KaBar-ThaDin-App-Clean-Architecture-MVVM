package com.apjake.kabarthadin.data.datasource

import com.apjake.kabarthadin.domain.model.Article

interface NewsLocalDataSource {
    suspend fun getAllArticles(): List<Article>
    suspend fun insertArticles(articles: List<Article>)
    suspend fun clearArticles()
}