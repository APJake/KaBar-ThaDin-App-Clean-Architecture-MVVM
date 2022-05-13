package com.apjake.kabarthadin.domain.usecase

import com.apjake.kabarthadin.domain.repository.NewsRepository

class GetAllArticlesUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke() = repository.getArticles()
}