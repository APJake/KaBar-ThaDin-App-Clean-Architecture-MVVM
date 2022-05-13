package com.apjake.kabarthadin.data.local.datasource

import com.apjake.kabarthadin.data.datasource.NewsLocalDataSource
import com.apjake.kabarthadin.data.local.dao.NewsDao
import com.apjake.kabarthadin.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsLocalDataSourceImpl(
    private val dao: NewsDao
): NewsLocalDataSource {
    override fun getAllArticles(): Flow<List<Article>> {
        return dao.getArticles().map { list -> list.map { item-> item.toArticle() } }
    }

    override suspend fun insertArticles(articles: List<Article>) {
        return dao.insertArticleList(articles.map { it.toArticleEntity() })
    }

    override suspend fun clearArticles() {
        return dao.clearArticles()
    }
}