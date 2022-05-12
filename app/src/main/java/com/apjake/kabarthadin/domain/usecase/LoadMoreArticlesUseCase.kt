package com.apjake.kabarthadin.domain.usecase

import com.apjake.kabarthadin.common.util.Resource
import com.apjake.kabarthadin.domain.model.Article
import com.apjake.kabarthadin.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class LoadMoreArticlesUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(query: String, page: Int): Flow<Resource<List<Article>>> {
        return newsRepository.loadMoreArticles(query, page)
    }
}