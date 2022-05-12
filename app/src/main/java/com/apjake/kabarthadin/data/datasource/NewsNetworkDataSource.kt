package com.apjake.kabarthadin.data.datasource

import com.apjake.kabarthadin.common.util.AppConstants
import com.apjake.kabarthadin.common.util.Resource
import com.apjake.kabarthadin.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsNetworkDataSource {
    suspend fun getNewsArticles(query: String, page: Int, pageSize: Int = AppConstants.DEFAULT_ARTICLE_PAGE_SIZE)
            : List<Article>
}