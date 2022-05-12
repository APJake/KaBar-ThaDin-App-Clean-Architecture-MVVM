package com.apjake.kabarthadin.domain.usecase

import com.apjake.kabarthadin.common.util.Resource
import com.apjake.kabarthadin.domain.model.Article
import com.apjake.kabarthadin.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchArticlesUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<Article>>>{
        if(query.trim().isBlank()) return flow{}
        return newsRepository.searchArticles(query,1)
    }
}