package com.apjake.kabarthadin.presentation.mvvm.state

import com.apjake.kabarthadin.domain.model.Article


data class ArticleListState(
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val articles: List<Article> = emptyList(),
    val endReached: Boolean = false,
    val page: Int = 1,
)