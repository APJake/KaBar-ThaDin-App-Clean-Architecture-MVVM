package com.apjake.kabarthadin.data.local.datasource

import com.apjake.kabarthadin.data.datasource.NewsLocalDataSource
import com.apjake.kabarthadin.data.local.dao.NewsDao
import com.apjake.kabarthadin.domain.model.Article
import javax.inject.Inject

class NewsLocalDataSourceImpl(
    private val dao: NewsDao
): NewsLocalDataSource {
    override suspend fun getAllArticles(): List<Article> {
        return dao.getArticles().map { it.toArticle() }
    }

    override suspend fun insertArticles(articles: List<Article>) {
        return dao.insertArticleList(articles.map { it.toArticleEntity() })
    }

    override suspend fun clearArticles() {
        return dao.clearArticles()
    }
}