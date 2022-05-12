package com.apjake.kabarthadin.data.network.datasource

import com.apjake.kabarthadin.common.util.AppConstants
import com.apjake.kabarthadin.data.datasource.NewsNetworkDataSource
import com.apjake.kabarthadin.data.network.service.NewsApi
import com.apjake.kabarthadin.domain.model.Article

class NewsNetworkDataSourceImpl(
    private val api: NewsApi
): NewsNetworkDataSource {
    override suspend fun getNewsArticles(query: String, page: Int, pageSize: Int): List<Article> {
        return api.getNews(query, page, AppConstants.DEFAULT_ARTICLE_PAGE_SIZE)
            .articles?.map { it.toArticle() }?: emptyList()
    }
}